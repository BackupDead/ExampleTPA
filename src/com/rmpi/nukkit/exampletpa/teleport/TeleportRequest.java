package com.rmpi.nukkit.exampletpa.teleport;

import cn.nukkit.Player;

public class TeleportRequest {
    public final Player sender;
    public final Player target;
    public final TeleportDirection direction;

    public TeleportRequest(Player sender, Player target, TeleportDirection direction) {
        this.sender = sender;
        this.target = target;
        this.direction = direction;
    }

    public boolean execute() {
        if (sender.isConnected() && sender.isValid() && target.isConnected() && target.isValid())
            switch (direction) {
                case TELEPORT:
                    return sender.teleport(target);
                case SPAWN:
                    return target.teleport(sender);
                default:
                    return false;
            }
        else
            return false;
    }
}
