package com.inarosdev;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.inarosdev.commands.RxpCommand;
import com.inarosdev.components.PlayerRPGComponent;
import com.inarosdev.events.GiveXPEvent;
import com.inarosdev.events.LevelUpEvent;
import com.inarosdev.handlers.GiveXPHandler;
import com.inarosdev.handlers.LevelUpHandler;
import com.inarosdev.systems.PlayerJoinSystem;
import com.inarosdev.systems.XPGainSystem;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class ReqXP extends JavaPlugin {

    public ReqXP(@NonNullDecl JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        var registry = getEntityStoreRegistry();

        var rpgType = registry.registerComponent(
                PlayerRPGComponent.class,
                "MiniRPG_PlayerData",
                PlayerRPGComponent.CODEC
        );
        PlayerRPGComponent.setComponentType(rpgType);

        registry.registerSystem(new XPGainSystem());
        registry.registerSystem(new PlayerJoinSystem());

        getEventRegistry().register(GiveXPEvent.class, new GiveXPHandler());
        getEventRegistry().register(LevelUpEvent.class, new LevelUpHandler());

        getCommandRegistry().registerCommand(new RxpCommand());
    }
}