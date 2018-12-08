package xyz.markpost.traininformation.country.nl.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import xyz.markpost.traininformation.model.Disruption;

@XmlRootElement(name = "Storing")
@XmlType(propOrder={"id","route","period","cause","advise","message","date"})
@XmlAccessorType(XmlAccessType.FIELD)
public class NSDisruption extends Disruption {

  @Override
  @XmlElement(name = "id")
  public void setId(String id) {
    this.id = id;
  }

  @Override
  @XmlElement(name = "Traject")
  public void setRoute(String route) {
    this.route = route;
  }

  @Override
  @XmlElement(name = "Periode")
  public void setPeriod(String period) {
    this.period = period;
  }

  @Override
  @XmlElement(name = "Reden")
  public void setCause(String cause) {
    this.cause = cause;
  }

  @Override
  @XmlElement(name = "Advies")
  public void setAdvise(String advise) {
    this.advise = advise;
  }

  @Override
  @XmlElement(name = "Bericht")
  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  @XmlElement(name = "Datum")
  public void setDate(String date) {
    this.date = date;
  }

}
