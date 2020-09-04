package com.oms.rest;

import com.oms.json.PointParser;

public class Main {

    public static void main(String[] args)  {
        String json = RestClient.getJsonAsString("Московская область", "russia");
        PointParser parser = new PointParser(json);

        System.out.print(parser.getMiddle());
    }
}
