package xyz.markpost.traininformation.country.nl.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Ongepland")
@XmlAccessorType(XmlAccessType.FIELD)
public class NSUnplanned {

  @XmlElement(name = "Storing")
  private List<NSDisruption> disruptions;

  public List<NSDisruption> getDisruptions() {
    return disruptions;
  }

  public void setDisruptions(List<NSDisruption> disruptions) {
    this.disruptions = disruptions;
  }

}
