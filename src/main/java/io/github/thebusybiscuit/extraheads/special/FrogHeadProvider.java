package io.github.thebusybiscuit.extraheads.special;

import org.bukkit.entity.Frog;
import org.bukkit.entity.LivingEntity;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

public class FrogHeadProvider implements MobHeadProvider {
    final Config cfg;

    static final String COLD = "27bcccc125a4110434a85c40ada039d050f14ef7db34a3444067310f8ce69606";
    static final String TEMPERATE = "1f3e29dd947a177895f6121d2331b65ac3f896fda4bdd1151491e40b804952a7";
    static final String WARM = "1e9312b5b2bab9ad51ea4b6a407d6d390bb5043408757b976a7556898ac43de0";

    public FrogHeadProvider(Config cfg) {
        this.cfg = cfg;
    }


    public String getDescription() {
        if (cfg.getBoolean("options.special.frog-variants")) {
            return "&6Comes in 3 variants!";
        } else {
            return null;
        }
    }

    @Override
    public SlimefunItemStack getHead(LivingEntity target) {
        if (target instanceof Frog) {
            return getHeadByVariant((Frog) target);
        } else {
            return MobHeadUtils.buildHead("FROG_HEAD", "Frog Head", COLD);
        }
    }

    SlimefunItemStack getHeadByVariant(Frog target) {
        if (!cfg.getBoolean("options.special.frog-variants") || target == null || target.getVariant() == null) {
            return MobHeadUtils.buildHead("FROG_HEAD", "Frog Head", COLD);
        }

        switch(target.getVariant()) {
            case COLD:
                return MobHeadUtils.buildHead("FROG_HEAD", "Frog Head (Cold)", COLD);
            case TEMPERATE:
                return MobHeadUtils.buildHead("FROG_HEAD", "Frog Head (Temperate)", TEMPERATE);
            case WARM:
                return MobHeadUtils.buildHead("FROG_HEAD", "Frog Head (Warm)", WARM);
            default:
                return MobHeadUtils.buildHead("FROG_HEAD", "Frog Head", COLD);
        }
    }
}
