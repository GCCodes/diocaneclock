package main.diocaneclock;

import mkremins.fanciful.FancyMessage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;



public class commands implements Listener, CommandExecutor {
    private diocaneclock plugin;
    public commands(diocaneclock pl){
        plugin = pl;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("dcc")){
            if(args.length == 0){
                new FancyMessage(ChatColor.translateAlternateColorCodes('&', "&8--------------&c[&6" + plugin.pdf.getName() + "&c]&8--------------\n"))
                .then(ChatColor.translateAlternateColorCodes('&', "&6DioCaneClock &7-&a Developed by Gianluca_C\n"))
                .then(ChatColor.translateAlternateColorCodes('&', "&fhttps://t.me/GianlucasChannel\n"))
                .color(org.bukkit.ChatColor.WHITE)
                .link("https://t.me/GianlucasChannel")
                .tooltip("Clicca qui per visitare il mio canale Telegram!")
                .then("https://t.me/Gianluca_C")
                .color(org.bukkit.ChatColor.WHITE)
                .link("https://t.me/Gianluca_C")
                .tooltip("Clicca qui per scrivermi su Telegram!")
                .send(sender);
                //sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8-----------&c[&6" + plugin.pdf.getName() + "&c]&8-----------\n        &6DioCaneClock &7-&a Developed by Gianluca_C\n        &fhttp://t.me/GianlucasChannel&f\n        http://t.me/Gianluca_C"));
            }
            else if(args.length == 1){
                if(args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    if (plugin.getConfig().getString("bestemmia").length() > 16 || plugin.getConfig().getString("bestemmia").contains(" "))
                        plugin.getLogger().info("La lunghezza della bestemmia supera i 16 caratteri o contiene degli spazi! Modificala ed esegui il comando /dcc reload.");
                    else
                        sender.sendMessage(plugin.prefix + ChatColor.translateAlternateColorCodes('&', "&aBestemmia ricaricata con successo!"));
                }
            }
            else{
                sender.sendMessage(plugin.prefix + ChatColor.translateAlternateColorCodes('&', "&cErrore! Sintassi: &f/dcc reload"));
            }
        }
        return true;
    }
}
