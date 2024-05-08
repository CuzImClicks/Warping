package me.clicks

import me.clicks.clicksmod.features.impl.TransferCooldown
import net.minecraft.client.Minecraft
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

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

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(TransferCooldown)

        arrayOf(
            HubWrapper,
            IsWrapper,
            WarpWrapper,
            WarpingCommand
        ).forEach {
            ClientCommandHandler.instance.registerCommand(it)
        }
    }

}
