package xyz.markpost.traininformation.carrier.ns.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import xyz.markpost.traininformation.model.DepartingTrain;

@XmlRootElement(name = "VertrekkendeTrein")
@XmlType(propOrder={"tripNumber","departureTime","departureDelay","departureDelayText",
    "finalDestination","trainType","route","carrier","departureTrack"})
@XmlAccessorType(XmlAccessType.FIELD)
public class NSDepartingTrain extends DepartingTrain {

  @Override
  @XmlElement(name = "RitNummer")
  public void setTripNumber(String tripNumber) {
    this.tripNumber = tripNumber;
  }

  @Override
  @XmlElement(name = "VertrekTijd")
  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  @Override
  @XmlElement(name = "VertrekVertraging")
  public void setDepartureDelay(String departureDelay) {
    this.departureDelay = departureDelay;
  }

  @Override
  @XmlElement(name = "VertrekVertragingTekst")
  public void setDepartureDelayText(String departureDelayText) {
    this.departureDelayText = departureDelayText;
  }

  @Override
  @XmlElement(name = "EindBestemming")
  public void setFinalDestination(String finalDestination) {
    this.finalDestination = finalDestination;
  }

  @Override
  @XmlElement(name = "TreinSoort")
  public void setTrainType(String trainType) {
    this.trainType = trainType;
  }

  @Override
  @XmlElement(name = "RouteTekst")
  public void setRoute(String route) {
    this.route = route;
  }

  @Override
  @XmlElement(name = "Vervoerder")
  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  @Override
  @XmlElement(name = "VertrekSpoor")
  public void setDepartureTrack(String departureTrack) {
    this.departureTrack = departureTrack;
  }

}
