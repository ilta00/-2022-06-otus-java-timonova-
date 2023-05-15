package ru.otus.dataprocessor;

import jakarta.json.Json;
import jakarta.json.JsonStructure;
import ru.otus.model.Measurement;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        try (var jsonReader = Json.createReader(ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName))) {
            JsonStructure jsonFromTheFile = jsonReader.read();
            var gson = new Gson();
            List<Measurement> measurementList = new ArrayList<>();
            jsonFromTheFile.asJsonArray().forEach(jsonValue -> {
                Measurement measurement = gson.fromJson(jsonValue.toString(), Measurement.class);
                measurementList.add(measurement);
            });
            return measurementList;
        }
    }
}
