package net.nebulabc.autorestart;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public final class Autorestart extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        FileConfiguration config = this.getConfig();
        config.addDefault("hour", 24);
        config.options().copyDefaults(true);
        saveConfig();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hypatios Autorestart has initialized");
        System.out.println("Copyright 2021 NebulaBC All rights reserved.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("[Autorestart] Your restart time is set to " + config.get("hour"));

        int hourvar = (config.getInt("hour"));
        int minutevar = (config.getInt("minute"));
        Plugin plugin = this;
        
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduleRepeatAtTime(this, new Runnable()
        {
            public void run()
            {
                System.out.println("[Autorestart] Restart process started");
                Bukkit.broadcastMessage("§8[§5Hypatios§8]§5 Restarting in 5 minutes");
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Bukkit.broadcastMessage("§8[§5Hypatios§8]§5 Restarting in 3 minutes");
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            Bukkit.broadcastMessage("§8[§5Hypatios§8]§5 Restarting in 2 minutes");
                                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                    Bukkit.broadcastMessage("§8[§5Hypatios§8]§5 Restarting in 1 minutes");
                                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                            Bukkit.broadcastMessage("§8[§5Hypatios§8]§5 Restarting now");
                                                Bukkit.spigot().restart();
                            }, 1200L);
                        }, 1200L);
                    }, 1200L);
                }, 2400L);
            }
        }, hourvar);
    }

    @Override
    public void onDisable() {
    }

    public static int scheduleRepeatAtTime(Plugin plugin, Runnable task, int hour)
    {
        Calendar cal = Calendar.getInstance();
        long now = cal.getTimeInMillis();
        if(cal.get(Calendar.HOUR_OF_DAY) >= hour)
            cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long offset = cal.getTimeInMillis() - now;
        long ticks = offset / 50L;
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, ticks, 1728000L);
    }

}
