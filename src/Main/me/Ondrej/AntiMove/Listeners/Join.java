package me.Ondrej.AntiMove.Listeners;

import me.Ondrej.AntiMove.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Join implements Listener{


    ArrayList<Player> player = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        File f = new File(Bukkit.getServer().getPluginManager().getPlugin("AntiMove").getDataFolder(), File.separator + "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(f);

        player.add(p);

        if (!f.exists()) {
            try {

                config.createSection("message");
                config.set("message", "&7[&6&lSystem&7] You are joining? Wait three seconds, please.");

                config.createSection("time");
                config.set("time", 3);

                int tim = config.getInt("time");

                String mess = config.getString("message");

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', mess));

                int vypocet = tim * 20;

                Main.pl.getServer().getScheduler().runTaskLater(Main.pl, () -> player.remove(p), vypocet);

                config.save(f);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            int tim = config.getInt("time");

            String mess = config.getString("message");


            p.sendMessage(ChatColor.translateAlternateColorCodes('&', mess));

            int vypocet = tim * 20;

            Main.pl.getServer().getScheduler().runTaskLater(Main.pl, () -> player.remove(p), vypocet);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if (this.player.contains(p)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent e){
        Player p = e.getPlayer();

        if (this.player.contains(p)){
            e.setCancelled(true);
        }
    }

}
