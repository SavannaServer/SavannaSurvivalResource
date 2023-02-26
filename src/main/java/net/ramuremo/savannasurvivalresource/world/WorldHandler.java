package net.ramuremo.savannasurvivalresource.world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public final class WorldHandler {
    private final Plugin plugin;

    public WorldHandler(@Nonnull Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadWorlds() {
        loadWorld("resource-normal", World.Environment.NORMAL);
        loadWorld("resource-nether", World.Environment.NETHER);
        loadWorld("resource-end", World.Environment.THE_END);
    }

    public void unloadWorlds() {
        unloadWorld("resource-normal");
        unloadWorld("resource-nether");
        unloadWorld("resource-end");
    }

    private void loadWorld(@Nonnull String name, @Nonnull World.Environment environment) {
        new WorldCreator(name)
                .environment(environment)
                .createWorld();
        final WorldConfig worldConfig = new WorldConfig(plugin, new File("./" + name));
        if (Objects.equals(worldConfig.getValue(WorldConfig.Path.CREATED_MONTH), Calendar.getInstance(Locale.JAPAN).get(Calendar.MONTH)))
            resetWorld(name, environment);
    }

    private void unloadWorld(@Nonnull String name) {
        Bukkit.unloadWorld(name, true);
    }

    private void resetWorld(@Nonnull String name, @Nonnull World.Environment environment) {
        unloadWorld(name);
        new File("./" + name).delete();
        loadWorld(name, environment);
    }
}
