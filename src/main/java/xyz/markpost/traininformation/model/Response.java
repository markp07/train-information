package xyz.markpost.traininformation.model;

public class Response {

  private int status = 500;

  private Object data = null;

  private String message = null;

  public Response(){

  }

  public Response(int status, Object data) {
    this.status = status;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
