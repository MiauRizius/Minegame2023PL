package de.mp.minegame2023pl.commands;

import de.mp.minegame2023pl.core.Boot;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LightningStrikeCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("nerv nicht"));
            return false;
        }
        if(!player.isOp()) {
            player.sendMessage(Component.text(Color.RED+"Failed! You are not an operator."));
        }
        LightningStrike lightningStrike = player.getWorld().strikeLightning(player.getLocation());
        Boot.getLOGGER().info("Executed "+this.getClass());
        return true;
    }
}
