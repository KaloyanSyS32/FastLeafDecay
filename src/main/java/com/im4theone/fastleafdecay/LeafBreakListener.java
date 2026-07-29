package com.im4theone.fastleafdecay;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeafBreakListener implements Listener {

    private final FastLeafDecay plugin;

    public LeafBreakListener(FastLeafDecay plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        // Check if broken block is a log
        if (Tag.LOGS.isTagged(block.getType())) {
            scheduleLeafDecayAround(block);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onLeavesDecay(LeavesDecayEvent event) {
        // Chain the decay so neighboring leaves also decay fast
        scheduleLeafDecayAround(event.getBlock());
    }

    private void scheduleLeafDecayAround(Block center) {
        List<Block> leavesToProcess = new ArrayList<>();

        // Scan 5x5x5 area around broken block
        int radius = 2;
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block neighbor = center.getRelative(x, y, z);

                    if (Tag.LEAVES.isTagged(neighbor.getType())) {
                        Leaves leavesData = (Leaves) neighbor.getBlockData();

                        // Only decay leaves that are NOT persistent (placed by players)
                        if (!leavesData.isPersistent() && leavesData.getDistance() > 6) {
                            leavesToProcess.add(neighbor);
                        }
                    }
                }
            }
        }

        if (leavesToProcess.isEmpty()) {
            return;
        }

        // Shuffle so decay pattern looks natural rather than blocky
        Collections.shuffle(leavesToProcess);

        // Schedule decay across consecutive ticks
        for (int i = 0; i < leavesToProcess.size(); i++) {
            Block leafBlock = leavesToProcess.get(i);
            long delay = (i / 4L) + 1L; // Process 4 leaves per tick

            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                if (Tag.LEAVES.isTagged(leafBlock.getType())) {
                    Leaves leavesData = (Leaves) leafBlock.getBlockData();
                    if (!leavesData.isPersistent()) {
                        // Break leaf block naturally (triggers standard drops like apples, saplings)
                        leafBlock.breakNaturally();
                    }
                }
            }, delay);
        }
    }
}