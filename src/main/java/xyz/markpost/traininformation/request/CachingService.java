package xyz.markpost.traininformation.request;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * TODO: refactor
 * TODO: JavaDoc
 * TODO: JUnit
 */
@Service
public class CachingService {

  @Autowired
  private RequestService requestService;

  private HashMap<String,CachingDTO> cache = new HashMap<>();

  private static final int MAX_NUMBER_CACHED_ITEMS = 10;

  /**
   *
   * @param request
   * @return
   */
  public ResponseDTO doRequest(RequestDTO request){
    if (isInCache(request)){
      CachingDTO cachedResponse = getFromCache(request);
      cachedResponse.setLastUsage();
      return cachedResponse.getResponseDTO();
    } else {
      ResponseDTO response = requestService.doRequest(request);
      String hash = generateHash(request);
      long maxAge = request.getMaxAge();
      CachingDTO cachingDTO = new CachingDTO(hash, request, response, maxAge);
      addToCache(cachingDTO);
      return response;
    }
  }

  /**
   *
   * @param request
   * @return
   */
  private boolean isInCache(RequestDTO request){
    String hash = generateHash(request);

    if(cache.containsKey(hash)){
      CachingDTO cachingDTO = cache.get(hash);
      boolean correctUrl = processedUrlEquals(request, cachingDTO.getRequestDTO());
      boolean notReachedMaxAge = !cachingDTO.hasReachedMaxAge();
      return correctUrl && notReachedMaxAge;
    } else {
      return false;
    }
  }

  private boolean processedUrlEquals(RequestDTO requestDTO, RequestDTO cachedRequestDTO) {
    String cachedRequestUrl = cachedRequestDTO.getProcessedUrl();
    String requestUrl = requestDTO.getProcessedUrl();
    return cachedRequestUrl.equals(requestUrl);
  }

  /**
   *
   * @param request
   * @return
   */
  private CachingDTO getFromCache(RequestDTO request) {
    String hash = generateHash(request);
    return cache.get(hash);
  }

  /**
   *
   * @param request
   * @return
   */
  private String generateHash(RequestDTO request) {
    String processedUrl = request.getProcessedUrl();
    return  DigestUtils.sha256Hex(processedUrl);
  }

  /**
   *
   * @param cachingDTO
   */
  private void addToCache(CachingDTO cachingDTO) {
    if(MAX_NUMBER_CACHED_ITEMS >= cache.size()){
      cleanItemFromCache();
    }
    cache.put(cachingDTO.getHash(), cachingDTO);
  }

  /**
   *
   * @return
   */
  private int cleanCache(){
    int removedItems = 0;
    ArrayList<String> toBeRemoved = new ArrayList<>();

    for(Entry<String, CachingDTO> cachedItem : cache.entrySet()) {
      CachingDTO cachingDTO = cachedItem.getValue();
      String hash = cachedItem.getKey();
      if (cachingDTO.hasReachedMaxAge()) {
        toBeRemoved.add(hash);
        removedItems++;
      }
    }

    for(String toRemove: toBeRemoved){
      cache.remove(toRemove);
    }

    return removedItems;
  }

  /**
   *
   * @param numberOfItems
   */
  private void cleanItemsFromCacheAccordingToPolicy(int numberOfItems) {
    for(int i = 0; i < numberOfItems; i++){
      String leastRecentlyUsedHash = "";
      Date date = new Date();
      Timestamp leastRecentlyUsedTimestamp = new Timestamp(date.getTime());
      for (Entry<String, CachingDTO> cachedItem : cache.entrySet()){
        CachingDTO cachingDTO = cachedItem.getValue();
        if (leastRecentlyUsedTimestamp.after(cachingDTO.getLastUsage())) {
          leastRecentlyUsedHash = cachingDTO.getHash();
          leastRecentlyUsedTimestamp = cachingDTO.getLastUsage();
        }
      }
      cache.remove(leastRecentlyUsedHash);
    }
  }

  /**
   *
   */
  private void cleanItemFromCache() {
    int cleanedUpItems = cleanCache();
    int remainingItemsToClean = 1 - cleanedUpItems;

    if(0 < remainingItemsToClean) {
      cleanItemsFromCacheAccordingToPolicy(remainingItemsToClean);
    }
  }

}
