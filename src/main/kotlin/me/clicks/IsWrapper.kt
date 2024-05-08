package me.clicks

import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

/**
 * @author Clicks
 */
object IsWrapper : CommandBase() {
    override fun getCommandName(): String {
        return "is"
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
                Warping.mc.thePlayer.sendChatMessage("/is")
            }
            return
        }
        Warping.mc.thePlayer.sendChatMessage("/is")
    }
}
