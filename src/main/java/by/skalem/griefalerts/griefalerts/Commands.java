package by.skalem.griefalerts.griefalerts;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Commands implements CommandExecutor, Runnable {

    private final Griefalerts plugin;
    private long time;
    private String name;

    public Commands(Griefalerts plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(strings.length == 0){
            return false;
        }

        if (strings[0].equals("stop")) {
            if(strings.length != 2) return false;
            this.name = commandSender.getName();
            String time = strings[1];
            Thread thread = new Thread(this);
            switch (time) {
                case "1":
                    this.time = 60 * 1000;
                    thread.start();
                    return true;
                case "2":
                    this.time = 2 * 60 * 1000;
                    thread.start();
                    return true;
                case "3":
                    this.time = 3 * 60 * 1000;
                    thread.start();
                    return true;
                case "4":
                    this.time = 4 * 60 * 1000;
                    thread.start();
                    return true;
                case "5":
                    this.time = 5 * 60 * 1000;
                    thread.start();
                    return true;
                case "6":
                    this.time = 6 * 60 * 1000;
                    thread.start();
                    return true;
                case "7":
                    this.time = 7 * 60 * 1000;
                    thread.start();
                    return true;
                case "8":
                    this.time = 8 * 60 * 1000;
                    thread.start();
                    return true;
                case "9":
                    this.time = 9 * 60 * 1000;
                    thread.start();
                    return true;
                case "10":
                    this.time = 10 * 60 * 1000;
                    thread.start();
                    return true;
            }
        }
        else if (strings.length < 2) return false;
         else if(strings[0].equals("friend") && strings[1].equals("add")){

            if(strings.length != 4) return false;

            String player1 = strings[2];
            String player2 = strings[3];

            Gson gson = new Gson();
            File file1 = new File(plugin.getDataFolder() + File.separator + player1 +".json");
            File file2 = new File(plugin.getDataFolder() + File.separator + player2 +".json");

            JsonArray friends = new JsonArray();
            friends.add("Null");
            if (!file1.exists()){
                try {
                    plugin.getLogger().info("Crating new JSON");
                    file1.createNewFile();
                    plugin.getLogger().info("Created");
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    FileWriter w = new FileWriter(file1);
                    gson.toJson(friends, w);
                    w.flush();
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!file2.exists()){
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    FileWriter w = new FileWriter(file2);
                    gson.toJson(friends , w);
                    w.flush();
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                FileReader reader = new FileReader(plugin.getDataFolder() + File.separator + player1 + ".json");
                Friends f = gson.fromJson(reader, Friends.class);
                ArrayList<String> friendsString = f.List();
                if (!friendsString.contains(player2)) friends.add(player2);

                new Friends(friendsString);

                try {
                    FileWriter writer = new FileWriter(plugin.getDataFolder() + File.separator + player1 + ".json");
                    gson.toJson(friendsString,writer);

                } catch (IOException e) {
                    commandSender.sendMessage("Couldn't save file for path " + plugin.getDataFolder() + File.separator + player1 + ".json");
                    e.printStackTrace();
                    return false;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                commandSender.sendMessage("Couldn't find file for path " + plugin.getDataFolder() + File.separator + player1 + ".json");
                return false;
            }



            try {
                FileReader reader = new FileReader(plugin.getDataFolder() + File.separator + player2 + ".json");
                Friends f = gson.fromJson(reader, Friends.class);
                ArrayList<String> friendsString = f.List();
                if(!friendsString.contains(player1)) friends.add(player1);
                new Friends(friendsString);

                try {
                    FileWriter writer = new FileWriter(plugin.getDataFolder() + File.separator + player2 + ".json");
                    gson.toJson(friendsString,writer);

                } catch (IOException e) {
                    commandSender.sendMessage("Couldn't save file for path " + plugin.getDataFolder() + File.separator + player2 + ".json");
                    e.printStackTrace();
                    return false;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                commandSender.sendMessage("Couldn't find file for path " + plugin.getDataFolder() + File.separator + player2 + ".json");
                return false;
            }

            return true;


        } else if (strings[0].equals("friends") && strings[1].equals("remove")){

            if(strings.length < 4) return false;
            String player1 = strings[2];
            String player2 = strings[3];

            Gson gson = new Gson();
            File file1 = new File(plugin.getDataFolder() + File.separator + player1 +".json");
            File file2 = new File(plugin.getDataFolder() + File.separator + player2 +".json");

            if (!file1.exists()){
                commandSender.sendMessage("Cannot find user" + player1);
            }

            if (!file2.exists()){
                commandSender.sendMessage("Cannot find user" + player2);
            }


            try {
                FileReader reader = new FileReader(plugin.getDataFolder() + File.separator + player1 + ".json");
                Friends f = gson.fromJson(reader, Friends.class);
                ArrayList<String> friends = f.List();
                if(!friends.remove(player2)) commandSender.sendMessage("Couldn't find player " + player2 + "amongst friends of player" + player1);
                new Friends(friends);

                try {
                    FileWriter writer = new FileWriter(plugin.getDataFolder() + File.separator + player1 + ".json");
                    gson.toJson(friends,writer);

                } catch (IOException e) {
                    commandSender.sendMessage("Couldn't save file for path " + plugin.getDataFolder() + File.separator + player1 + ".json");
                    e.printStackTrace();
                    return false;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                commandSender.sendMessage("Couldn't find file for path " + plugin.getDataFolder() + File.separator + player1 + ".json");
                return false;
            }


            try {
                FileReader reader = new FileReader(plugin.getDataFolder() + File.separator + player2 + ".json");
                Friends f = gson.fromJson(reader, Friends.class);
                ArrayList<String> friends = f.List();
                if(!friends.remove(player2)) commandSender.sendMessage("Couldn't find player " + player1 + "amongst friends of player" + player2);
                new Friends(friends);

                try {
                    FileWriter writer = new FileWriter(plugin.getDataFolder() + File.separator + player2 + ".json");
                    gson.toJson(friends,writer);

                } catch (IOException e) {
                    commandSender.sendMessage("Couldn't save file for path " + plugin.getDataFolder() + File.separator + player2 + ".json");
                    e.printStackTrace();
                    return false;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                commandSender.sendMessage("Couldn't find file for path " + plugin.getDataFolder() + File.separator + player2 + ".json");
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public void run() {

        Threads threads = new Threads(plugin);
        threads.mute(this.name);
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threads.unmute(this.name);
    }
}
