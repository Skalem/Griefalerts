package by.skalem.griefalerts.griefalerts;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;

public class JsonWorker {
    private final File file;

    JsonWorker(File file) {
        this.file = file;
    }

    public ArrayList<String> readJSON() throws IOException {
        Gson g = new Gson();

        FileReader r = new FileReader(file);
        if (file.exists()) {
            return g.fromJson(r, Friends.class).List();
        }
        //returns null if no file was found
        return null;
    }

    public void writeJSON(@NotNull ArrayList<String> list, String arrayName) throws IOException {
        file.createNewFile();

        JsonWriter jw = new JsonWriter(new FileWriter(file));
        jw.beginObject();
        jw.name("muted");
        jw.beginArray();
        for (String s : list) {
            jw.value(s);
        }
        jw.endArray();
        jw.endObject();
        jw.close();
    }
}



