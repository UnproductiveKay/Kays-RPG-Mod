package kay.rpgcombat.capabilities;

import kay.rpgcombat.RPGCombat;
import kay.rpgcombat.capabilities.providers.AlliesProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {
    public static final ResourceLocation ALLIES_CAPABILITY = new ResourceLocation(RPGCombat.MODID, "allies");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(ALLIES_CAPABILITY, new AlliesProvider());
    }
}
