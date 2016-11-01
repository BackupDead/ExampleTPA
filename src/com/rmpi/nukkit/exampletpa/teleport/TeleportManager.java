package com.rmpi.nukkit.exampletpa.teleport;

import cn.nukkit.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeleportManager {
    private Map<Player, List<TeleportRequest>> requests = new HashMap<>();

    public void addPlayer(Player player) {
        requests.putIfAbsent(player, new ArrayList<>());
    }

    public void delPlayer(Player player) {
        requests.remove(player);
    }

    public void addRequest(TeleportRequest request) {
        requests.putIfAbsent(request.target, new ArrayList<>());
        requests.get(request.target).add(request);
    }

    public Player peekSender(Player sender) {
        if (requests.get(sender) == null)
            return null;
        return peekSender(sender, requests.get(sender).size() - 1);
    }

    public Player peekSender(Player sender, int index) {
        if (requests.get(sender) == null
                || requests.get(sender).size() <= index
                || index < 0)
            return null;
        return requests.get(sender).get(index).sender;
    }

    public boolean acceptRequest(Player sender) {
        if (requests.get(sender) == null)
            return false;
        return acceptRequest(sender, requests.get(sender).size() - 1);
    }

    public boolean acceptRequest(Player sender, int index) {
        if (requests.get(sender) == null
                || requests.get(sender).size() <= index
                || index < 0)
            return false;
        return requests.get(sender).remove(index).execute();
    }

    public boolean denyRequest(Player sender) {
        if (requests.get(sender) == null)
            return false;
        return denyRequest(sender, requests.get(sender).size() - 1);
    }

    public boolean denyRequest(Player sender, int index) {
        if (requests.get(sender) == null
                || requests.get(sender).size() <= index
                || index < 0)
            return false;
        requests.get(sender).remove(index);
        return true;
    }

    public String dump(Player sender) {
        return "Request list:\n"
                + (
                        requests.get(sender) == null
                                ? "" : requests.get(sender)
                                .parallelStream()
                                .map(t -> {
                                    StringBuilder requestDumpBuilder = new StringBuilder();
                                    requestDumpBuilder
                                            .append(t.sender.getName())
                                            .append(" ");

                                    switch (t.direction) {
                                        case TELEPORT:
                                            requestDumpBuilder.append("->");
                                            break;
                                        case SPAWN:
                                            requestDumpBuilder.append("<-");
                                            break;
                                        default:
                                            requestDumpBuilder.append("UNDEFINED");
                                    }

                                    requestDumpBuilder
                                            .append(" ")
                                            .append(t.target.getName());
                                    return requestDumpBuilder.toString();
                                })
                                .collect(Collectors.joining("\n"))
        );
    }
}
