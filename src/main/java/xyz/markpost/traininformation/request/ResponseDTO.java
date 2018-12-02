package xyz.markpost.traininformation.request;

/**
 * TODO: comment
 */
public class ResponseDTO {

  private int status;

  private String data;

  private String message;

  /**
   * TODO: comment
   * @param status
   * @param data
   * @param message
   */
  public ResponseDTO(int status, String data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  /**
   * TODO: comment
   * @return
   */
  public int getStatus() {
    return status;
  }

  /**
   * TODO: comment
   * @return
   */
  public String getData() {
    return data;
  }

  /**
   * TODO: comment
   * @return
   */
  public String getMessage() {
    return message;
  }
}
