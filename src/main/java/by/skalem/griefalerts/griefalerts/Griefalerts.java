package by.skalem.griefalerts.griefalerts;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
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

        List<String> aliases = new ArrayList<>();
        aliases.add("friend");
        aliases.add("stop");
        if(getCommand("gf") != null) {
            getCommand("gf").setAliases(aliases);
        }
        getLogger().info("Enabled");

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

}