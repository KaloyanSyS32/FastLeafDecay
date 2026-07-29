package com.im4theone.fastleafdecay;

import org.bukkit.plugin.java.JavaPlugin;

public final class FastLeafDecay extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LeafBreakListener(this), this);
        getLogger().info("FastLeafDecay enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FastLeafDecay disabled.");
    }
}