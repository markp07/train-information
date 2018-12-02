package xyz.markpost.traininformation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.markpost.traininformation.country.nl.NSTravelInformation;
import xyz.markpost.traininformation.country.nl.model.NSDepartingTrain;
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
public class DeparturesController {

  @Autowired
  private NSTravelInformation nsTravelInformation;

  /**
   *
   * @param station
   * @return
   */
  @GetMapping(value = "/api/departures")
  public Response getDepartures(String station){
    List<NSDepartingTrain> data = nsTravelInformation.getDepartureTimes(station);
    return new Response(200, data);
  }

  /**
   *
   * @param station
   * @return
   */
  @GetMapping(value = "/api/delays")
  public Response getDelays(String station){
    List<NSDepartingTrain> data = nsTravelInformation.getDelays(station);
    return new Response(200, data);
  }

}
