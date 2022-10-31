package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FamilyTree {

    public String generateFemaleNames(File file) throws Exception {
        // make a method that generates family tree data
        try (FileReader fileReader = new FileReader(file)) {

            JsonParser jsonParser = new JsonParser();

            JsonObject rootObj = (JsonObject)jsonParser.parse(fileReader);

            JsonArray nameArray = (JsonArray)rootObj.get("data");
            int rnd = new Random().nextInt(nameArray.size());
            String femaleName = nameArray.get(rnd).getAsString();

            return femaleName;
        }
    }

    public String generateMaleNames(File file) throws Exception {
        // make a method that generates family tree data
        try (FileReader fileReader = new FileReader(file)) {

            JsonParser jsonParser = new JsonParser();

            JsonObject rootObj = (JsonObject)jsonParser.parse(fileReader);

            JsonArray nameArray = (JsonArray)rootObj.get("data");
            int rnd = new Random().nextInt(nameArray.size());
            String maleName = nameArray.get(rnd).getAsString();

            return maleName;
        }
    }

    public String generateLastNames(File file) throws Exception {
        // make a method that generates family tree data
        try (FileReader fileReader = new FileReader(file)) {

            JsonParser jsonParser = new JsonParser();

            JsonObject rootObj = (JsonObject)jsonParser.parse(fileReader);

            JsonArray nameArray = (JsonArray)rootObj.get("data");
            int rnd = new Random().nextInt(nameArray.size());
            String lastName = nameArray.get(rnd).getAsString();

            return lastName;
        }
    }

    public Location generateLocations(File file) throws Exception {
        // TODO: make a method that generates family tree data
        try (FileReader fileReader = new FileReader(file)) {

            List<String> countries = new ArrayList<>();
            List<String> cities = new ArrayList<>();
            List<Float> latitudes = new ArrayList<>();
            List<Float> longitudes = new ArrayList<>();

            JsonParser jsonParser = new JsonParser();

            JsonObject rootObj = (JsonObject)jsonParser.parse(fileReader);

            JsonArray nameArray = (JsonArray)rootObj.get("data");
            for (int i = 0; i < nameArray.size(); ++i) {
                JsonObject locationObj = (JsonObject)nameArray.get(i);

                JsonPrimitive country = (JsonPrimitive)locationObj.get("country");
                JsonPrimitive city = (JsonPrimitive)locationObj.get("city");
                JsonPrimitive latitude = (JsonPrimitive)locationObj.get("latitude");
                JsonPrimitive longitude = (JsonPrimitive)locationObj.get("longitude");

                String countryString = country.getAsString();
                String cityString = city.getAsString();
                Float latVal = latitude.getAsFloat();
                Float longVal = longitude.getAsFloat();

                countries.add(countryString);
                cities.add(cityString);
                latitudes.add(latVal);
                longitudes.add(longVal);
            }

            int rnd = new Random().nextInt(nameArray.size());
            String finalCountry = countries.get(rnd);
            String finalCity = cities.get(rnd);
            Float finalLat = latitudes.get(rnd);
            Float finalLong = longitudes.get(rnd);

            Location location = new Location(finalCountry, finalCity, finalLat, finalLong);

            return location;
        }
    }

    public int generateYear() {
        // just generate a random int based on the level of the generation
        return 0;
    }

}
