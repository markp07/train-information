package xyz.markpost.traininformation.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.markpost.traininformation.carrier.ns.NSTravelInformation;
import xyz.markpost.traininformation.carrier.ns.model.NSDepartingTrain;
import xyz.markpost.traininformation.model.DepartingTrain;

@RestController
@RequestMapping("/api/departures")
public class DeparturesController {

  @Autowired
  NSTravelInformation nsTravelInformation;

  @GetMapping
  public List<NSDepartingTrain> getDepartures(){
    List<NSDepartingTrain> departingTrains = nsTravelInformation.getDepartureTimes("ut");

    return departingTrains;
  }

}
