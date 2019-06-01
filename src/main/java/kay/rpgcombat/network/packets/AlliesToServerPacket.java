package kay.rpgcombat.network.packets;

import io.netty.buffer.ByteBuf;
import kay.rpgcombat.capabilities.interfaces.IAllies;
import kay.rpgcombat.capabilities.providers.AlliesProvider;
import kay.rpgcombat.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlliesToServerPacket implements IMessage{
    private List<UUID> uuids = new ArrayList();

    public AlliesToServerPacket() {}

    /**
     * PACKET TO UPDATE ALLIES
     */
    public AlliesToServerPacket(IAllies a) {
        uuids = a.getRequestsTo();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();

        for (int i = 0; i < length; i++) {
            uuids.add(UUID.fromString(ByteBufUtils.readUTF8String(buf)));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(uuids.size());

        for (UUID uuid: uuids) {
            ByteBufUtils.writeUTF8String(buf, uuid.toString());
        }
    }


    public static class Handler implements IMessageHandler<AlliesToServerPacket, IMessage> {
        @Override
        public IMessage onMessage(AlliesToServerPacket msg, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(msg,ctx));
            return null;
        }

        /**
         * THIS IS HOW THE SERVER WILL HANDLE THIS PACKET
         */
        private void handle(AlliesToServerPacket msg, MessageContext ctx) {
            List<UUID> requestedUuids = msg.uuids;

            for (UUID uuid: requestedUuids) {
                EntityPlayerMP playerRequested = ctx.getServerHandler().player.mcServer.getPlayerList().getPlayerByUUID(uuid);

                if (playerRequested != null)
                    PacketHandler.sendTo(playerRequested, new AlliesToClientPacket());
            }
        }
    }
}