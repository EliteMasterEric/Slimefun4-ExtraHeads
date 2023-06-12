package io.github.thebusybiscuit.extraheads.special;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Shulker;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

public class ShulkerHeadProvider implements MobHeadProvider {
    final Config cfg;

    public static final String DESCRIPTION = "&6Comes in 16 additional colors!";

    static final String LEGACY = "b1d3534d21fe8499262de87affbeac4d25ffde35c8bdca069e61e1787ff2f";
    static final String DEFAULT = "537a294f6b7b4ba437e5cb35fb20f46792e7ac0a490a66132a557124ec5f997a";

    static final String BLACK = "c5455aaadb2317b5f29e98981aa57f5795705069d8f415c0d68a92a791413b3a";
    static final String BLUE = "9430966d5cebbd787147699a29743751b3ce4bb814e2db564fe92142d119cd1";
    static final String BROWN = "c96d7fb87447ffc054fb109b84d6225d41029b1e6710c7de57f661aefa6f";
    static final String CYAN = "26f173d4e3e8bd6f9be0bf9b745bd1cc7a29ff836ed2d486c5b99292c85cc";
    static final String GRAY = "a798432fbeb2bc2d757b1d3c3b3558e6990392dd091ea4ef381b2e019c9462";
    static final String GREEN = "331abe2bb2b9141f1018eb1539d2cfec13d9231b53a46caf8dc21aa2705049";
    static final String LIGHT_BLUE = "c21cdec2cf4ebeef35d58b184b832598bc890a0ae5c2d54ee9be586d0";
    static final String LIGHT_GRAY = "bfa17d41ea183bee53d546c7bec4ccf6a54d4f508fde6ebf3e5d388d4cbeacb9";
    static final String LIME = "17144957c2c5bbfe447f4b2d36a246ea1b023da4cbd1aa2dbbb15e94981248";
    static final String MAGENTA = "69fe291676c7b3f96f3cdf63c47b53fb45d73bd6fe2ce22de10749eb1426a";
    static final String ORANGE = "f5711d954bd56236d1a9f99be880c5d38990df6bef72e73f745b04995dbf6";
    static final String PINK = "3f847c12d57fe6555e8f9b47e562ecf1683fb6c35f92ce2ced2ae68de628750";
    static final String PURPLE = "1e73832e272f8844c476846bc424a3432fb698c58e6ef2a9871c7d29aeea7";
    static final String RED = "59994040433af0f015be4d6968c3d55e044c98dac2c4c6a6ea0efac7a6ddb";
    static final String WHITE = "6b94b0acb3177b4cdb017fe31cd5c247262def53bf83381c6c82d72c56ac";
    static final String YELLOW = "8cbf5586836b7b342932e1d23efc2490cf59c69accf1e05e9ed576caed8b7877";

    public ShulkerHeadProvider(Config cfg) {
        this.cfg = cfg;
    }

    @Override
    public SlimefunItemStack getHead(LivingEntity target) {
        if (target instanceof Shulker) {
            return getHeadByColor((Shulker) target);
        } else {
            return MobHeadUtils.buildHead("SHULKER_HEAD", "Shulker Head", DEFAULT, getGuideLore());
        }
    }

    String[] getGuideLore() {
        double dropChance = cfg.getOrSetDefault("chances.SHULKER", 5.0);
        boolean variantsEnabled = cfg.getOrSetDefault("options.special.shulker-variants", true);

        if (variantsEnabled) {
            return new String[] {
                "&7Drop Chance: &e" + dropChance + "%",
                "&6Comes in 16 additional colors!"
            };
        } else {
            return new String[] {
                "&7Drop Chance: &e" + dropChance + "%"
            };
        }
    }

    SlimefunItemStack getHeadByColor(Shulker target) {
        if (!cfg.getOrSetDefault("options.special.shulker-colors", true) || target == null || target.getColor() == null) {
            return MobHeadUtils.buildHead("SHULKER_HEAD", "Shulker Head", DEFAULT);
        }

        switch(target.getColor()) {
            case BLACK:
                return MobHeadUtils.buildHead("SHULKER_HEAD_BLACK", "Shulker Head (Black)", BLACK);
            case BLUE:
                return MobHeadUtils.buildHead("SHULKER_HEAD_BLUE", "Shulker Head (Blue)", BLUE);
            case BROWN:
                return MobHeadUtils.buildHead("SHULKER_HEAD_BROWN", "Shulker Head (Brown)", BROWN);
            case CYAN:
                return MobHeadUtils.buildHead("SHULKER_HEAD_CYAN", "Shulker Head (Cyan)", CYAN);
            case GRAY:
                return MobHeadUtils.buildHead("SHULKER_HEAD_GRAY", "Shulker Head (Gray)", GRAY);
            case GREEN:
                return MobHeadUtils.buildHead("SHULKER_HEAD_GREEN", "Shulker Head (Green)", GREEN);
            case LIGHT_BLUE:
                return MobHeadUtils.buildHead("SHULKER_HEAD_LIGHT_BLUE", "Shulker Head (Light Blue)", LIGHT_BLUE);
            case LIGHT_GRAY:
                return MobHeadUtils.buildHead("SHULKER_HEAD_LIGHT_GRAY", "Shulker Head (Light Gray)", LIGHT_GRAY);
            case LIME:
                return MobHeadUtils.buildHead("SHULKER_HEAD_LIME", "Shulker Head (Lime)", LIME);
            case MAGENTA:
                return MobHeadUtils.buildHead("SHULKER_HEAD_MAGENTA", "Shulker Head (Magenta)", MAGENTA);
            case ORANGE:
                return MobHeadUtils.buildHead("SHULKER_HEAD_ORANGE", "Shulker Head (Orange)", ORANGE);
            case PINK:
                return MobHeadUtils.buildHead("SHULKER_HEAD_PINK", "Shulker Head (Pink)", PINK);
            case PURPLE:
                return MobHeadUtils.buildHead("SHULKER_HEAD_PURPLE", "Shulker Head (Purple)", PURPLE);
            case RED:
                return MobHeadUtils.buildHead("SHULKER_HEAD_RED", "Shulker Head (Red)", RED);
            case WHITE:
                return MobHeadUtils.buildHead("SHULKER_HEAD_WHITE", "Shulker Head (White)", WHITE);
            case YELLOW:
                return MobHeadUtils.buildHead("SHULKER_HEAD_YELLOW", "Shulker Head (Yellow)", YELLOW);
            default:
                return MobHeadUtils.buildHead("SHULKER_HEAD", "Shulker Head", DEFAULT);
        }
    }
}
