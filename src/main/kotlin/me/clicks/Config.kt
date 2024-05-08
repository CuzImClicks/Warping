package me.clicks

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

/**
 * @author Clicks
 */
object Config : Vigilant(File("./config/warping.toml")) {

    @Property(
        category = "General",
        name = "Enabled",
        description = "Enable Warping",
        type = PropertyType.SWITCH
    )
    var enabled = true

    @Property(
        category = "General",
        name = "Transfer Cooldown",
        description = "Default is 3000 ms",
        type = PropertyType.NUMBER,
        min = 0,
        max = 4000,
        increment = 50
    )
    var transferCooldownDelay = 3000

    @Property(
        category = "General",
        name = "Transfer Cooldown Ended Message",
        description = "Message to display when transfer cooldown ends",
        type = PropertyType.SWITCH
    )
    var transferCooldownEndedMessage = true

    init {
        initialize()

        markDirty()
    }
}
