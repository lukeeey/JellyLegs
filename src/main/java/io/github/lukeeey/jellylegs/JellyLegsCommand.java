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
                sender.sendMessage(TextFormat.RED + "You can now take fall damage");
            } else {
                sender.sendMessage(TextFormat.GREEN + "You can no longer take fall damage");
            }
        }
        if (args.length == 1) {
            Player target = sender.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(TextFormat.RED + "Player not found");
                return true;
            }

            plugin.setJellyLegs(target, !plugin.hasJellyLegs(target));

            if (plugin.hasJellyLegs(target)) {
                sender.sendMessage(TextFormat.RED + "Player '" + target.getName() + "' can now take fall damage");
                target.sendMessage(TextFormat.RED + "You can now take fall damage");
            } else {
                sender.sendMessage(TextFormat.GREEN + "Player '" + target.getName() + "' can no longer take fall damage");
                target.sendMessage(TextFormat.GREEN + "You can no longer take fall damage");
            }
        }
        return true;
    }
}
