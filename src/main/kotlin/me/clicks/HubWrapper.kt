package me.clicks

import me.clicks.Warping.mc
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

/**
 * @author Clicks
 */
object HubWrapper : CommandBase() {
    override fun getCommandName(): String {
        return "hub"
    }

    override fun getCommandUsage(sender: ICommandSender?): String {
        return "/$commandName"
    }

    override fun getRequiredPermissionLevel(): Int {
        return 0
    }

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        if (TransferCooldown.isTaskScheduled) {
            TransferCooldown.registerListener {
                mc.thePlayer.sendChatMessage("/hub")
            }
            return
        }
        mc.thePlayer.sendChatMessage("/hub")
    }
}
