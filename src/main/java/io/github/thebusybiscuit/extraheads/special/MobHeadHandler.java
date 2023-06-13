package io.github.thebusybiscuit.extraheads.special;

import javax.annotation.Nullable;

import org.bukkit.entity.LivingEntity;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public abstract class MobHeadHandler implements MobHeadProvider {
    public abstract SlimefunItemStack getHead(@Nullable LivingEntity target);
    public abstract SlimefunItemStack[] getVariantItemStacks();

    public static SlimefunItemStack buildHead(String type, String name, String texture, String... lore) {
        return new SlimefunItemStack(type, texture, "&r" + name, lore);
    }
}
