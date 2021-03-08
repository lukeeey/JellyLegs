package io.github.lukeeey.jellylegs;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;

public class JellyLegsCommand extends Command {
    private final JellyLegsPlugin plugin;

    public JellyLegsCommand(JellyLegsPlugin plugin) {
        super("jellylegs", "Toggle fall damage", "/jellylegs [player]", new String[]{"jelly"});
        this.setPermission("jellylegs.toggle;jellylegs.toggle.others;jellylegs.*");
        this.plugin = plugin;
        this.commandParameters.put("default", new CommandParameter[]{
                new CommandParameter("player", CommandParamType.TARGET, true)
        });
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!testPermission(sender)) {
            return true;
        }
        if (args.length > 1) {
            sender.sendMessage(getUsage());
            return true;
        }
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(TextFormat.RED + "This command must be executed in-game");
                sender.sendMessage(TextFormat.GOLD + "Try /jellylegs <player name>");
                return true;
            }
            Player player = (Player) sender;

            plugin.setJellyLegs(player, !plugin.hasJellyLegs(player));

            if (plugin.hasJellyLegs(player)) {
                sender.sendMessage(plugin.getMessage("fall-damage-disabled"));
            } else {
                sender.sendMessage(plugin.getMessage("fall-damage-enabled"));
            }
        }
        if (args.length == 1) {
            Player target = sender.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(plugin.getMessage("player-not-found"));
                return true;
            }

            plugin.setJellyLegs(target, !plugin.hasJellyLegs(target));

            if (plugin.hasJellyLegs(target)) {
                sender.sendMessage(plugin.getMessage("fall-damage-disabled-other").replace("{player}", target.getName()));
                target.sendMessage(plugin.getMessage("fall-damage-disabled"));
            } else {
                sender.sendMessage(plugin.getMessage("fall-damage-enabled-other").replace("{player}", target.getName()));
                target.sendMessage(plugin.getMessage("fall-damage-enabled"));
            }
        }
        return true;
    }
}
