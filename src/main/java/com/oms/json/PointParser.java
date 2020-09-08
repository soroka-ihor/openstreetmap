package com.oms.json;

import com.oms.domain.Point;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import java.util.ArrayList;


/**
 * Parsing logic here
 * @getMiddle - returns the middle point
 * @getPoints - returns an array with points
 * @getCoordinates - returns array with doubles
 */
public class PointParser {

    private ArrayList<Point> points = new ArrayList<Point>();
    private String json;

    public PointParser(String json) {
        this.json = json;
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
    public void exctractPoints()  {

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
    @Cacheable("points")
    public ArrayList<Point> getPoints() {
        return this.points;
    }
    public String getJson() {
        return this.json;
    }
}
