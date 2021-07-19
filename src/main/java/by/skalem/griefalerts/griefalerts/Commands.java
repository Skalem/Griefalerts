package by.skalem.griefalerts.griefalerts;



import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

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
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {

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
                    commandSender.sendMessage("Stopped griefalerts for" + 1 + "minute");
                    return true;
                case "2":
                    this.time = 2 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 2 + "minutes");
                    return true;
                case "3":
                    this.time = 3 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 3 + "minutes");
                    return true;
                case "4":
                    this.time = 4 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 4 + "minutes");
                    return true;
                case "5":
                    this.time = 5 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 5 + "minutes");
                    return true;
                case "6":
                    this.time = 6 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 6 + "minutes");
                    return true;
                case "7":
                    this.time = 7 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 7 + "minutes");
                    return true;
                case "8":
                    this.time = 8 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 8 + "minutes");
                    return true;
                case "9":
                    this.time = 9 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 9 + "minutes");
                    return true;
                case "10":
                    this.time = 10 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 10 + "minutes");
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


            if (!file1.exists()){
                JsonWriter jw;
                try {
                    jw = new JsonWriter(new FileWriter(file1));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    jw.beginObject();
                    jw.name("friends");
                    jw.beginArray();
                    jw.value(player2);
                    jw.endArray();
                    jw.endObject();
                    jw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ArrayList<String> friends;
                Friends friends1;

                try {
                    friends1 = gson.fromJson(new FileReader(file1), Friends.class);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return true;
                }

                friends = friends1.List();
                if(!friends.contains(player2)){
                    friends.add(player2);
                } else {
                    commandSender.sendMessage("This user already registered as a friend");
                    return true;
                }
                JsonWriter jw;
                try {
                    jw = new JsonWriter(new FileWriter(file1));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    jw.beginObject();
                    jw.name("friends");
                    jw.beginArray();

                    for (String friend : friends) {
                        jw.value(friend);
                    }

                    jw.endArray();
                    jw.endObject();
                    jw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!file2.exists()){
                JsonWriter jw;
                try {
                    jw = new JsonWriter(new FileWriter(file2));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    jw.beginObject();
                    jw.name("friends");
                    jw.beginArray();
                    jw.value(player1);
                    jw.endArray();
                    jw.endObject();
                    jw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ArrayList<String> friends;
                Friends friends2;

                try {
                    friends2 = gson.fromJson(new FileReader(file2), Friends.class);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return true;
                }
                friends = friends2.List();
                if(!friends.contains(player1)){
                    friends.add(player1);
                } else {
                    commandSender.sendMessage("This user already registered as a friend");
                    return true;
                }
                JsonWriter jw;
                try {
                    jw = new JsonWriter(new FileWriter(file2));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    jw.beginObject();
                    jw.name("friends");
                    jw.beginArray();

                    for (String friend : friends) {
                        jw.value(friend);
                    }

                    jw.endArray();
                    jw.endObject();
                    jw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            commandSender.sendMessage("Successfully added players in friends");
            return true;

        } else if (strings[0].equals("friend") && strings[1].equals("remove")){


            if(strings.length != 4) return false;

            String player1 = strings[2];
            String player2 = strings[3];

            Gson gson = new Gson();
            File file1 = new File(plugin.getDataFolder() + File.separator + player1 +".json");
            File file2 = new File(plugin.getDataFolder() + File.separator + player2 +".json");

            if(file1.exists()){
                ArrayList<String> friends;
                Friends friends1;
                try {
                    friends1 = gson.fromJson(new FileReader(file1), Friends.class);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return true;
                }
                friends = friends1.List();
                if(friends.contains(player2)){
                    friends.remove(player2);
                } else {
                    commandSender.sendMessage("This players are not friends");
                    return true;
                }
                JsonWriter jw;
                try {
                    jw = new JsonWriter(new FileWriter(file1));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    jw.beginObject();
                    jw.name("friends");
                    jw.beginArray();

                    for (String friend : friends) {
                        jw.value(friend);
                    }

                    jw.endArray();
                    jw.endObject();
                    jw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(file2.exists()){
                ArrayList<String> friends;
                Friends friends1;
                try {
                    friends1 = gson.fromJson(new FileReader(file2), Friends.class);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return true;
                }
                friends = friends1.List();
                if(friends.contains(player1)){
                    friends.remove(player1);
                } else {
                    commandSender.sendMessage("This players are not friends");
                    return true;
                }
                JsonWriter jw;
                try {
                    jw = new JsonWriter(new FileWriter(file2));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                try {
                    jw.beginObject();
                    jw.name("friends");
                    jw.beginArray();

                    for (String friend : friends) {
                        jw.value(friend);
                    }

                    jw.endArray();
                    jw.endObject();
                    jw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            commandSender.sendMessage("Successfully removed players from friends");
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
