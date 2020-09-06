package com.oms.rest;

import com.oms.domain.Point;
import com.oms.json.PointParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {

    private PointParser parser;

    @GetMapping("/points")
    public ArrayList<Point> getPoints(@RequestParam(value = "state") String state,
                                      @RequestParam(value = "country") String country) {
        parser = new PointParser(RestClient.getJsonAsString(state, country));

        return parser.getPoints();
    }

}
