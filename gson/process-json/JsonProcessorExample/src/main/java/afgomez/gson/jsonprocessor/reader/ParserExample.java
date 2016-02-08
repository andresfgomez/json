/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afgomez.gson.jsonprocessor.reader;

import afgomez.gson.jsonprocessor.model.Country;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agomez
 */
public class ParserExample {

    public static void main(String[] args) {
        URL url = Thread.currentThread().getContextClassLoader().getResource("countries.json");
        List<Country> countries = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(new FileReader(url.getPath()));

            JsonArray jsonArray = (JsonArray) obj;
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement element = jsonArray.get(i);
                JsonObject jsonObject = element.getAsJsonObject();

                Country country = new Country(jsonObject.get("code").getAsString(), jsonObject.get("name").getAsString());
                countries.add(country);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Total countries retrieved: " + countries.size());

    }

}
