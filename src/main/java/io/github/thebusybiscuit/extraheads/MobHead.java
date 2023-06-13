package io.github.thebusybiscuit.extraheads;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class MobHead extends SlimefunItem {
    private Runnable runnable;

    private SlimefunItemStack displayItem;
    private SlimefunItemStack dropItem;

    public MobHead(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack recipe) {
        super(itemGroup, item, recipeType, new ItemStack[] { null, null, null, null, recipe, null, null, null, null });
        displayItem = item;

        dropItem = (SlimefunItemStack) displayItem.clone();
        ItemMeta cleanMeta = dropItem.getItemMeta();
        cleanMeta.setLore(null);
        dropItem.setItemMeta(cleanMeta);
    }

    public void register(ExtraHeads plugin, Runnable runnable) {
        this.runnable = runnable;
        register(plugin);
    }

    @Override
    public void postRegister() {
        super.postRegister();

        if (!isDisabled()) {
            runnable.run();
        }
    }

    public SlimefunItemStack getCleanItem() {
        return (SlimefunItemStack) dropItem.clone();
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return Arrays.asList(getCleanItem());
    }

    @Override
    public Collection<ItemStack> getDrops(Player p) {
        return Arrays.asList(getCleanItem());
    }
}
