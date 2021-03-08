package io.github.lukeeey.jellylegs.event;

import cn.nukkit.Player;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import lombok.Data;
import lombok.Getter;

@Data
public class JellyLegsToggleEvent extends Event {
    @Getter
    private static final HandlerList handlers = new HandlerList();

    private final Player player;
    private final boolean enabled;
}
