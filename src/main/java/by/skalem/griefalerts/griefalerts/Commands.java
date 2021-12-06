package by.skalem.griefalerts.griefalerts;




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
    private Threads threads;


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {

        if(strings.length == 0){
            return false;
        }

        if (strings[0].equals("stop")) {
            if(strings.length != 2) return false;
            this.name = commandSender.getName();
            String time = strings[1];
            threads = new Threads(plugin);
            Thread thread = new Thread(this);
            switch (time) {
                case "1":
                    this.time = 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 1 + " minute");
                    return true;
                case "2":
                    this.time = 2 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 2 + " minutes");
                    return true;
                case "3":
                    this.time = 3 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 3 + " minutes");
                    return true;
                case "4":
                    this.time = 4 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 4 + " minutes");
                    return true;
                case "5":
                    this.time = 5 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 5 + " minutes");
                    return true;
                case "6":
                    this.time = 6 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 6 + " minutes");
                    return true;
                case "7":
                    this.time = 7 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 7 + " minutes");
                    return true;
                case "8":
                    this.time = 8 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 8 + " minutes");
                    return true;
                case "9":
                    this.time = 9 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 9 + " minutes");
                    return true;
                case "10":
                    this.time = 10 * 60 * 1000;
                    thread.start();
                    commandSender.sendMessage("Stopped griefalerts for" + 10 + " minutes");
                    return true;
            }
        }
        else if (strings.length < 2) return false;
         else if(strings[0].equals("friend") && strings[1].equals("add")){

            if(strings.length != 4) return false;

            String player1 = strings[2];
            String player2 = strings[3];

            File file1 = new File(plugin.getDataFolder() + File.separator + player1 +".json");
            File file2 = new File(plugin.getDataFolder() + File.separator + player2 +".json");

            JsonWorker jsonWorker1 = new JsonWorker(file1);
            JsonWorker jsonWorker2 = new JsonWorker(file2);

            try {
                ArrayList<String> friends = new ArrayList<>();
                friends = jsonWorker1.readJSON();
                 if (!friends.contains(player1)) {
                     friends.add(player1);
                 } else {
                     commandSender.sendMessage(player1 + " already has friend " + player2);
                     return true;
                 }
                jsonWorker1.writeJSON(friends, "friends");
            } catch (IOException e){
                e.printStackTrace();
            }

            try {
                ArrayList<String> friends = new ArrayList<>();
                friends = jsonWorker2.readJSON();
                if (!friends.contains(player2)) {
                    friends.add(player2);
                } else {
                    commandSender.sendMessage(player2 + " already has friend " + player1);
                    return true;
                }
                jsonWorker1.writeJSON(friends, "friends");
            } catch (IOException e){
                e.printStackTrace();
            }

            commandSender.sendMessage("Successfully added players in friends");
            return true;

        } else if (strings[0].equals("friend") && strings[1].equals("remove")){


            if(strings.length != 4) return false;

            String player1 = strings[2];
            String player2 = strings[3];

            File file1 = new File(plugin.getDataFolder() + File.separator + player1 +".json");
            File file2 = new File(plugin.getDataFolder() + File.separator + player2 +".json");

            JsonWorker jsonWorker1 = new JsonWorker(file1);
            JsonWorker jsonWorker2 = new JsonWorker(file2);

            try {
                ArrayList<String> friends = new ArrayList<>();
                friends = jsonWorker1.readJSON();
                friends.remove(player1);
                jsonWorker1.writeJSON(friends, "friends");
            } catch (IOException e){
                e.printStackTrace();
            }

            try {
                ArrayList<String> friends = new ArrayList<>();
                friends = jsonWorker2.readJSON();
                friends.remove(player2);
                jsonWorker1.writeJSON(friends, "friends");
            } catch (IOException e){
                e.printStackTrace();
            }

            commandSender.sendMessage("Successfully removed players from friends");
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        plugin.getLogger().info("Starting new thread");
        plugin.getLogger().info("Muting with nickname" + this.name);
        this.threads.mute(this.name);
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        plugin.getLogger().info("Unmuting player" + this.name);
        this.threads.unmute(this.name);
    }
}
