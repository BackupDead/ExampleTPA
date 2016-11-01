package com.rmpi.nukkit.exampletpa;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.plugin.PluginBase;

import com.rmpi.nukkit.exampletpa.commands.*;
import com.rmpi.nukkit.exampletpa.teleport.TeleportManager;
import com.rmpi.nukkit.simplecommand.CommandClassCorruptedException;
import com.rmpi.nukkit.simplecommand.CommandRegisterer;

public class ExampleTPA extends PluginBase implements Listener {
    private static ExampleTPA instance;

    private TeleportManager teleportMgr;

    @Override
    public void onEnable() {
        getLogger().info(getClass().getSimpleName() + " is enabled.");
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);
        teleportMgr = new TeleportManager();

        try {
            CommandRegisterer.register(Tpa.class);
            CommandRegisterer.register(Tpahere.class);
            CommandRegisterer.register(Tpaccept.class);
            CommandRegisterer.register(Tpdeny.class);
            CommandRegisterer.register(Tplist.class);
        } catch (CommandClassCorruptedException e) {
            getLogger().error("An error occured while registering commands.", e);
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info(getClass().getSimpleName() + " is disabled.");
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        teleportMgr.addPlayer(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        teleportMgr.delPlayer(event.getPlayer());
    }

    public TeleportManager getTeleportMgr() {
        return teleportMgr;
    }

    public static ExampleTPA getInstance() {
        return instance;
    }
}
