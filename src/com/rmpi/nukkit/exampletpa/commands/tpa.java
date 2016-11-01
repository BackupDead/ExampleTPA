package com.rmpi.nukkit.exampletpa.commands;

import cn.nukkit.Player;
import cn.nukkit.permission.Permission;
import com.rmpi.nukkit.exampletpa.ExampleTPA;
import com.rmpi.nukkit.exampletpa.teleport.TeleportDirection;
import com.rmpi.nukkit.exampletpa.teleport.TeleportRequest;
import com.rmpi.nukkit.simplecommand.ParameterDefine;

public class tpa {
    public final static String description = "Sends teleport request";
    public final static String permissionDefault = Permission.DEFAULT_TRUE;

    public static void onCommand(Player sender, @ParameterDefine(name = "target") Player target) {
        ExampleTPA.getInstance().getTeleportMgr().addRequest(new TeleportRequest(sender, target, TeleportDirection.TELEPORT));
        sender.sendMessage("Teleport request is sent to " + target.getName() + ".");
        target.sendMessage("Teleport request is received from " + sender.getName() + ".");
    }
}
