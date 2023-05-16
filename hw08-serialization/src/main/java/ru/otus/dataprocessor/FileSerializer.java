package ru.otus.dataprocessor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {

    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        try (var bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            final String[] text = {""};
            final boolean[] firstline = {true};
            data.entrySet().forEach(entry -> {
                text[0] = String.format("%s%s\"%s\":%s", text[0], firstline[0] ? "" : ",", entry.getKey(), entry.getValue());
                firstline[0] = false;
            });
            text[0] = String.format("{%s}", text[0]);
            bufferedWriter.write(text[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
