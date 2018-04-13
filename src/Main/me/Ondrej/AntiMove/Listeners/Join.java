package me.Ondrej.AntiMove.Listeners;

import javafx.print.PageLayout;
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


    public static ArrayList<Player> player = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        File f = new File(Bukkit.getServer().getPluginManager().getPlugin("AntiMove").getDataFolder(), File.separator + "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(f);

        if (!f.exists()) {
            try {

                config.createSection("joinmessage");
                config.set("joinmessage", "&7[&6&lSystem&7] You are joining? Wait three seconds, please.");

                config.createSection("freezemessage");
                config.set("freezemessage", "&7[&6&lSystem&7] You were freeze.");

                config.createSection("unfreezemessage");
                config.set("unfreezemessage", "&7[&6&lSystem&7] You were unfreeze.");

                config.createSection("freezemessageadmin");
                config.set("freezemessageadmin", "&7[&6&lSystem&7] You are freezing player: player.");

                config.createSection("unfreezemessageadmin");
                config.set("unfreezemessageadmin", "&7[&6&lSystem&7] You are unfreezing player: player.");

                config.createSection("dontpermission");
                config.set("dontpermission", "&7[&6&lAntimove&7] &cYou don´t have permission!");

                config.createSection("noplayer");
                config.set("noplayer", "&7[&6&lAntimove&7] &cYou must give player name");

                config.createSection("playernotonline");
                config.set("playernotonline", "&7[&6&lAntimove&7] &cThis player is not online.");

                config.createSection("time");
                config.set("time", 3);

                int tim = config.getInt("time");

                String mess = config.getString("joinmessage");

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', mess));

                int vypocet = tim * 20;

                player.add(p);

                Main.pl.getServer().getScheduler().runTaskLater(Main.pl, () -> player.remove(p), vypocet);

                config.save(f);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {

                int tim = config.getInt("time");

                String mess = config.getString("joinmessage");


                p.sendMessage(ChatColor.translateAlternateColorCodes('&', mess));

                int vypocet = tim * 20;

                player.add(p);

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

}
