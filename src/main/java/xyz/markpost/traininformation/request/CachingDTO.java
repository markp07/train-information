package xyz.markpost.traininformation.request;

import java.sql.Timestamp;
import java.util.Date;

/**
 * TODO: comment
 */
public class CachingDTO {

  private String hash;

  private RequestDTO requestDTO;

  private ResponseDTO responseDTO;

  private long maxAge;

  private Timestamp initialUsage;

  private Timestamp lastUsage;

  /**
   * TODO: comment
   * @param hash
   * @param requestDTO
   * @param responseDTO
   */
  public CachingDTO(String hash, RequestDTO requestDTO, ResponseDTO responseDTO, long maxAge) {
    this.hash = hash;
    this.requestDTO = requestDTO;
    this.responseDTO = responseDTO;
    this.maxAge = maxAge;
    setLastUsage();
    initialUsage = lastUsage;
  }

  /**
   * TODO: comment
   * @return
   */
  public String getHash() {
    return hash;
  }

  /**
   * TODO: comment
   * @return
   */
  public RequestDTO getRequestDTO() {
    return requestDTO;
  }

  /**
   * TODO: comment
   * @return
   */
  public ResponseDTO getResponseDTO() {
    return responseDTO;
  }

  public Timestamp getLastUsage() {
    return lastUsage;
  }

  public void setLastUsage() {
    Date date = new Date();
    lastUsage = new Timestamp(date.getTime());
  }

  public boolean hasReachedMaxAge(){
    Date date = new Date();
    Timestamp currentTime = new Timestamp(date.getTime());
    long age = currentTime.getTime() - initialUsage.getTime();
    return maxAge <= age;
  }
}
