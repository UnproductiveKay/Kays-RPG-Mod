package kay.rpgcombat.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;
import java.util.UUID;

public class AlliesToClientPacket implements IMessage{
    private BlockPos blockPos;

    public AlliesToClientPacket() {
    }

    /**
     * PACKET:
     *     UPDATE - Update Allies for Player
     */
    public AlliesToClientPacket(List<UUID> uuids) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }


    public static class Handler implements IMessageHandler<AlliesToClientPacket, IMessage> {
        @Override
        public IMessage onMessage(AlliesToClientPacket msg, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(msg,ctx));
            return null;
        }

        /**
         * THIS IS HOW THE Client WILL HANDLE THIS PACKET
         */
        private void handle(AlliesToClientPacket msg, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();

            if (world.isBlockLoaded(msg.blockPos)) {
                Block block = world.getBlockState(msg.blockPos).getBlock();
                playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Hit block: " + block.getRegistryName()), false);
            }
        }
    }
}
