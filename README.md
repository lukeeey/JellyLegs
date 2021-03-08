# JellyLegs
![JellyLegs](https://github.com/lukeeey/JellyLegs/workflows/JellyLegs/badge.svg)
[![Discord](https://img.shields.io/discord/803794932820082739.svg?color=%237289da&label=Discord)](https://discord.gg/pXz2rGJ8FA)  

A Nukkit plugin allows you to disable taking fall damage! Great for a donator perk.

## Permissions
| Permission | Default |
| ---------- | ------- |
| jellyllegs.* | false |
| jellylegs.toggle | op |
| jellylegs.toggle.others | op |

## For Developers
### Events
There is a single `JellyLegsToggleEvent` that is fired when a player toggles their JellyLegs status.
The event is cancellable.

### API
In `JellyLegsPlugin` there are 2 methods for accessing or changing a players JellyLegs status.

Here is a full example:
```java
JellyLegsPlugin jellyLegs = getServer().getPluginManager().getPlugin("JellyLegs");

jellyLegs.setJellyLegs(player, true);

if (jellyLegs.hasJellyLegs(player)) {
    player.sendMessage("You have Jelly Legs!");
}
```