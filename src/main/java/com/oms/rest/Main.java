package com.oms.rest;

import com.oms.domain.Point;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/obj.json")));
        String json = "";
        while (reader.ready()) {
            json += reader.readLine();
        }
        json = json.substring(1, json.length() - 1);

        JSONObject jsonObject = new JSONObject(json);
        JSONObject coordinates = jsonObject.getJSONObject("geojson");
        JSONArray points = coordinates.getJSONArray("coordinates");
        ArrayList<Point> listPoints = new ArrayList<Point>();

        for (int i = 0; i < points.length(); i++) {
            for (Point point : exctractPoints(points.get(i).toString())) {
                listPoints.add(point);
            }
        }

        for (Point point : listPoints) System.out.print("x: " + point.getX() + " y: " + point.getY() + "\n");
    }
    public static ArrayList<Point> exctractPoints(String line) {
        line = line.replaceAll("\\[", "").replaceAll("]", "");
        String[] lines = line.split(",");
        ArrayList<Point> points = new ArrayList<Point>();

        for (int i = 0; i < lines.length; i += 2) {
            points.add(new Point(
                    Double.parseDouble(lines[i]),
                    Double.parseDouble(lines[i + 1])
            ));
        }
        return points;
    }
}
