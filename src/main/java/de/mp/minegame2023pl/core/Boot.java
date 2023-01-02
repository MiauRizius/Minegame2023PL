package de.mp.minegame2023pl.core;

import de.mp.minegame2023pl.commands.LightningStrikeCMD;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Boot extends JavaPlugin implements Listener {

    private static Logger LOGGER;
    private static Plugin PLUGIN;
    private static final Pattern PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    public void onEnable() {
        LOGGER = getLogger();
        PLUGIN = Boot.getPlugin(Boot.class);
        registerEvent(this);
        registerCommand("strikelightning", new LightningStrikeCMD());
        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("Plugin disabled");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLightningStrike(LightningStrikeEvent event) {
        event.getWorld().strikeLightningEffect(event.getLightning().getLocation());
        event.setCancelled(true);
    }

    public static Plugin getPLUGIN() {
        return PLUGIN;
    }

    /**
     * Register an event listener
     *
     * @param listener
     *        The {@link org.bukkit.event.Listener Listener} which should be added
     */
    private void registerEvent(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    /**
     * Register a command
     *
     * @param invoke
     *        The caller of the command (like /mute - in this case, mute is the caller)
     *
     * @param commandExecutor
     *        The {@link org.bukkit.command.CommandExecutor} CommandExecutor of the command
     */
    private void registerCommand(String invoke, CommandExecutor commandExecutor) {
        Objects.requireNonNull(this.getCommand(invoke)).setExecutor(commandExecutor);
    }

    /**
     * Get a file in the server's dictionary
     *
     * @param name
     *        The name of the file
     *
     * @return returns the file
     */
    private File getPluginFile(String name) {
        return new File(Bukkit.getWorlds().get(0).getWorldFolder().getPath().replace(Bukkit.getWorlds().get(0).getName(), "")+"/plugins/"+name);
    }

    /**
     * Check if String content is only numbers
     *
     * @param strNum
     *        The String which you want to check
     *
     * @return return if the string is only made of numbers
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return PATTERN.matcher(strNum).matches();
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }
}
