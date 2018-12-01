package xyz.markpost.traininformation.carrier.ns.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ActueleVertrekTijden")
@XmlAccessorType(XmlAccessType.FIELD)
public class NSDepartureTimes {

  @XmlElement(name = "VertrekkendeTrein")
  private List<NSDepartingTrain> departingTrains;

  public List<NSDepartingTrain> getDepartingTrains() {
    return departingTrains;
  }

  public void setDepartingTrains(List<NSDepartingTrain> departingTrains) {
    this.departingTrains = departingTrains;
  }

}
