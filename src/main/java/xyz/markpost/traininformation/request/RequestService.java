package xyz.markpost.traininformation.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * TODO: javaDoc
 * TODO: JUnit
 */
@Service
class RequestService {

  private static final Logger LOGGER = LogManager.getLogger(RequestService.class);

  private HttpURLConnection httpURLConnection;

  private RequestDTO request;

  private ResponseDTO response;

  RequestService() {
  }

  /**
   *
   * @param request
   * @return
   */
  ResponseDTO doRequest(RequestDTO request) {
    try {
      this.request = request;

      openConnection();
      addAuthentication();
      processResponse();
    } finally {
      closeConnection();
    }

    return response;
  }

  /**
   *
   */
  private void openConnection() {
    try {
      String processedUrl = request.getProcessedUrl();
      URL url = new URL(processedUrl);
      httpURLConnection = (HttpURLConnection) url.openConnection();
      httpURLConnection.setRequestMethod("GET");
    } catch (MalformedURLException malformedUrlException) {
      LOGGER.error("Malformed URL while opening connection.", malformedUrlException);
    } catch (IOException ioException) {
      LOGGER.error("IOException while opening connection.", ioException);
    }
  }

  /**
   *
   */
  private void addAuthentication() {
    if(request.hasAuthentication()){
      String authenticationString = generateAuthenticationString();
      httpURLConnection.setRequestProperty ("Authorization", authenticationString);
    }
  }

  /**
   *
   * @return
   */
  private String generateAuthenticationString(){
    String user = request.getUser();
    String password = request.getPassword();
    String userCredentials = user + ":" + password;
    return  "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
  }

  /**
   *
   */
  private void processResponse() {
    try {
      InputStream data = httpURLConnection.getInputStream();
      String responseData = getDataFromResponse(data);
      int status = getResponseStatus();
      String responseMessage = getResponseMessage();
      response = new ResponseDTO(status, responseData, responseMessage);
    } catch (IOException ioException) {
      LOGGER.error("Error while processing response.", ioException);
    }
  }

  /**
   *
   * @param data
   * @return
   * @throws IOException
   */
  private String getDataFromResponse(InputStream data) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(data));
    String inputLine;
    StringBuilder content = new StringBuilder();

    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }

    in.close();

    return content.toString();
  }

  /**
   *
   * @return
   * @throws IOException
   */
  private int getResponseStatus() throws IOException {
    return httpURLConnection.getResponseCode();
  }

  /**
   *
   * @return
   * @throws IOException
   */
  private String getResponseMessage() throws IOException {
    return httpURLConnection.getResponseMessage();
  }

  /**
   *
   */
  private void closeConnection(){
    httpURLConnection.disconnect();
  }

}
