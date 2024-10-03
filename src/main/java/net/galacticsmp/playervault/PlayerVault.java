package net.galacticsmp.playervault;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.galacticsmp.playervault.command.OpenCommand;
import net.galacticsmp.playervault.listener.EventListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class PlayerVault extends JavaPlugin {

    @Getter private static PlayerVault instance;

    private PaperCommandManager commandManager;

    public static PlayerVault getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        getLogger().info("Enabled.");
        registerCommands();
        registerEvents();

    }

    private void registerCommands() {

        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new OpenCommand());

    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EventListener(),  this);
    }

}
