package xyz.markpost.traininformation.model;

public class DepartingTrain {

  protected String tripNumber;

  protected String departureTime;

  protected String departureDelay;

  protected String departureDelayText;

  protected String finalDestination;

  protected String trainType;

  protected String route;

  protected String carrier;

  protected String departureTrack;

  protected String travelTip;

  protected String notes;

  public String getTripNumber() {
    return tripNumber;
  }

  public void setTripNumber(String tripNumber) {
    this.tripNumber = tripNumber;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public String getDepartureDelay() {
    return departureDelay;
  }

  public void setDepartureDelay(String departureDelay) {
    this.departureDelay = departureDelay;
  }

  public String getDepartureDelayText() {
    return departureDelayText;
  }

  public void setDepartureDelayText(String departureDelayText) {
    this.departureDelayText = departureDelayText;
  }

  public String getFinalDestination() {
    return finalDestination;
  }

  public void setFinalDestination(String finalDestination) {
    this.finalDestination = finalDestination;
  }

  public String getTrainType() {
    return trainType;
  }

  public void setTrainType(String trainType) {
    this.trainType = trainType;
  }

  public String getRoute() {
    return route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  public String getCarrier() {
    return carrier;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public String getDepartureTrack() {
    return departureTrack;
  }

  public void setDepartureTrack(String departureTrack) {
    this.departureTrack = departureTrack;
  }

  public String getTravelTip() {
    return travelTip;
  }

  public void setTravelTip(String travelTip) {
    this.travelTip = travelTip;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

}
