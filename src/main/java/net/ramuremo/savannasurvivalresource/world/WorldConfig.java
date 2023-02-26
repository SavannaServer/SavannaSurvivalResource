package net.ramuremo.savannasurvivalresource.world;

import net.ramuremo.savannasurvivalresource.config.ConfigFile;
import net.ramuremo.savannasurvivalresource.config.ResourceConfig;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Calendar;
import java.util.Locale;

public final class WorldConfig extends ConfigFile {
    public WorldConfig(Plugin plugin, File parent) {
        super(plugin, parent, "savanna-survival-resource.yml");

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public <T> T getValue(@Nonnull WorldConfig.Path<T> path) {
        return getConfig().getObject("config." + path.getPath(), path.getClazz(), path.getDefault());
    }

    public <T> void setValue(@Nonnull ResourceConfig.Path<T> path, T value) {
        getConfig().set(path.getPath(), value);
    }

    public interface Path<T> {
        Path<Integer> CREATED_MONTH = new Path<Integer>() {
            @Override public String getPath() {return "created-month";}
            @Override public Integer getDefault() {return Calendar.getInstance(Locale.JAPAN).get(Calendar.MONTH);}
            @Override public Class<Integer> getClazz() {return Integer.class;}
        };

        String getPath();

        T getDefault();

        Class<T> getClazz();
    }
}
