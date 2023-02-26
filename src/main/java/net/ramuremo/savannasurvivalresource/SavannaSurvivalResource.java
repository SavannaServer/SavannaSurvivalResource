package net.ramuremo.savannasurvivalresource;

import net.ramuremo.savannasurvivalresource.world.WorldHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class SavannaSurvivalResource extends JavaPlugin {
    private WorldHandler worldHandler;

    @Override
    public void onEnable() {
        worldHandler = new WorldHandler(this);
        worldHandler.loadWorlds();

        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        worldHandler.unloadWorlds();
        getLogger().info("The plugin has been disabled.");
    }
}
