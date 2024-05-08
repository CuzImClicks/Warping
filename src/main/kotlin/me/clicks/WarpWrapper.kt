package me.clicks

import me.clicks.Warping.mc
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

/**
 * @author Clicks
 */
object WarpWrapper : CommandBase() {
    override fun getCommandName(): String {
        return "warp"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return "/$commandName <destination>"
    }

    override fun getRequiredPermissionLevel(): Int {
        return 0
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        if (TransferCooldown.isTaskScheduled  && args?.size == 1) {
            TransferCooldown.registerListener {
                mc.thePlayer.sendChatMessage("/warp ${args[0]}")
            }
            return
        }
        mc.thePlayer.sendChatMessage("/warp ${args?.get(0) ?: ""}")
    }
}
