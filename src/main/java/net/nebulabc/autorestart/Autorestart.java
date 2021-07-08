package net.nebulabc.autorestart;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Logger;

public final class Autorestart extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getConfig();
        FileConfiguration config = this.getConfig();
        saveConfig();
        System.out.println("------------------------------------------------");
        System.out.println("          Autorestart has initialized");
        System.out.println("  Copyright 2021 NebulaBC All rights reserved.");
        System.out.println("------------------------------------------------");
        System.out.println("[Autorestart] Your restart time is set to " + config.get("hour"));

        int hourvar = (config.getInt("hour"));
        String prefix = (config.getString("prefix"));
        String title = (config.getString("title"));
        String sound = (config.getString("sound"));
        String maincolor = (config.getString("maincolor"));
        String secondarycolor = (config.getString("secondarycolor"));
        String textcolor = (config.getString("textcolor"));
        Plugin plugin = this;
        
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduleRepeatAtTime(this, new Runnable()
        {
            public void run()
            {
                System.out.println("[Autorestart] Restart process started");
                Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting in 5 minutes");
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting in 3 minutes");
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting in 2 minutes");
                                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                    Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting in 1 minute");
                                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                            Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting in 3");
                                            for (Player player : Bukkit.getOnlinePlayers())
                                            {
                                                if(sound.equals("true")) {
                                                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                                                }
                                                if(title.equals("true")){
                                                    player.sendTitle("Restarting in 3", "", 0, 1, 0);
                                                }
                                            }
                                                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                                    Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting in 2");
                                                    for (Player player : Bukkit.getOnlinePlayers())
                                                    {
                                                        if(sound.equals("true")) {
                                                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                                                        }
                                                        if(title.equals("true")){
                                                            player.sendTitle("Restarting in 2", "", 0, 1, 0);
                                                        }
                                                    }
                                                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                                            Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting in 1");
                                                            for (Player player : Bukkit.getOnlinePlayers())
                                                            {
                                                                if(sound.equals("true")) {
                                                                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                                                                }
                                                                if(title.equals("true")){
                                                                    player.sendTitle("Restarting in 1", "", 0, 1, 0);
                                                                }
                                                            }
                                                                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                                                    Bukkit.broadcastMessage(secondarycolor + "[" + maincolor + prefix + secondarycolor + "]" + textcolor + " Restarting now");
                                                                    for (Player player : Bukkit.getOnlinePlayers())
                                                                    {
                                                                        if(sound.equals("true")) {
                                                                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0.2F);
                                                                        }
                                                                        if(title.equals("true")){
                                                                            player.sendTitle("Restarting now", "", 0, 1, 0);
                                                                        }
                                                                    }
                                                                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                                                            Bukkit.spigot().restart();
                                                }, 20L);
                                            }, 20L);
                                        }, 20L);
                                    }, 20L);
                                }, 1160L);
                            }, 1200);
                        }, 1200L);
                    }, 2400L);
            }
        }, hourvar);

        Logger logger = this.getLogger();

        new UpdateChecker(this, 94004).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("There is not a new update available.");
            } else {
                logger.info("There is a new update available.");
            }
        });

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