package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree {
    private List<String> femaleNames = new ArrayList<>();
    private List<String> maleNames = new ArrayList<>();
    private List<String> lastNames = new ArrayList<>();
    private List<Float> latitudes = new ArrayList<>();
    private List<Float> longitudes = new ArrayList<>();
    private List<String> countries = new ArrayList<>();
    private List<String> cities = new ArrayList<>();

    public void generateFemaleNames(File file) throws Exception {
        try (FileReader fileReader = new FileReader(file)) {

            JsonParser jsonParser = new JsonParser();

            JsonObject rootObj = (JsonObject)jsonParser.parse(fileReader);

            JsonArray nameArray = (JsonArray)rootObj.get("data");

            for(int i = 0; i < nameArray.size(); ++i) {
                femaleNames.add(nameArray.get(i).toString());
            }
        }
    }

    public void generateMaleNames(File file) throws Exception {
        try (FileReader fileReader = new FileReader(file)) {

            JsonParser jsonParser = new JsonParser();

            JsonObject rootObj = (JsonObject)jsonParser.parse(fileReader);

            JsonArray nameArray = (JsonArray)rootObj.get("data");
            for(int i = 0; i < nameArray.size(); ++i) {
                maleNames.add(nameArray.get(i).toString());
            }
        }
    }

    public void generateLastNames(File file) throws Exception {
        try (FileReader fileReader = new FileReader(file)) {

            JsonParser jsonParser = new JsonParser();

            JsonObject rootObj = (JsonObject)jsonParser.parse(fileReader);

            JsonArray nameArray = (JsonArray)rootObj.get("data");
            for(int i = 0; i < nameArray.size(); ++i) {
                lastNames.add(nameArray.get(i).toString());
            }
        }
    }

    public void generateLocations(File file) throws Exception {
        try (FileReader fileReader = new FileReader(file)) {

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
        }
    }

    public int generateYear() {
        // just generate a random int based on the level of the generation
        return 0;
    }

    public Event generateEvent(String eventType, int birthYear) {



        return null;
    }

    public Person generatePerson(String gender, int generations, Connection conn) {




        return null;
    }

}
