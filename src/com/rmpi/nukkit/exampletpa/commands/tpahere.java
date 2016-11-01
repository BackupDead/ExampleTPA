package com.rmpi.nukkit.exampletpa.commands;

import cn.nukkit.Player;
import cn.nukkit.permission.Permission;
import com.rmpi.nukkit.exampletpa.ExampleTPA;
import com.rmpi.nukkit.exampletpa.teleport.TeleportDirection;
import com.rmpi.nukkit.exampletpa.teleport.TeleportRequest;
import com.rmpi.nukkit.simplecommand.ParameterDefine;

public class Tpahere {
    public final static String name = "tpahere";
    public final static String description = "Sends spawn request";
    public final static String permissionDefault = Permission.DEFAULT_TRUE;

    public static void onCommand(Player sender, @ParameterDefine(name = "target") Player target) {
        ExampleTPA.getInstance().getTeleportMgr().addRequest(new TeleportRequest(sender, target, TeleportDirection.SPAWN));
        sender.sendMessage("Spawn request is sent to " + target.getName() + ".");
        target.sendMessage("Spawn request is received from " + sender.getName() + ".");
    }
}
