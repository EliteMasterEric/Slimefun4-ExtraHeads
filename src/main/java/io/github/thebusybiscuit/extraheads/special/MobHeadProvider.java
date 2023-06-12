package io.github.thebusybiscuit.extraheads.special;

import javax.annotation.Nullable;

import org.bukkit.entity.LivingEntity;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

@FunctionalInterface
public interface MobHeadProvider {
    /**
     * Given a living entity, this method should return the ItemStack to drop.
     * @param target The living entity that was killed
     *  If null, return the itemstack to display in the Slimefun guide.
     * @return The entity's head.
     */
    public SlimefunItemStack getHead(@Nullable LivingEntity target);
}
