package com.inarosdev.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class RxpCommand extends AbstractCommandCollection {

    public RxpCommand() {
        super("rxp", "RXP Debug Commands");
        addSubCommand(new RxpSpawnCommand());
        addSubCommand(new RxpXpCommand());
        addSubCommand(new RxpStatsCommand());
    }
}