package me.Ondrej.AntiMove.commands;

import me.Ondrej.AntiMove.Listeners.Join;
import me.Ondrej.AntiMove.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Freeze implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("freeze")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                File f = new File(Bukkit.getServer().getPluginManager().getPlugin("AntiMove").getDataFolder(), File.separator + "config.yml");
                FileConfiguration config = YamlConfiguration.loadConfiguration(f);

                if (args.length != 0) {
                    Player check = Main.pl.getServer().getPlayer(args[0]);

                    if (check == null) {
                        p.sendMessage("§7[§6§lAntimove§7] §cThis player is not online.");
                        return true;
                    }

                    if (Join.player.contains(check)){

                        String a = config.getString("freezemessageadmin").replaceAll("<player>", check.getName());

                        config.set("freezemessageadmin", a);

                        String mess = ChatColor.translateAlternateColorCodes('&', config.getString("freezemessage"));
                        check.sendMessage(mess);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("freezemessageadmin")));
                    } else {

                        Join.player.add(check);
                        String a = config.getString("freezemessageadmin").replaceAll("<player>", check.getName());

                        config.set("freezemessageadmin", a);

                        String mess = ChatColor.translateAlternateColorCodes('&', config.getString("freezemessage"));
                        check.sendMessage(mess);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("freezemessageadmin")));
                    }

                } else {
                    p.sendMessage("§7[§6§lAntimove§7] §cYou must give player name");
                }
            } else {
                if (args.length != 0) {
                    Player check = Main.pl.getServer().getPlayer(args[0]);

                    if (check == null) {
                        Bukkit.getConsoleSender().sendMessage("§7[§6§lAntimove§7] §cThis player is not online.");
                        return true;
                    }

                    File f = new File(Bukkit.getServer().getPluginManager().getPlugin("AntiMove").getDataFolder(), File.separator + "config.yml");
                    FileConfiguration config = YamlConfiguration.loadConfiguration(f);

                    if (Join.player.contains(check)){

                        String a = config.getString("freezemessageadmin").replaceAll("<player>", check.getName());

                        config.set("freezemessageadmin", a);

                        check.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("freezemessage")));
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("freezemessageadmin")));
                    } else {

                        String a = config.getString("freezemessageadmin").replaceAll("<player>", check.getName());

                        config.set("freezemessageadmin", a);

                        Join.player.add(check);
                        check.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("freezemessage")));
                        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("freezemessageadmin")));
                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("§7[§6§lAntimove§7] §cYou must give player name");
                }
            }
        }
        return true;
    }
}
