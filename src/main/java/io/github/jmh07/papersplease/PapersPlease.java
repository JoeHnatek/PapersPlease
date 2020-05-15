package io.github.jmh07.papersplease;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class PapersPlease extends JavaPlugin implements CommandExecutor {

    final private String ID_SCANNER_GIVEN = ChatColor.GOLD + "Item may have been added to your inventory!" +
            " Check to make sure!";
    final private String NO_PERM = ChatColor.DARK_RED + "You don't have permission!";
    final private String ERROR_PLAYERS_ONLY = "Error: Players are only allowed to use this command!";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Papers Please plugin has started!");
        this.getCommand("papersplease").setExecutor(this::onCommand);
        this.getServer().getPluginManager().registerEvents(new IDScannerItem(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        boolean isPlayer = sender instanceof Player;
        boolean hasNoArgs = args.length == 0;

        // /papersplease will give one item, no durability
        if(isPlayer && hasNoArgs){
            Player player = (Player) sender;
            if(player.hasPermission("papersplease.give")) {

                player.getInventory().addItem(IDScannerItem.getIDScanner());

                player.sendMessage(ID_SCANNER_GIVEN);

            }else{
                player.sendMessage(NO_PERM);
                return false;
            }
        }else{
            getLogger().info(ERROR_PLAYERS_ONLY);
            return false;
        }

        return true;
    }



}
