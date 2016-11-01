package com.rmpi.nukkit.exampletpa.commands;

import cn.nukkit.Player;
import cn.nukkit.permission.Permission;
import com.rmpi.nukkit.exampletpa.ExampleTPA;
import com.rmpi.nukkit.simplecommand.ParameterDefine;

public class tpaccept {
    public final static String description = "Accepts teleport request";
    public final static String permissionDefault = Permission.DEFAULT_TRUE;

    public static void onCommand(Player sender) {
        Player requester = ExampleTPA.getInstance().getTeleportMgr().peekSender(sender);

        if (ExampleTPA.getInstance().getTeleportMgr().acceptRequest(sender)) {
            sender.sendMessage("The request is accepted.");
            assert requester != null;
            requester.sendMessage("A request is accepted by " + sender.getName() + ".");
        } else {
            sender.sendMessage("There was a problem.");
        }
    }

    public static void onCommand(Player sender, @ParameterDefine(name = "index") int index) {
        Player requester = ExampleTPA.getInstance().getTeleportMgr().peekSender(sender, index);

        if (ExampleTPA.getInstance().getTeleportMgr().acceptRequest(sender, index)) {
            sender.sendMessage("The request is accepted.");
            assert requester != null;
            requester.sendMessage("A request is accepted by " + sender.getName() + ".");
        } else {
            sender.sendMessage("There was a problem.");
        }
    }
}
