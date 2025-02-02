package io.github.thebusybiscuit.extraheads;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.extraheads.special.BeeHeadHandler;
import io.github.thebusybiscuit.extraheads.special.FrogHeadHandler;
import io.github.thebusybiscuit.extraheads.special.MobHeadHandler;
import io.github.thebusybiscuit.extraheads.special.MobHeadProvider;
import io.github.thebusybiscuit.extraheads.special.SheepHeadHandler;
import io.github.thebusybiscuit.extraheads.special.ShulkerHeadHandler;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

public class ExtraHeads extends JavaPlugin implements SlimefunAddon {

    private final Map<EntityType, MobHeadProvider> mobs = new EnumMap<>(EntityType.class);

    private Config cfg;
    private Logger logger;

    private ItemGroup itemGroup;
    private RecipeType recipeType;

    @Override
    public void onEnable() {
        cfg = new Config(this);
        logger = getLogger();

        recipeType = new RecipeType(new NamespacedKey(this, "decapitation"), new CustomItemStack(Material.IRON_SWORD, "&6Kill the specified Mob","Use a Sword of Beheading for better luck"));

        itemGroup = new ItemGroup(
            new NamespacedKey(this, "extra_heads"),
            new CustomItemStack(
                SlimefunUtils.getCustomHead("b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0"),
                "&dMob Heads"
            )
        );

        // Setting up bStats
        new Metrics(this, 5650);

        if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "TheBusyBiscuit/ExtraHeads/master").start();
        }
        
        // Animals
        registerHead(EntityType.COW, "Cow Head", "5d6c6eda942f7f5f71c3161c7306f4aed307d82895f9d2b07ab4525718edc5");
        registerHead(EntityType.CHICKEN, "Chicken Head", "1638469a599ceef7207537603248a9ab11ff591fd378bea4735b346a7fae893");
        registerHead(EntityType.PIG, "Pig Head", "621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4");
        registerHead(EntityType.RABBIT, "Rabbit Head", "ff1559194a175935b8b4fea6614bec60bf81cf524af6f564333c555e657bc");
        registerHeads(EntityType.SHEEP, new SheepHeadHandler(this.getCfg()));
        registerHead(EntityType.TURTLE, "Turtle Head", "0a4050e7aacc4539202658fdc339dd182d7e322f9fbcc4d5f99b5718a");
        registerHead(EntityType.POLAR_BEAR, "Polar Bear Head", "442123ac15effa1ba46462472871b88f1b09c1db467621376e2f71656d3fbc");
        registerHead(EntityType.FOX, "Fox Head", "46cff7a19e683a08e4587ea1457880313d5f341f346ceb5b0551195d810e3");
        registerHeads(EntityType.BEE, new BeeHeadHandler(this.getCfg()));
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20)) {
            registerHead(EntityType.SNIFFER, "Sniffer Head", "3d6c9f43510cb90d24493e07b7cf8ca9f54132d09a257f20b7048022e3b1b707");
        }
        registerHead(EntityType.OCELOT, "Ocelot Head", "5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1");
        
        // Animals (Special)
        registerHead(EntityType.PARROT, "Parrot Head", "a4ba8d66fecb1992e94b8687d6ab4a5320ab7594ac194a2615ed4df818edbc3");
        registerHead(EntityType.CAT, "Cat Head", "2f6ec090ed3ed2dddbb511ee2a12551131019939c062a7761371df85549f9fad");
        registerHead(EntityType.PANDA, "Panda Head", "7818b681cace1c641919f53edadecb142330d089a826b56219138c33b7a5e0db");
        registerHead(EntityType.MUSHROOM_COW, "Red Mooshroom Head", "d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            registerHead(EntityType.AXOLOTL, "Axolotl Head", "5c138f401c67fc2e1e387d9c90a9691772ee486e8ddbf2ed375fc8348746f936");
        }
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            registerHeads(EntityType.FROG, new FrogHeadHandler(this.getCfg()));
            registerHead(EntityType.TADPOLE, "Tadpole", "987035f5352334c2cba6ac4c65c2b9059739d6d0e839c1dd98d75d2e77957847");
        }
        
        // Neutral Mobs
        registerHead(EntityType.BAT, "Bat Head", "2796aa6d18edc5b724bd89e983bc3215a41bf775d112635e9b5835d1b8ad20cb");
        registerHead(EntityType.DOLPHIN, "Dolphin Head", "cefe7d803a45aa2af1993df2544a28df849a762663719bfefc58bf389ab7f5");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            registerHead(EntityType.GOAT, "Goat Head", "457a0d538fa08a7affe312903468861720f9fa34e86d44b89dcec5639265f03");
        }
        registerHead(EntityType.IRON_GOLEM, "Iron Golem Head", "89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714");
        registerHead(EntityType.SQUID, "Squid Head", "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac");
        registerHead(EntityType.STRIDER, "Strider Head", "18a9adf780ec7dd4625c9c0779052e6a15a451866623511e4c82e9655714b3c1");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            registerHead(EntityType.GLOW_SQUID, "Glow Squid Head", "57327ee11812b764c7ade70b282cce4c58e635b2015244081d1490543da7280e");
        }
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            registerHead(EntityType.ALLAY, "Allay Head", "e1c59dccde4b8535500dcf6794ca450663f607290e2510f6d8eb1e5eb71da5af");
        }
        
        // Horse Mobs
        registerHead(EntityType.HORSE, "Horse Head", "61902898308730c4747299cb5a5da9c25838b1d059fe46fc36896fee662729");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_20)) {
            registerHead(EntityType.CAMEL, "Camel Head", "74b8a333dfa92e7e5a95ad4ae2d84b1bafa33dc28c054925277f60e79dafc8c4");
        }
        registerHead(EntityType.LLAMA, "Llama Head", "2a5f10e6e6232f182fe966f501f1c3799d45ae19031a1e4941b5dee0feff059b");
        registerHead(EntityType.TRADER_LLAMA, "Trader Llama Head", "15ad6b69cc6b4769d3516a0ce98b99b2a5d406fea4912dec570ea4a4f2bcc0ff");
        
        // Villager Mobs
        registerHead(EntityType.VILLAGER, "Villager Head", "822d8e751c8f2fd4c8942c44bdb2f5ca4d8ae8e575ed3eb34c18a86e93b");
        registerHead(EntityType.WANDERING_TRADER, "Wandering Trader Head", "5f1379a82290d7abe1efaabbc70710ff2ec02dd34ade386bc00c930c461cf932");
        
        // Illager Mobs
        registerHead(EntityType.EVOKER, "Evoker Head", "d954135dc82213978db478778ae1213591b93d228d36dd54f1ea1da48e7cba6");
        registerHead(EntityType.ILLUSIONER, "Illusioner Head", "2f2882dd09723e47c0ab9663eab083d6a5969273706110c82910e61bf8a8f07e");
        registerHead(EntityType.PILLAGER, "Pillager Head", "4aee6bb37cbfc92b0d86db5ada4790c64ff4468d68b84942fde04405e8ef5333");
        registerHead(EntityType.RAVAGER, "Ravager Head", "1cb9f139f9489d86e410a06d8cbc670c8028137508e3e4bef612fe32edd60193");
        registerHead(EntityType.VEX, "Vex Head", "c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd");
        registerHead(EntityType.VINDICATOR, "Vindicator Head", "6deaec344ab095b48cead7527f7dee61b063ff791f76a8fa76642c8676e2173");
        registerHead(EntityType.ZOMBIE_VILLAGER, "Zombie Villager Head", "a6224941314bca2ebbb66b10ffd94680cc98c3435eeb71a228a08fd42c24db");
        
        // Hostile Mobs
        registerHead(EntityType.BLAZE, "Blaze Head", "b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0");
        registerHead(EntityType.DROWNED, "Drowned Head", "c84df79c49104b198cdad6d99fd0d0bcf1531c92d4ab6269e40b7d3cbbb8e98c");
        registerHead(EntityType.ENDERMAN, "Enderman Head", "7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf");
        registerHead(EntityType.GHAST, "Ghast Head", "8b6a72138d69fbbd2fea3fa251cabd87152e4f1c97e5f986bf685571db3cc0");
        registerHead(EntityType.GUARDIAN, "Guardian Head", "932c24524c82ab3b3e57c2052c533f13dd8c0beb8bdd06369bb2554da86c123");
        registerHead(EntityType.HUSK, "Husk Head", "d674c63c8db5f4ca628d69a3b1f8a36e29d8fd775e1a6bdb6cabb4be4db121");
        registerHead(EntityType.MAGMA_CUBE, "Magma Cube Head", "38957d5023c937c4c41aa2412d43410bda23cf79a9f6ab36b76fef2d7c429");
        registerHeads(EntityType.SHULKER, new ShulkerHeadHandler(this.getCfg()));
        registerHead(EntityType.SLIME, "Slime Head", "16ad20fc2d579be250d3db659c832da2b478a73a698b7ea10d18c9162e4d9b5");
        registerHead(EntityType.SPIDER, "Spider Head", "cd541541daaff50896cd258bdbdd4cf80c3ba816735726078bfe393927e57f1");
        registerHead(EntityType.CAVE_SPIDER, "Cave Spider Head", "41645dfd77d09923107b3496e94eeb5c30329f97efc96ed76e226e98224");
        registerHead(EntityType.STRAY, "Stray Head", "78ddf76e555dd5c4aa8a0a5fc584520cd63d489c253de969f7f22f85a9a2d56");
        registerHead(EntityType.WITCH, "Witch Head", "ddedbee42be472e3eb791e7dbdfaf18c8fe593c638ba1396c9ef68f555cbce");
        
        // Piglin Mobs
        if (Slimefun.getMinecraftVersion().isBefore(MinecraftVersion.MINECRAFT_1_20)) {
            // After 1.20, Piglins now have their own head.
            registerHead(EntityType.PIGLIN, "Piglin Head", "11d18bbd0d795b9ac8efaad655e3d0c59fcbb9b964c2a9948ef537f4a3fbbf87");
        }
        registerHead(EntityType.ZOMBIFIED_PIGLIN, "Zombified Piglin Head", "e935842af769380f78e8b8a88d1ea6ca2807c1e5693c2cf797456620833e936f");
        registerHead(EntityType.PIGLIN_BRUTE, "Piglin Brute Head", "3e300e9027349c4907497438bac29e3a4c87a848c50b34c21242727b57f4e1cf");
        registerHead(EntityType.HOGLIN, "Hoglin Head", "9bb9bc0f01dbd762a08d9e77c08069ed7c95364aa30ca1072208561b730e8d75");
        registerHead(EntityType.ZOGLIN, "Zoglin Head", "e67e18602e03035ad68967ce090235d8996663fb9ea47578d3a7ebbc42a5ccf9");

        // Boss Mobs
        registerHead(EntityType.ELDER_GUARDIAN, "Elder Guardian Head", "4adc4a6f53afa116027b51d6f2e433ee7afa5d59b2ffa04780be464fa5d61a");
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
            registerHead(EntityType.WARDEN, "Warden Head", "c6f74361fb00490a0a98eeb814544ecdd775cb55633dbb114e60d27004cb1020");
        }
        registerHead(EntityType.WITHER, "Wither Head", "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f");
        
        cfg.save();

        new HeadListener(this);
    }

    /**
     * Register a custom mob head using a standard handler.
     */
    private void registerHead(EntityType type, String name, String texture) {
        try {
            double chance = cfg.getOrSetDefault("chances." + type.toString(), 5.0);
            SlimefunItemStack item = new SlimefunItemStack(type.toString() + "_HEAD", texture, "&r" + name, "&7Drop Chance: &e" + chance + "%");
            MobHead baseItem = new MobHead(itemGroup, item, recipeType, new CustomItemStack(item, "&rKill 1 " + ChatUtils.humanize(type.name()), "&7Chance: &e" + chance + "%"));
            baseItem.register(this, () -> mobs.put(type, (entity) -> baseItem.getCleanItem()));
        }
        catch (Exception x) {
            logger.log(Level.WARNING, x, () -> "Could not load Mob Head for Entity: " + type);
        }
    }

    /**
     * Register a custom mob head using a custom handler.
     */
    private void registerHeads(EntityType type, MobHeadHandler provider) {
        try {
            SlimefunItemStack item = provider.getHead(null);
            String[] itemDescription = item.getItemMeta().getLore().toArray(new String[0]);
            MobHead baseItem = new MobHead(itemGroup, item, recipeType, new CustomItemStack(item, "&rKill 1 " + ChatUtils.humanize(type.name()), itemDescription));
            baseItem.register(this, () -> mobs.put(type, provider));

            // Register variants, but keep them hidden.
            for (SlimefunItemStack variantItemStack : provider.getVariantItemStacks()) {
                MobHead variantItem = new MobHead(itemGroup, variantItemStack, recipeType, variantItemStack);
                variantItem.register(this, () -> {});
                variantItem.setHidden(true);
            }
        }
        catch (Exception x) {
            logger.log(Level.WARNING, x, () -> "Could not load Mob Head for Entity: " + type);
        }
    }

    public boolean hasHead(LivingEntity entity) {
        return mobs.containsKey(entity.getType());
    }

    public ItemStack getHead(LivingEntity entity) {
        return mobs.get(entity.getType()).getHead(entity);
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
