package com.oms.controllers;

import com.oms.domain.Point;
import com.oms.exceptions.WrongCountryOrStateException;
import com.oms.json.PointParser;
import com.oms.clients.RestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class RestfulController {

    private PointParser parser;

    @GetMapping(value = "/points", produces = APPLICATION_JSON_VALUE)
    public ArrayList<Point> get(@RequestParam(value = "state") String state,
                                      @RequestParam(value = "country") String country) {
          parser = new PointParser(RestClient.getJsonAsString(state, country));

          if (parser.getJson().equals("[]"))
              throw new WrongCountryOrStateException(country, state);

          parser.exctractPoints();
          return parser.getPoints();
    }

}
