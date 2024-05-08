package me.clicks.clicksmod.features.impl

import me.clicks.Config
import me.clicks.Warping.mc
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.concurrent.Executors

/**
 * @author Clicks
 */
object TransferCooldown {

    private val executor = Executors.newSingleThreadExecutor()

    @Volatile var isTaskScheduled = false
        private set

    private val transferCooldownEndedEvent = mutableListOf<() -> Unit>()

    fun registerListener(listener: () -> Unit) {
        transferCooldownEndedEvent.add(listener)
    }

    @SubscribeEvent
    fun onWorldLoad(event: WorldEvent.Load) {
        mc.theWorld ?: return
        mc.thePlayer ?: return
        mc.thePlayer.inventory ?: return
        if (!Config.enabled) return
        synchronized(this) {
            if (!isTaskScheduled) {
                executor.submit {
                    isTaskScheduled = true
                    Thread.sleep(Config.transferCooldownDelay.toLong())
                    if (Config.transferCooldownEndedMessage) {
                        // [Warping] Player Transfer Cooldown has ended.
                        mc.thePlayer.addChatMessage(ChatComponentText("${EnumChatFormatting.GRAY}[${EnumChatFormatting.DARK_PURPLE}Warping${EnumChatFormatting.GRAY}] ${EnumChatFormatting.GREEN}Player Transfer Cooldown has ended."))
                    }
                    transferCooldownEndedEvent.forEach {
                        it()
                    }
                    transferCooldownEndedEvent.clear()
                    isTaskScheduled = false
                }
            }
        }
    }
}
