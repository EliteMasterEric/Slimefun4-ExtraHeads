package io.github.thebusybiscuit.extraheads.special;

import org.bukkit.entity.Frog;
import org.bukkit.entity.LivingEntity;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

public class FrogHeadHandler extends MobHeadHandler {
    final Config cfg;

    static final String COLD = "27bcccc125a4110434a85c40ada039d050f14ef7db34a3444067310f8ce69606";
    static final String TEMPERATE = "1f3e29dd947a177895f6121d2331b65ac3f896fda4bdd1151491e40b804952a7";
    static final String WARM = "1e9312b5b2bab9ad51ea4b6a407d6d390bb5043408757b976a7556898ac43de0";

    public FrogHeadHandler(Config cfg) {
        this.cfg = cfg;
    }

    @Override
    public SlimefunItemStack getHead(LivingEntity target) {
        if (target instanceof Frog) {
            return getHeadByVariant((Frog) target);
        } else {
            return MobHeadHandler.buildHead("FROG_HEAD", "Frog Head", COLD, getGuideLore());
        }
    }

    @Override
    public SlimefunItemStack[] getVariantItemStacks() {
        return new SlimefunItemStack[] {
            MobHeadHandler.buildHead("FROG_HEAD_COLD", "Frog Head (Cold)", COLD),
            MobHeadHandler.buildHead("FROG_HEAD_TEMPERATE", "Frog Head (Temperate)", TEMPERATE),
            MobHeadHandler.buildHead("FROG_HEAD_WARM", "Frog Head (Warm)", WARM),
        };
    }

    String[] getGuideLore() {
        double dropChance = cfg.getOrSetDefault("chances.FROG", 5.0);
        boolean variantsEnabled = cfg.getOrSetDefault("options.special.frog-variants", true);

        if (variantsEnabled) {
            return new String[] {
                "&7Drop Chance: &e" + dropChance + "%",
                "&6Comes in 3 variants!"
            };
        } else {
            return new String[] {
                "&7Drop Chance: &e" + dropChance + "%"
            };
        }
    }

    SlimefunItemStack getHeadByVariant(Frog target) {
        if (!cfg.getOrSetDefault("options.special.frog-variants", true) || target == null || target.getVariant() == null) {
            return MobHeadHandler.buildHead("FROG_HEAD", "Frog Head", COLD);
        }

        switch(target.getVariant()) {
            case COLD:
                return MobHeadHandler.buildHead("FROG_HEAD_COLD", "Frog Head (Cold)", COLD);
            case TEMPERATE:
                return MobHeadHandler.buildHead("FROG_HEAD_TEMPERATE", "Frog Head (Temperate)", TEMPERATE);
            case WARM:
                return MobHeadHandler.buildHead("FROG_HEAD_WARM", "Frog Head (Warm)", WARM);
            default:
                return MobHeadHandler.buildHead("FROG_HEAD", "Frog Head", COLD);
        }
    }
}
