package net.galacticsmp.playervault.listener;

import net.galacticsmp.playervault.PlayerVault;
import net.galacticsmp.playervault.util.VaultUtil;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        PersistentDataContainer data = player.getPersistentDataContainer();

        if (!data.has(new NamespacedKey(PlayerVault.getInstance(), "vault"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey(PlayerVault.getInstance(), "vault"), PersistentDataType.STRING,  "");
        }

    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "" + ChatColor.BOLD + "Vault")) {

            ArrayList<ItemStack> prunedItems = new ArrayList<>();

            Arrays.stream(event.getInventory().getContents())
                    .filter(itemStack -> {
                        if (itemStack == null) return false;
                        return true;
                    })
                    .forEach(itemStack -> prunedItems.add(itemStack));

            VaultUtil.storeItems(prunedItems, player);
        }

    }

}
