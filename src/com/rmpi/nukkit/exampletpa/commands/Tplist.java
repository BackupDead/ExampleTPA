package com.rmpi.nukkit.exampletpa.commands;

import cn.nukkit.Player;
import cn.nukkit.permission.Permission;
import com.rmpi.nukkit.exampletpa.ExampleTPA;

public class Tplist {
    public final static String name = "tplist";
    public final static String description = "Dumps current teleport requests in inbox";
    public final static String permissionDefault = Permission.DEFAULT_TRUE;

    public static void onCommand(Player sender) {
        sender.sendMessage(ExampleTPA.getInstance().getTeleportMgr().dump(sender));
    }
}
