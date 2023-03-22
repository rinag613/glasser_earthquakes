package glasser.earthquakes;

import com.google.gson.Gson;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
        URLConnection connection =url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader((inputStream)));
        Gson gson = new Gson();
        FeatureCollection featureCollection = gson.fromJson(reader, FeatureCollection.class);

        EarthquakeInfoFrame earthquakeInfoFrame = new EarthquakeInfoFrame(featureCollection);
        System.out.println(featureCollection.features[0].properties.place);
    }
}