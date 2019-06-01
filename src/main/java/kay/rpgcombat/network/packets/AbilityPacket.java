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

public class AbilityPacket implements IMessage{
    private BlockPos blockPos;

    //blank
    //public AbilityPacket() {
    //}

    /**
     * PACKET TO SEND KEYBOARD INPUT FROM KEYBINDS
     */
    public AbilityPacket() {

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }


    public static class Handler implements IMessageHandler<kay.rpgcombat.network.packets.AbilityPacket, IMessage> {
        @Override
        public IMessage onMessage(kay.rpgcombat.network.packets.AbilityPacket msg, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(msg,ctx));
            return null;
        }

        /**
         * THIS IS HOW THE SERVER WILL HANDLE THIS PACKET
         */
        private void handle(kay.rpgcombat.network.packets.AbilityPacket msg, MessageContext ctx) {
            EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();

            if (world.isBlockLoaded(msg.blockPos)) {
                Block block = world.getBlockState(msg.blockPos).getBlock();
                playerEntity.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Hit block: " + block.getRegistryName()), false);
            }
        }
    }
}
