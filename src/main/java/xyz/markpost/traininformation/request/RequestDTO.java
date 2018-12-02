package xyz.markpost.traininformation.request;

import java.util.Map;

/**
 * TODO: comment
 */
public class RequestDTO {

  private String url;

  private Map<String, String> parameters;

  private String user;

  private String password;

  private long maxAge = 5 * 60 * 1000;

  /**
   * TODO: comment
   * @param url
   * @param parameters
   * @param user
   * @param password
   */
  public RequestDTO(String url, Map<String, String> parameters, String user, String password){
    this.url = url;
    this.parameters = parameters;
    this.user = user;
    this.password = password;
  }

  /**
   * TODO: comment
   * @return
   */
  public String getProcessedUrl(){
    StringBuilder processedUrl = new StringBuilder();
    String protocol = "https://";
    processedUrl.append(protocol)
        .append(url)
        .append("?");

    for (Map.Entry<String, String> parameter : parameters.entrySet()){
      processedUrl.append(parameter.getKey())
          .append("=")
          .append(parameter.getValue())
          .append("&");
    }

    return processedUrl.toString();
  }

  public boolean hasAuthentication(){
    return null != user && !"".equals(user)
        && null != password && !"".equals(password);
  }

  /**
   * TODO: comment
   * @return
   */
  public String getUser() {
    return user;
  }

  /**
   * TODO: comment
   * @return
   */
  public String getPassword() {
    return password;
  }

  public long getMaxAge() {
    return maxAge;
  }

  public void setMaxAge(long maxAge) {
    this.maxAge = maxAge;
  }
}
