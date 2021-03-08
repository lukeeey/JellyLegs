package io.github.lukeeey.jellylegs;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.plugin.PluginBase;
import io.github.lukeeey.jellylegs.event.JellyLegsToggleEvent;

import java.util.HashSet;
import java.util.Set;

public class JellyLegsPlugin extends PluginBase implements Listener {
    private final Set<Player> jellyLegsEnabled = new HashSet<>();

    @Override
    public void onEnable() {
        getServer().getCommandMap().register("jellylegs", new JellyLegsCommand(this));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (hasJellyLegs(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public boolean hasJellyLegs(Player player) {
        return jellyLegsEnabled.contains(player);
    }

    public void setJellyLegs(Player player, boolean value) {
        setJellyLegs(player, value, true);
    }

    public void setJellyLegs(Player player, boolean value, boolean fireEvent) {
        if (value) {
            jellyLegsEnabled.add(player);
        } else {
            jellyLegsEnabled.remove(player);
        }
        if (fireEvent) {
            getServer().getPluginManager().callEvent(new JellyLegsToggleEvent(player, value));
        }
    }
}
