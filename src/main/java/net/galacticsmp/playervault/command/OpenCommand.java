package net.galacticsmp.playervault.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import com.viaversion.viaversion.api.type.types.item.ItemShortArrayType1_8;
import net.galacticsmp.playervault.PlayerVault;
import net.galacticsmp.playervault.util.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@CommandAlias("vault")
public class OpenCommand extends BaseCommand {

    @Default
    public void main(Player player) {
        player.sendMessage(ChatColor.RED + "Use: /vault open <int>");
    }

    @Subcommand("open")
    public void open(Player player, String[] args) {

        ArrayList<ItemStack> vaultItems = VaultUtil.getItems(player);
        Inventory vault = Bukkit.createInventory(player, 54, ChatColor.YELLOW + "" + ChatColor.BOLD + "Vault");

        vaultItems.stream()
                .forEach(itemStack -> vault.addItem(itemStack));

        player.openInventory(vault);

    }

}
