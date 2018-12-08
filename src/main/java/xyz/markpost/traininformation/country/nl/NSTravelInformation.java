package xyz.markpost.traininformation.country.nl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.markpost.traininformation.country.nl.model.NSDepartingTrain;
import xyz.markpost.traininformation.country.nl.model.NSDepartureTimes;
import xyz.markpost.traininformation.country.nl.model.NSDisruption;
import xyz.markpost.traininformation.country.nl.model.NSDisruptions;
import xyz.markpost.traininformation.request.CachingService;
import xyz.markpost.traininformation.request.RequestDTO;
import xyz.markpost.traininformation.request.ResponseDTO;

/**
 *
 * TODO: refactor
 * TODO: streamline XML parsing (new class)
 * TODO: JavaDoc
 * TODO: JUnit
 */
@Service
public class NSTravelInformation {

  private static final Logger LOGGER = LogManager.getLogger(NSTravelInformation.class);

  @Autowired
  private CachingService cachingService;

  /**
   *
   * @param station
   * @return
   */
  public List<NSDepartingTrain> getDepartureTimes(String station) {
    try {
      String url =
          NSTravelInformationConstants.BASE_URL + NSTravelInformationConstants.DEPARTURE_TIMES;
      HashMap<String, String> parameters = new HashMap<>();
      parameters.put(NSTravelInformationConstants.STATION_PARAM, station);
      String user = NSTravelInformationConstants.API_USERNAME;
      String password = NSTravelInformationConstants.API_PASSWORD;
      RequestDTO request = new RequestDTO(url, parameters, user, password);
      ResponseDTO response = cachingService.doRequest(request);

      if (200 == response.getStatus()) {
        JAXBContext context = JAXBContext.newInstance(NSDepartureTimes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(response.getData());
        NSDepartureTimes nsDepartureTimes = (NSDepartureTimes) unmarshaller.unmarshal(reader);
        return nsDepartureTimes.getDepartingTrains();
      }
    } catch (JAXBException jaxbException) {
      LOGGER.error("JAXBException occurred - NSTravelInformation:getDepartureTimes()", jaxbException);
    }

    return new ArrayList<>();
  }

  /**
   *
   * @param station
   * @return
   */
  public List<NSDepartingTrain> getDelays(String station) {
    List<NSDepartingTrain> departingTrains = getDepartureTimes(station);
    List<NSDepartingTrain> delayedDepartingTrains = new ArrayList<>();

    for (NSDepartingTrain departingTrain: departingTrains) {
      if(null != departingTrain.getDepartureDelay()) {
        delayedDepartingTrains.add(departingTrain);
      }
    }

    return delayedDepartingTrains;
  }

  /**
   *
   * @param station
   * @return
   */
  public List<NSDisruption> getActualDisruptions(String station) {
    try {
      String url =
          NSTravelInformationConstants.BASE_URL + NSTravelInformationConstants.DISRUPTIONS;
      HashMap<String, String> parameters = new HashMap<>();

      if(null != station) {
        parameters.put(NSTravelInformationConstants.STATION_PARAM, station);
      }
      parameters.put(NSTravelInformationConstants.ACTUAL_PARAM, "true");
      parameters.put(NSTravelInformationConstants.UNPLANNED_PARAM, "false");
      String user = NSTravelInformationConstants.API_USERNAME;
      String password = NSTravelInformationConstants.API_PASSWORD;
      RequestDTO request = new RequestDTO(url, parameters, user, password);
      ResponseDTO response = cachingService.doRequest(request);

      if (200 == response.getStatus()) {
        JAXBContext context = JAXBContext.newInstance(NSDisruptions.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(response.getData());
        NSDisruptions nsDisruptions = (NSDisruptions) unmarshaller.unmarshal(reader);
        return nsDisruptions.getPlannedAndUnplannedDisruptions();
      }
    } catch (JAXBException jaxbException) {
      LOGGER.error("JAXBException occurred - NSTravelInformation:getActualDisruptions()", jaxbException);
    }

    return new ArrayList<>();
  }

  /**
   *
   * @param station
   * @return
   */
  public List<NSDisruption> getPlannedConstruction(String station) {
    try {
      String url =
          NSTravelInformationConstants.BASE_URL + NSTravelInformationConstants.DISRUPTIONS;
      HashMap<String, String> parameters = new HashMap<>();
      if(null != station) {
        parameters.put(NSTravelInformationConstants.STATION_PARAM, station);
      }
      parameters.put(NSTravelInformationConstants.ACTUAL_PARAM, "false");
      parameters.put(NSTravelInformationConstants.UNPLANNED_PARAM, "true");
      String user = NSTravelInformationConstants.API_USERNAME;
      String password = NSTravelInformationConstants.API_PASSWORD;
      RequestDTO request = new RequestDTO(url, parameters, user, password);
      ResponseDTO response = cachingService.doRequest(request);

      if (200 == response.getStatus()) {
        JAXBContext context = JAXBContext.newInstance(NSDisruptions.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(response.getData());
        NSDisruptions nsDisruptions = (NSDisruptions) unmarshaller.unmarshal(reader);
        return nsDisruptions.getPlannedAndUnplannedDisruptions();
      }
    } catch (JAXBException jaxbException) {
      LOGGER.error("JAXBException occurred - NSTravelInformation:getPlannedConstruction()", jaxbException);
    }

    return new ArrayList<>();
  }

}
