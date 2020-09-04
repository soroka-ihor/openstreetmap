package com.oms.json;

import com.oms.domain.Point;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@Getter
public class PointParser {
    private ArrayList<Point> points = new ArrayList<Point>();

    public PointParser(String json) {
        exctractPoints(json);
    }

    public double[] getCoordinates() {

        double[] coordinates = new double[points.size() * 2];
        int iterator = 0;

        for (int i = 0; i < points.size(); i++) {
            coordinates[iterator] = points.get(i).getX();
            coordinates[iterator + 1] = points.get(i).getY();
            iterator += 2;
        }

        return coordinates;
    }
    private void exctractPoints(String json) {

        json = json.substring(1, json.length() - 1);
        JSONObject jsonObject = new JSONObject(json);
        JSONObject coordinates = jsonObject.getJSONObject("geojson");
        JSONArray jsonPoints = coordinates.getJSONArray("coordinates");


        for (int i = 0; i < jsonPoints.length(); i++) {
            getPointsFromLine(jsonPoints.get(i).toString());
        }
    }
    private void getPointsFromLine(String line) {
        line = line.replaceAll("\\[", "").replaceAll("]", "");
        String[] lines = line.split(",");
       // ArrayList<Point> points = new ArrayList<Point>();

        for (int i = 0; i < lines.length; i += 2) {
            points.add(new Point(
                    Double.parseDouble(lines[i]),
                    Double.parseDouble(lines[i + 1])
            ));
        }
    }
    public Point getMiddle() {
        double x = 0.0;
        double y = 0.0;

        for (Point point : points) {
            x += point.getX();
            y += point.getY();
        }

        return new Point(x / points.size(), y / points.size());
    }
}
