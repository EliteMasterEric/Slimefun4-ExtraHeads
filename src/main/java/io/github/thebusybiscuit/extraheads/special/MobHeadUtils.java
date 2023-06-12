package io.github.thebusybiscuit.extraheads.special;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public class MobHeadUtils {
    public static SlimefunItemStack buildHead(String type, String name, String texture, String... lore) {
        return new SlimefunItemStack(type, texture, "&r" + name, lore);
    }
}
