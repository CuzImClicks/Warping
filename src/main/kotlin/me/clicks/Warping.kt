package me.clicks

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

/**
 * @author Clicks
 */
@Mod(
    modid = "warping",
    name = "Warping",
    version = "0.0.1",
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true,
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
)
object Warping {

    val mc: Minecraft by lazy {
        Minecraft.getMinecraft()
    }


    var gui: GuiScreen? = null // taken from skytils

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {

        arrayOf(
            TransferCooldown,
            this
        ).forEach {
            MinecraftForge.EVENT_BUS.register(it)
        }

        arrayOf(
            HubWrapper,
            IsWrapper,
            WarpWrapper,
            WarpingCommand
        ).forEach {
            ClientCommandHandler.instance.registerCommand(it)
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (gui != null) {
            mc.displayGuiScreen(gui)
            gui = null
        }
    }

}
