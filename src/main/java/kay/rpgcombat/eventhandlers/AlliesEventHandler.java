package kay.rpgcombat.eventhandlers;

import kay.rpgcombat.capabilities.interfaces.IAllies;
import kay.rpgcombat.capabilities.providers.AlliesProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class AlliesEventHandler {
    @SubscribeEvent
    public void onPlayerLogsIn(PlayerEvent.PlayerLoggedInEvent e) {
        EntityPlayer p = e.player;
        IAllies allies = p.getCapability(AlliesProvider.ALLIES_CAPABILITY, null);
    }


}
