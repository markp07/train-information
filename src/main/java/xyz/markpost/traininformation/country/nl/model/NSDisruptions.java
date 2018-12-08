package xyz.markpost.traininformation.country.nl.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Storingen")
@XmlAccessorType(XmlAccessType.FIELD)
public class NSDisruptions {

  @XmlElement(name = "Ongepland")
  private NSUnplanned unplanned;

  public NSUnplanned getUnplanned() {
    return unplanned;
  }

  public void setUnplanned(NSUnplanned unplanned) {
    this.unplanned = unplanned;
  }

  @XmlElement(name = "Gepland")
  private NSPlanned planned;

  public NSPlanned getPlanned() {
    return planned;
  }

  public void setPlanned(NSPlanned departingTrains) {
    this.planned = departingTrains;
  }

  public List<NSDisruption> getPlannedAndUnplannedDisruptions() {
    List<NSDisruption> plannedAndUnplanned = new ArrayList<>();
    plannedAndUnplanned.addAll(planned.getDisruptions());
    plannedAndUnplanned.addAll(unplanned.getDisruptions());
    return plannedAndUnplanned;
  }

}
