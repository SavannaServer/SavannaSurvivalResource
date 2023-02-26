package net.ramuremo.savannasurvivalresource.config;

import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;

public final class ResourceConfig extends ConfigFile {
    public ResourceConfig(Plugin plugin) {
        super(plugin, "config.yml");

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public <T> T getValue(@Nonnull Path<T> path) {
        return getConfig().getObject("config." + path.getPath(), path.getClazz(), path.getDefault());
    }

    public <T> void setValue(@Nonnull Path<T> path, T value) {
        getConfig().set(path.getPath(), value);
    }

    public interface Path<T> {

        String getPath();
        T getDefault();
        Class<T> getClazz();
    }
}
