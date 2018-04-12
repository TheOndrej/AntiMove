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

public class Unfreeze implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("unfreeze")){
            if (sender instanceof Player){
                Player p = (Player)sender;

                File f = new File(Bukkit.getServer().getPluginManager().getPlugin("AntiMove").getDataFolder(), File.separator + "config.yml");
                FileConfiguration config = YamlConfiguration.loadConfiguration(f);

                if (args.length != 0) {
                    Player check = Main.pl.getServer().getPlayer(args[0]);

                    if (check == null) {
                        p.sendMessage("§7[§6§lAntimove§7] §cThis player is not online.");
                        return true;
                    }


                    Join.player.remove(check);

                    String a = config.getString("unfreezemessageadmin").replaceAll("<player>", check.getName());

                    config.set("unfreezemessageadmin", a);

                    String mess = ChatColor.translateAlternateColorCodes('&', config.getString("unfreezemessage"));
                    check.sendMessage(mess);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("unfreezemessageadmin")));

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

                    String a = config.getString("unfreezemessageadmin").replaceAll("<player>", check.getName());

                    config.set("unfreezemessageadmin", a);

                    Join.player.remove(check);
                    check.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("unfreezemessage")));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("unfreezemessageadmin")));

                } else {
                    Bukkit.getConsoleSender().sendMessage("§7[§6§lAntimove§7] §cYou must give player name");
                }
            }
            }
        return true;
        }
    }
