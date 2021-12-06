package by.skalem.griefalerts.griefalerts;

import com.sun.source.tree.IfTree;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.bukkit.Bukkit.getServer;

public class Threads implements Runnable {

    Griefalerts plugin;

    public Threads(Griefalerts plugin) {
        this.plugin = plugin;
    }

    String player;
    int x;
    int y;
    int z;
    String block;
    int code;
    String dim;

    public void ThreadCreator(String player, int x, int y, int z, String block, int code, String dim) {

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


        Component msgo, msgb, msgl, msgblock, msgdoor, msggate, msgredstone, msgtrapdoor;

        player = this.player;
        x = this.x;
        y = this.y;
        z = this.z;
        block = this.block;
        code = this.code;
        dim = this.dim;


        String player2 = DataBase.Coordinates(x, y, z, dim);
        if (player.equals(player2) || player2.contains("#")) {
            return;
        }

        try {
            File file = new File(plugin.getDataFolder() + File.separator + player2 + ".json");
            if (file.exists()) {
                ArrayList<String> friends = new JsonWorker(file).readJSON();
                if (friends.contains(player)) return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> muted = new ArrayList<>();
        File file = new File(plugin.getDataFolder() + File.separator + "md.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            muted = new JsonWorker(file).readJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }


        switch (code) {
            case 0 -> {
                Component msg = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " сломал блок " + ChatColor.RED + block + ChatColor.GRAY + " игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msg.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msg);
            }
            case 1 -> {
                msgo = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл контейнер игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msgo.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msgo);
            }
            case 2 -> {
                msgb = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " нажал на кнопку игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msgb.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msgb);
            }
            case 3 -> {
                msgl = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " нажал на рычаг игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msgl.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msgl);
            }
            case 4 -> {
                msgblock = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " поставил блок " + ChatColor.RED + block + ChatColor.GRAY + " на блок игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msgblock.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msgblock);
            }
            case 5 -> {
                msgdoor = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл дверь игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msgdoor.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msgdoor);
            }
            case 6 -> {
                msggate = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл калитку игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msggate.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msggate);
            }
            case 7 -> {
                msgredstone = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " изменил параметр блока игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msgredstone.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msgredstone);
            }
            case 8 -> {
                msgtrapdoor = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + player + ChatColor.GRAY + " открыл люк игрока " + ChatColor.RED + player2 + ChatColor.GRAY + " в " + dim + " на " + ChatColor.UNDERLINE + x + " " + y + " " + z);
                msgtrapdoor.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + x + " " + y + " " + z));
                sendMsgToAdmins(muted, msgtrapdoor);
            }
        }
    }

    public void sendMsgToAdmins(ArrayList<String> muted, Component msg) {

        Collection<? extends Player> players = getServer().getOnlinePlayers();
        Iterator<? extends Player> it = players.iterator();

        if (players.size() != 0) {
            while (it.hasNext()) {
                Player pl = it.next();
                if (pl.hasPermission("Griefalerts.spam")) {
                    if (muted != null) {
                        for (String s : muted) {
                            if (s != null) {
                                if (s.equals(pl.getName())) {
                                    return;
                                }
                            }
                        }
                        pl.sendMessage(msg);
                    } else {
                        pl.sendMessage(msg);
                    }
                }
            }
        }
    }

    public void mute(String player) {
        plugin.getLogger().info("Starting to mute");

        ArrayList<String> muted = new ArrayList<>(), oldMuted = new ArrayList<>();

        File file = new File(plugin.getDataFolder() + File.separator + "md.json");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonWorker jsonWorker = new JsonWorker(new File(plugin.getDataFolder() + File.separator + "md.json"));

        try {
            oldMuted = jsonWorker.readJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }

        muted.add(player);
        muted.addAll(oldMuted);

        try {
            jsonWorker.writeJSON(muted, "muted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unmute(String player) {

        File file = new File(plugin.getDataFolder() + File.separator + "md.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> muted = new ArrayList<>();

        JsonWorker jsonWorker = new JsonWorker(file);

        if (!file.exists()) return;

        try {
            muted = jsonWorker.readJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }

        muted.remove(player);

        try {
            jsonWorker.writeJSON(muted, "muted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
