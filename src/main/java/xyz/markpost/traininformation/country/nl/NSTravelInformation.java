package xyz.markpost.traininformation.country.nl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import xyz.markpost.traininformation.country.nl.model.NSDepartingTrain;
import xyz.markpost.traininformation.country.nl.model.NSDepartureTimes;

/**
 *
 * TODO: refactor
 * TODO: use CachingService and RequestService
 * TODO: streamline XML parsing
 * TODO: JavaDoc
 * TODO: JUnit
 */
@Service
public class NSTravelInformation {

  private static final Logger LOGGER = LogManager.getLogger(NSTravelInformation.class);

  /**
   *
   * @param station
   * @return
   */
  public List<NSDepartingTrain> getDepartureTimes(String station) {
    String path = NSTravelInformationConstants.DEPARTURE_TIMES
        + station;

    try {
      NSDepartureTimes nsDepartureTimes = doApiCall(path);
      return nsDepartureTimes.getDepartingTrains();
    } catch (IOException ioException) {
      LOGGER.error("IOException occurred - NSTravelInformation:getDepartureTimes()", ioException);
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

  private NSDepartureTimes doApiCall(String path) throws IOException, JAXBException {
    InputStream response;
    String urlString = NSTravelInformationConstants.BASE_URL + path;

    URL url = new URL(urlString);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty ("Authorization", addAuthentication());

    response = con.getInputStream();

    JAXBContext context = JAXBContext.newInstance(NSDepartureTimes.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    NSDepartureTimes nsDepartureTimes = (NSDepartureTimes) unmarshaller.unmarshal(response);

    con.disconnect();

    return nsDepartureTimes;
  }

  private String addAuthentication(){
    String userCredentials = NSTravelInformationConstants.API_USERNAME + ":"
        + NSTravelInformationConstants.API_PASSWORD;
    return  "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
  }

  private String getResponse(HttpURLConnection connection) {
    String response = "";
    try {
      BufferedReader in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuilder content = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }

      in.close();

      response = content.toString();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return response;
  }

}
