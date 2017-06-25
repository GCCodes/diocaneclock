package main.diocaneclock;


import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class diocaneclock extends JavaPlugin {
    PluginDescriptionFile pdf = this.getDescription();
    public String prefix = ChatColor.translateAlternateColorCodes('&', "&c[&6" + pdf.getName() + "&c]&7 ");
    @Override
    public void onEnable() {
        getLogger().info("Plugin " + pdf.getFullName() + " avviato con successo!");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        saveConfig();
        getCommand("dcc").setExecutor(new commands(this));
        long hour = (1000 * 60) * 60;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        String formattedTime = sdf.format(date);
        int times = Integer.parseInt(formattedTime);
        if (getConfig().getString("bestemmia").length() > 16 || getConfig().getString("bestemmia").contains(" "))
            getLogger().info("La lunghezza della bestemmia supera i 16 caratteri o contiene degli spazi! Modificala ed esegui il comando /dcc reload.");
        else {
            new Thread(() -> {
                while (true) {
                    if (Calendar.getInstance().get(Calendar.MINUTE) == 0) {
                        String bestemmia = "";
                        for (int i = 1; i <= times; i++) {
                            bestemmia += "                DIO " + getConfig().getString("bestemmia").toUpperCase() + "\n";
                        }
                        String complete = ChatColor.translateAlternateColorCodes('&', "&8-----------&c[&6" + pdf.getName() + "&c]&8-----------\n&f" + bestemmia + "\n&8----------------------------------");
                        getServer().broadcastMessage(complete);
                        try {
                            Thread.sleep(hour);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
    @Override
    public void onDisable(){
        getLogger().info("Plugin disattivato!");
    }

}