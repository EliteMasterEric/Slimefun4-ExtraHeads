package io.github.thebusybiscuit.extraheads;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bstats.bukkit.Metrics;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class ExtraHeads extends JavaPlugin implements SlimefunAddon {

    private final Map<EntityType, ItemStack> mobs = new EnumMap<>(EntityType.class);

    private Config cfg;
    private Logger logger;

    private ItemGroup itemGroup;
    private RecipeType recipeType;

    @Override
    public void onEnable() {
        cfg = new Config(this);
        logger = getLogger();

        // Setting up bStats
        new Metrics(this, 5650);

        if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "TheBusyBiscuit/ExtraHeads/master").start();
        }
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20)) {
            registerHead(EntityType.SNIFFER, "Sniffer Head", "3d6c9f43510cb90d24493e07b7cf8ca9f54132d09a257f20b7048022e3b1b707");
        }
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20)) {
            registerHead(EntityType.CAMEL, "Camel Head", "74b8a333dfa92e7e5a95ad4ae2d84b1bafa33dc28c054925277f60e79dafc8c4");
        }
        }

        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            registerHead("Allay Head", EntityType.ALLAY, "e1c59dccde4b8535500dcf6794ca450663f607290e2510f6d8eb1e5eb71da5af");
            registerHead("Frog Head", EntityType.FROG, "27bcccc125a4110434a85c40ada039d050f14ef7db34a3444067310f8ce69606");
            registerHead("Tadpole", EntityType.TADPOLE, "987035f5352334c2cba6ac4c65c2b9059739d6d0e839c1dd98d75d2e77957847");
            registerHead("Warden Head", EntityType.WARDEN, "c6f74361fb00490a0a98eeb814544ecdd775cb55633dbb114e60d27004cb1020");
        }

        cfg.save();

        new HeadListener(this);
    }

    private void registerHead(String name, EntityType type, String texture) {
        try {
            double chance = cfg.getOrSetDefault("chances." + type.toString(), 5.0);
            SlimefunItemStack item = new SlimefunItemStack(type.toString() + "_HEAD", texture, "&r" + name);
            new MobHead(itemGroup, item, recipeType, new CustomItemStack(item, "&rKill 1 " + ChatUtils.humanize(type.name()), "&7Chance: &e" + chance + "%")).register(this, () -> mobs.put(type, item));
        }
        catch (Exception x) {
            logger.log(Level.WARNING, x, () -> "Could not load Mob Head for Entity: " + type);
        }
    }

    public Map<EntityType, ItemStack> getMobDrops() {
        return mobs;
    }

    public Config getCfg() {
        return cfg;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/TheBusyBiscuit/ExtraHeads/issues";
    }
}