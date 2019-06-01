package kay.rpgcombat.network;

import kay.rpgcombat.network.packets.AbilityPacket;
import kay.rpgcombat.network.packets.AlliesToClientPacket;
import kay.rpgcombat.network.packets.AlliesToServerPacket;
import kay.rpgcombat.network.packets.InputPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    private static int packetId = 0;

    public static SimpleNetworkWrapper INSTANCE = null;

    public PacketHandler() {}

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        INSTANCE.registerMessage(InputPacket.Handler.class, InputPacket.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(AbilityPacket.Handler.class, AbilityPacket.class, nextID(), Side.SERVER);
        INSTANCE.registerMessage(AlliesToClientPacket.Handler.class, AlliesToClientPacket.class, nextID(), Side.CLIENT);
        INSTANCE.registerMessage(AlliesToServerPacket.Handler.class, AlliesToServerPacket.class, nextID(), Side.SERVER);
    }

    public static void sendTo(EntityPlayerMP playerMP, IMessage toSend) {
        INSTANCE.sendTo(toSend, playerMP);
    }
}
