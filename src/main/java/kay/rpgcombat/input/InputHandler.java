package kay.rpgcombat.input;

import kay.rpgcombat.network.PacketHandler;
import kay.rpgcombat.network.packets.InputPacket;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class InputHandler {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent e) {
        if (KeyBindings.ability2.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new InputPacket());
        }
    }
}
