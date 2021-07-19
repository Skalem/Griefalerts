package by.skalem.griefalerts.griefalerts;


import com.google.gson.Gson;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.bukkit.Bukkit.getServer;

public class Threads implements Runnable {

    Griefalerts plugin;
    public Threads(Griefalerts plugin){
        this.plugin = plugin;
    }

    String player;
    int x;
    int y;
    int z;
    String block;
    int code;
    String dim;
    String[] players = new String[20];

    public void ThreadCreator(String player, int x, int y, int z,String block, int code, String dim){

        this.player = player;
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block;
        this.code = code;
        this.dim = dim;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        String player;
        int x;
        int y;
        int z;
        String block;
        int code;
        String dim;


        TextComponent msgo;
        TextComponent msgb;
        TextComponent msgl;
        TextComponent msgblock;
        TextComponent msgdoor;
        TextComponent msggate;
        TextComponent msgredstone;
        TextComponent msgtrapdoor;

        Collection<? extends Player> players = getServer().getOnlinePlayers();
        Iterator<? extends Player> it = players.iterator();

        player = this.player;
        x = this.x;
        y = this.y;
        z = this.z;
        block = this.block;
        code = this.code;
        dim = this.dim;


        String player2 = DataBase.Coordinates(x, y, z, dim);
        if (player.equals(player2) || player2.contains("#")){
            return;
        }

        Gson g = new Gson();
        try {
            FileReader r = new FileReader(plugin.getDataFolder() + File.separator + player2 + ".json");
            Friends f = g.fromJson(r, Friends.class);
            ArrayList<String> friends = f.List();
            if(friends.contains(player)) return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        switch (code){
            case 0:
                TextComponent msg = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " сломал блок " + ChatColor.RED + block + ChatColor.GRAY + " игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));

                if (players.size() != 0) {
                    while (it.hasNext()){
                        Player pl  = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                pl.spigot().sendMessage(msg);
                            } else {
                                pl.spigot().sendMessage(msg);
                            }
                        }
                    }
                }
                break;
            case 1:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msgo = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл контейнер игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgo);
                            } else {
                                msgo = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл контейнер игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgo.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgo);
                            }
                        }
                    }
                }
                break;
            case 2:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msgb = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " нажал на кнопку игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgb.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgb);
                            } else {
                                msgb = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " нажал на кнопку игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgb.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgb);
                            }
                        }
                    }
                }
                break;
            case 3:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msgl = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " нажал на рычаг игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgl.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgl);
                            } else {
                                msgl = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " нажал на рычаг игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgl.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgl);
                            }
                        }
                    }
                }
                break;
            case 4:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++){
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msgblock = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " поставил блок " + ChatColor.RED + block + ChatColor.GRAY + " на блок игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgblock.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgblock);
                            } else {
                                msgblock = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " поставил блок " + ChatColor.RED + block + ChatColor.GRAY + " на блок игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgblock.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgblock);
                            }
                        }
                    }
                }
                break;

            case 5:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msgdoor = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл дверь игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgdoor.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgdoor);
                            } else {
                                msgdoor = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл дверь игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgdoor.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgdoor);
                            }
                        }
                    }
                }
                break;

            case 6:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msggate = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл калитку игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msggate.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msggate);
                            } else {
                                msggate = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл калитку игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msggate.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msggate);
                            }
                        }
                    }
                }
                break;
            case 7:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msgredstone = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " изменил параметр блока игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgredstone.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgredstone);
                            } else {
                                msgredstone = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " изменил параметр блока игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgredstone.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgredstone);
                            }
                        }
                    }
                }
                break;
            case 8:
                if (players.size() != 0) {
                    while (it.hasNext()) {
                        Player pl = it.next();
                        if (pl.hasPermission("Griefalerts.spam")) {
                            if(this.players != null) {
                                for (int i = 0; i <= this.players.length; i++) {
                                    if(this.players[i] != null) {
                                        if (this.players[i].equals(pl.getDisplayName())) {
                                            return;
                                        }
                                    }
                                }
                                msgtrapdoor = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл люк игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgtrapdoor.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgtrapdoor);
                            } else {
                                msgtrapdoor = new TextComponent(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл люк игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                                msgtrapdoor.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                                pl.spigot().sendMessage(msgtrapdoor);
                            }
                        }
                    }
                }
                break;
        }
    }

    public void mute(String player){
        for (int i = 0; i <= players.length; i++){
            if(players[i] == null) {
                players[i] = player;
                break;
            }
        }
    }

    public void unmute(String player){
        for (int i = 0; i <= players.length; i++){
            if(players[i].equals(player)){
                players[i] = null;
                break;
            }
        }
    }
}
