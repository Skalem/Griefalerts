package by.skalem.griefalerts.griefalerts;

import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Griefalerts extends JavaPlugin {


    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getLogger().info("Copying config file...");
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        new DataBase(this);

        Bukkit.getPluginManager().registerEvents(new Actions(this), this);
        if(getCommand("gf") != null) {
            getCommand("gf").setExecutor(new Commands(this));
        } else {
            getLogger().warning("Couldn't load command /gf");
        }

        getLogger().info("Enabled");

    }

    @Override
    public void onDisable() {

        JsonWriter jw;
        try {
            jw = new JsonWriter(new FileWriter(getDataFolder() + File.separator + "md.json"));
            jw.beginObject();
            jw.name("muted");
            jw.beginArray();
            jw.endArray();
            jw.endObject();
            jw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        getLogger().info("Disabled");
    }

}