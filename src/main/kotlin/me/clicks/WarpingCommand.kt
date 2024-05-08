package me.clicks

import me.clicks.Warping.mc
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

/**
 * @author Clicks
 */
object WarpingCommand : CommandBase() {
    override fun getCommandName(): String {
        return "warping"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return "/$commandName"
    }

    override fun getRequiredPermissionLevel(): Int {
        return 0
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        mc.displayGuiScreen(Config.gui())
    }
}
