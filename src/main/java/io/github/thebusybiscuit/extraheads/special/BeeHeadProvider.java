package io.github.thebusybiscuit.extraheads.special;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Bee;
import org.bukkit.entity.LivingEntity;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

public class BeeHeadProvider implements MobHeadProvider {
    final Config cfg;

    static final String DEFAULT = "259001a851bb1b9e9c05de5d5c68b1ea0dc8bd86babf188e0aded8f912c07d0d";

    static final String TRANS = "142111ad76f92c584ce1fabdd993dc73939b336d681dbf740d0f13365845c671";

    public BeeHeadProvider(Config cfg) {
        this.cfg = cfg;
    }

    public String getDescription() {
        if (cfg.getOrSetDefault("options.special.bee-variant-chance", 0.1) > 0.0) {
            return "&6Has a &bS&dE&fC&fR&dE&bT rare variant!";
        } else {
            return null;
        }
    }

    @Override
    public SlimefunItemStack getHead(LivingEntity target) {
        if (target instanceof Bee) {
            return getHeadByVariant((Bee) target);
        } else {
            return MobHeadUtils.buildHead("BEE_HEAD", "Bee Head", DEFAULT, getGuideLore());
        }
    }

    String[] getGuideLore() {
        double dropChance = cfg.getOrSetDefault("chances.BEE", 5.0);
        double variantChance = cfg.getOrSetDefault("options.special.bee-variant-chance", 10.0f);

        if (variantChance > 0.0) {
            return new String[] {
                "&7Drop Chance: &e" + dropChance + "%",
                "&6Has a &bS&dE&fC&fR&dE&bT &6rare variant!"
            };
        } else {
            return new String[] {
                "&7Drop Chance: &e" + dropChance + "%"
            };
        }
    }

    SlimefunItemStack getHeadByVariant(Bee target) {
        double variantChance = cfg.getOrSetDefault("options.special.bee-variant-chance", 10.0) / 100.0;
        if (variantChance <= 0.0) {
            return MobHeadUtils.buildHead("BEE_HEAD", "Bee Head", DEFAULT);
        }

        // 10% odds by default
        if (ThreadLocalRandom.current().nextFloat() <= variantChance) {
            return MobHeadUtils.buildHead("BEE_HEAD_TRANS", "Bee Head (Trans)", TRANS);
        } else {
            return MobHeadUtils.buildHead("BEE_HEAD", "Bee Head", DEFAULT);
        }
    }
}
