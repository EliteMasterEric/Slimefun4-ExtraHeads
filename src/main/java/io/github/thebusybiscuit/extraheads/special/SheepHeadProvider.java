package io.github.thebusybiscuit.extraheads.special;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Sheep;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

public class SheepHeadProvider implements MobHeadProvider {
    final Config cfg;

    static final String LEGACY = "f31f9ccc6b3e32ecf13b8a11ac29cd33d18c95fc73db8a66c5d657ccb8be70";
    static final String DEFAULT = "292df216ecd27624ac771bacfbfe006e1ed84a79e9270be0f88e9c8791d1ece4";

    static final String BLACK = "634ac5b398cf7c86e3f6f188a5127d8b283d772bf5885c70e0c130805f069950";
    static final String BLUE = "e39efc4b4eadec48576a5700ec812395510327e5d1e7c108fd8abc7796685aa3";
    static final String BROWN = "e5813715c2f34f05649f8fa3eaaa67f1eda5e6f9cf930fa9c2e0412d1f9728e1";
    static final String CYAN = "60558387b6658f5e9dcffc719214b603f603c4b04e708b7aabe75bcae91e804c";
    static final String GRAY = "e6c2a2755b20ddff551a6903f2dc7e61f13ebe39b1d5ca929c87bd8583ec801f";
    static final String GREEN = "5753a8ec32be9c550d1c560acb941edd9e3b73ddbf1586923fb37b220b4553dd";
    static final String LIGHT_BLUE = "c8eb0d17479870b3973e8e001b82dcde22efc9d10c90412c6733a0b136564d1f";
    static final String LIGHT_GRAY = "74a59be620ae8b3ee0dd0fa22c80affed4a0f729295cb8c41e78ee783f4633ad";
    static final String LIME = "1ce4090e1bccf992b36def74a6d7d3972c17db1b75554e2c509271680b8e7974";
    static final String MAGENTA = "fe228b04e9b979a10b70b8db6f3fb199deeb581594a5aa4a7febe948db17228b";
    static final String ORANGE = "4271442d8a37db49f02a94c29352694962b5d0bd6bea05f1d93fe19eb4e7060e";
    static final String PINK = "2e7cf1c58dbb7c3255b94c6043fa8f0d776c134f4d98b81ca31410965f47a25a";
    static final String PURPLE = "343cbdae1f20a79281d3a71adf242a35c8cc58562b415f1120bca9d94b76f254";
    static final String RED = "e0ce5b5ca9165ac77a9c3e3f64df0d3170d5afcf9d5a5575e3f0c0f21e43b83";
    static final String WHITE = "292df216ecd27624ac771bacfbfe006e1ed84a79e9270be0f88e9c8791d1ece4";
    static final String YELLOW = "12a5354c230e861aac72734a4582d1317026454b807ac353fc3a0bd0d8c422ba";

    public SheepHeadProvider(Config cfg) {
        this.cfg = cfg;
    }

    public String getDescription() {
        if (cfg.getBoolean("options.special.sheep-colors")) {
            return "&6Comes in 16 colors!";
        } else {
            return null;
        }
    }

    @Override
    public SlimefunItemStack getHead(LivingEntity target) {
        if (target instanceof Sheep) {
            return getHeadByColor((Sheep) target);
        } else {
            return MobHeadUtils.buildHead("SHEEP_HEAD", "Sheep Head", DEFAULT);
        }
    }

    SlimefunItemStack getHeadByColor(Sheep target) {
        if (!cfg.getBoolean("options.special.sheep-colors") || target == null || target.getColor() == null) {
            return MobHeadUtils.buildHead("SHEEP_HEAD", "Sheep Head", DEFAULT);
        }

        switch(target.getColor()) {
            case BLACK:
                return MobHeadUtils.buildHead("SHEEP_HEAD_BLACK", "Sheep Head (Black)", BLACK);
            case BLUE:
                return MobHeadUtils.buildHead("SHEEP_HEAD_BLUE", "Sheep Head (Blue)", BLUE);
            case BROWN:
                return MobHeadUtils.buildHead("SHEEP_HEAD_BROWN", "Sheep Head (Brown)", BROWN);
            case CYAN:
                return MobHeadUtils.buildHead("SHEEP_HEAD_CYAN", "Sheep Head (Cyan)", CYAN);
            case GRAY:
                return MobHeadUtils.buildHead("SHEEP_HEAD_GRAY", "Sheep Head (Gray)", GRAY);
            case GREEN:
                return MobHeadUtils.buildHead("SHEEP_HEAD_GREEN", "Sheep Head (Green)", GREEN);
            case LIGHT_BLUE:
                return MobHeadUtils.buildHead("SHEEP_HEAD_LIGHT_BLUE", "Sheep Head (Light Blue)", LIGHT_BLUE);
            case LIGHT_GRAY:
                return MobHeadUtils.buildHead("SHEEP_HEAD_LIGHT_GRAY", "Sheep Head (Light Gray)", LIGHT_GRAY);
            case LIME:
                return MobHeadUtils.buildHead("SHEEP_HEAD_LIME", "Sheep Head (Lime)", LIME);
            case MAGENTA:
                return MobHeadUtils.buildHead("SHEEP_HEAD_MAGENTA", "Sheep Head (Magenta)", MAGENTA);
            case ORANGE:
                return MobHeadUtils.buildHead("SHEEP_HEAD_ORANGE", "Sheep Head (Orange)", ORANGE);
            case PINK:
                return MobHeadUtils.buildHead("SHEEP_HEAD_PINK", "Sheep Head (Pink)", PINK);
            case PURPLE:
                return MobHeadUtils.buildHead("SHEEP_HEAD_PURPLE", "Sheep Head (Purple)", PURPLE);
            case RED:
                return MobHeadUtils.buildHead("SHEEP_HEAD_RED", "Sheep Head (Red)", RED);
            case WHITE:
                return MobHeadUtils.buildHead("SHEEP_HEAD_WHITE", "Sheep Head (White)", WHITE);
            case YELLOW:
                return MobHeadUtils.buildHead("SHEEP_HEAD_YELLOW", "Sheep Head (Yellow)", YELLOW);
            default:
                return MobHeadUtils.buildHead("SHEEP_HEAD", "Sheep Head", DEFAULT);
        }
    }
}
