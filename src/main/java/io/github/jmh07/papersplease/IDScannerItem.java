package io.github.jmh07.papersplease;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class IDScannerItem implements Listener {


    final static private String ID_SCANNER_NAME = ChatColor.GOLD + "ID Checker";
    final static private String SCANNER_LORE_P1 = ChatColor.GOLD + "Reveals a players identity.";
    final static private String SCANNER_LORE_P2 = ChatColor.ITALIC + "Papers Please.";
    final static private String PLAYER_IMMUNE = ChatColor.AQUA + "This is: " + ChatColor.MAGIC + "moon2LOLE";
    final static private String PLAYER_NOT_IMMUNE = ChatColor.AQUA + "This is: ";


    public static ItemStack getIDScanner() {
        ItemStack idScanner = new ItemStack(Material.STICK);

        ItemMeta idScannerMeta = idScanner.getItemMeta();

        idScannerMeta.setDisplayName(ID_SCANNER_NAME);

        ArrayList<String> idScannerLore = new ArrayList<>();

        idScannerLore.add(SCANNER_LORE_P1);
        idScannerLore.add(SCANNER_LORE_P2);

        idScannerMeta.setLore(idScannerLore);

        idScanner.setItemMeta(idScannerMeta);

        return idScanner;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent e) {

        if(e.getHand().equals(EquipmentSlot.OFF_HAND)) return;

        if(e.getRightClicked() instanceof Player){
            Player player = e.getPlayer();
            Player target = (Player) e.getRightClicked();
            PlayerInventory playerInventory = player.getInventory();
            if(playerInventory.getItemInMainHand().isSimilar(getIDScanner())){
                if(target.hasPermission("papersplease.immune")) {
                    player.sendMessage(PLAYER_IMMUNE);
                }else{
                    player.sendMessage(PLAYER_NOT_IMMUNE + target.getDisplayName());
                }
            }
        }

    }

}
