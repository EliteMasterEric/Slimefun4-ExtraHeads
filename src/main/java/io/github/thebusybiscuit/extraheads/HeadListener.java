package io.github.thebusybiscuit.extraheads;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

public class HeadListener implements Listener {

    private final ExtraHeads plugin;

    public HeadListener(ExtraHeads plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onKill(EntityDeathEvent e) {
        if (!plugin.hasHead(e.getEntity())) {
            return;
        }

        double chance = plugin.getCfg().getDouble("chances." + e.getEntityType().toString());
        Player killer = e.getEntity().getKiller();

        if (killer != null && SlimefunUtils.isItemSimilar(killer.getInventory().getItemInMainHand(), SlimefunItems.SWORD_OF_BEHEADING, true)) {
            chance *= plugin.getCfg().getDouble("options.sword-of-beheading-multiplier");
        }

        if (Math.random() * 100 < chance) {
            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), plugin.getHead(e.getEntity()));
        }
    }

}
