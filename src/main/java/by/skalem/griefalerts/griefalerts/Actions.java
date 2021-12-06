package by.skalem.griefalerts.griefalerts;



import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Collection;
import java.util.Iterator;

import static org.bukkit.Bukkit.getServer;


public class Actions implements Listener {

    Griefalerts plugin;

    public Actions(Griefalerts plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void BlockBreak(BlockBreakEvent e){
        if(e.getPlayer().hasPermission("Griefalerts.spam")) return;

        Threads t = new Threads(plugin);
        t.ThreadCreator(e.getPlayer().getDisplayName(),e.getBlock().getX(),e.getBlock().getY(),e.getBlock().getZ(),e.getBlock().getType().name(),0, e.getBlock().getWorld().getName());


    }
    @EventHandler
    public void Click(PlayerInteractEvent e){

        if(e.getPlayer().hasPermission("Griefalerts.spam")){
            return;
        }

        if(e.getClickedBlock() == null) return;
        Material m = e.getClickedBlock().getType();

        Threads t = new Threads(plugin);
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (m == Material.WHITE_SHULKER_BOX || m == Material.SHULKER_BOX || m == Material.BLACK_SHULKER_BOX || m == Material.CYAN_SHULKER_BOX || m == Material.BLUE_SHULKER_BOX || m == Material.BROWN_SHULKER_BOX || m == Material.LIGHT_BLUE_SHULKER_BOX || m == Material.LIGHT_GRAY_SHULKER_BOX || m == Material.LIME_SHULKER_BOX || m == Material.MAGENTA_SHULKER_BOX || m == Material.PINK_SHULKER_BOX || m == Material.YELLOW_SHULKER_BOX || m == Material.RED_SHULKER_BOX || m == Material.PURPLE_SHULKER_BOX || m == Material.GRAY_SHULKER_BOX || m == Material.ORANGE_SHULKER_BOX || m == Material.CHEST || m == Material.BARREL || m == Material.HOPPER || m == Material.HOPPER_MINECART || m == Material.CHEST_MINECART || m == Material.FURNACE || m == Material.FURNACE_MINECART || m == Material.SMOKER || m == Material.BLAST_FURNACE) {
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 1, e.getClickedBlock().getWorld().getName());
                return;
            }
            if (m == Material.ACACIA_BUTTON || m == Material.BIRCH_BUTTON || m == Material.CRIMSON_BUTTON || m == Material.OAK_BUTTON || m == Material.DARK_OAK_BUTTON || m == Material.JUNGLE_BUTTON || m == Material.POLISHED_BLACKSTONE_BUTTON || m == Material.SPRUCE_BUTTON || m == Material.STONE_BUTTON || m == Material.WARPED_BUTTON) {
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 2, e.getClickedBlock().getWorld().getName());
                return;
            }
            if (m == Material.LEVER) {
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 3, e.getClickedBlock().getWorld().getName());
                return;
            }
            if(m == Material.ACACIA_DOOR || m == Material.OAK_DOOR || m == Material.SPRUCE_DOOR || m == Material.BIRCH_DOOR || m == Material.JUNGLE_DOOR || m == Material.DARK_OAK_DOOR || m == Material.CRIMSON_DOOR || m == Material.WARPED_DOOR){
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 5, e.getClickedBlock().getWorld().getName());
                return;
            }
            if(m == Material.ACACIA_FENCE_GATE || m == Material.OAK_FENCE_GATE || m == Material.SPRUCE_FENCE_GATE || m == Material.BIRCH_FENCE_GATE || m == Material.JUNGLE_FENCE_GATE || m == Material.DARK_OAK_FENCE_GATE || m == Material.CRIMSON_FENCE_GATE || m == Material.WARPED_FENCE_GATE){
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 6, e.getClickedBlock().getWorld().getName());
                return;
            }
            if(m == Material.COMPARATOR || m == Material.REPEATER){
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 7, e.getClickedBlock().getWorld().getName());
                return;
            }
            if (m == Material.ACACIA_TRAPDOOR || m == Material.OAK_TRAPDOOR || m == Material.SPRUCE_TRAPDOOR || m == Material.BIRCH_TRAPDOOR || m == Material.JUNGLE_TRAPDOOR || m == Material.DARK_OAK_TRAPDOOR || m == Material.CRIMSON_TRAPDOOR || m == Material.WARPED_TRAPDOOR){
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 8, e.getClickedBlock().getWorld().getName());
                return;
            }
            if (e.isBlockInHand()) {
                t.ThreadCreator(e.getPlayer().getDisplayName(), e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ(), e.getClickedBlock().getType().name(), 4, e.getClickedBlock().getWorld().getName());
                return;
            }
        }
    }

    @EventHandler
    public void Sign(SignChangeEvent e){
        if(e.getPlayer().hasPermission("Griefalerts.spam")) return;

        Player p = e.getPlayer();

        Component msg = Component.newline().content(ChatColor.GRAY + "Игрок " + ChatColor.RED + p.getDisplayName() + ChatColor.GRAY + " Поставил табличку с текстом: " + ChatColor.WHITE + e.lines() + ChatColor.GRAY + " на " + ChatColor.UNDERLINE + e.getBlock().getX() + " " + e.getBlock().getY() + " " + e.getBlock().getZ());
        msg.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + e.getBlock().getX() + " " + e.getBlock().getY() + " " + e.getBlock().getZ()));

        Collection<? extends Player> players = getServer().getOnlinePlayers();
        Iterator<? extends Player> it = players.iterator();
        if (players.size() != 0) {
            while (it.hasNext()) {
                Player pl = it.next();
                if (pl.hasPermission("Griefalerts.spam")) {
                    pl.sendMessage(msg);
                }
            }
        }
    }
}