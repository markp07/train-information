package xyz.markpost.traininformation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.markpost.traininformation.country.nl.NSTravelInformation;
import xyz.markpost.traininformation.country.nl.model.NSDepartingTrain;
import xyz.markpost.traininformation.country.nl.model.NSDisruption;
import xyz.markpost.traininformation.model.Disruption;
import xyz.markpost.traininformation.model.Response;

/**
 *
 * TODO: JavaDoc
 * TODO: Add train stations list
 * TODO: Add disruptions and maintenance list
 * TODO: JUnit
 * TODO: Research add DE info
 * TODO: Research add UK info
 * TODO: Research add BE info
 * TODO: Research add SE info
 * TODO: Research adding other countries
 */
@RestController
@RequestMapping(path = "/v1")
public class DeparturesController {

  @Autowired
  private NSTravelInformation nsTravelInformation;

  /**
   *
   * @param station
   * @return
   */
  @GetMapping("/departures")
  public Response getDepartures(String station){
    List<NSDepartingTrain> data = nsTravelInformation.getDepartureTimes(station);
    return new Response(200, data);
  }

  /**
   *
   * @param station
   * @return
   */
  @GetMapping("/delays")
  public Response getDelays(String station){
    List<NSDepartingTrain> data = nsTravelInformation.getDelays(station);
    return new Response(200, data);
  }

  @GetMapping("/disruptions")
  public Response getActualDisruptions(String station) {
    List<NSDisruption> data = nsTravelInformation.getActualDisruptions(station);
    return new Response(200, data);
  }

  @GetMapping("/construction")
  public Response getPlannedConstruction(String station) {
    List<NSDisruption> data = nsTravelInformation.getPlannedConstruction(station);
    return new Response(200, data);
  }

}
