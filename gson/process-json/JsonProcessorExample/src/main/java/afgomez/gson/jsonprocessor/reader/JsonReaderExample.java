package afgomez.gson.jsonprocessor.reader;

import afgomez.gson.jsonprocessor.model.Country;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agomez
 */
public class JsonReaderExample {

    public static void main(String[] args) {

        URL url = Thread.currentThread().getContextClassLoader().getResource("countries.json");
        List<Country> countries = new ArrayList<>();

        try {
            JsonReader jsonReader = new JsonReader(new FileReader(url.getPath()));
            jsonReader.beginArray();

            while (jsonReader.hasNext()) {
                jsonReader.beginObject();

                Country country = new Country();
                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    switch (name) {
                        case "name":
                            country.setName(jsonReader.nextString());
                            break;
                        case "code":
                            country.setId(jsonReader.nextString());
                            break;
                    }
                }
                countries.add(country);
                jsonReader.endObject();
            }
            jsonReader.endArray();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Total countries retrieved: " + countries.size());

    }

}
