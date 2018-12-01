package xyz.markpost.traininformation.carrier.ns;

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
import org.springframework.stereotype.Service;
import xyz.markpost.traininformation.carrier.ns.model.NSDepartingTrain;
import xyz.markpost.traininformation.carrier.ns.model.NSDepartureTimes;
import xyz.markpost.traininformation.model.DepartingTrain;

@Service
public class NSTravelInformation {

  public List<NSDepartingTrain> getDepartureTimes(String station) {
    String path = NSTravelInformationConstants.DEPARTURE_TIMES
        + station;
    NSDepartureTimes nsDepartureTimes;
//    NSDepartureTimes nsDepartureTimes = null;

    try {
      nsDepartureTimes = doApiCall(path);
//      JAXBContext context = JAXBContext.newInstance(NSDepartureTimes.class);
//      Unmarshaller unmarshaller = context.createUnmarshaller();
//      nsDepartureTimes = (NSDepartureTimes) unmarshaller.unmarshal(response);
      return nsDepartureTimes.getDepartingTrains();
    } catch (IOException e) {
      //TODO: error handling
      e.printStackTrace();
    } catch (JAXBException e) {
      //TODO: error handling
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  public List<DepartingTrain> getDelays(String station) {
    return null;
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
