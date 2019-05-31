package kay.rpgcombat.capabilities.storages;

import kay.rpgcombat.capabilities.interfaces.IAllies;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import java.util.List;
import java.util.UUID;

public class AlliesStorage implements Capability.IStorage<IAllies> {
        @Override
        public NBTBase writeNBT(Capability<IAllies> capability, IAllies instance, EnumFacing side) {
                NBTTagList uuids = new NBTTagList();
                List<UUID> players = instance.getAllies();

                for (UUID player: players) {
                        uuids.appendTag(new NBTTagString(player.toString()));
                }

                return uuids;
        }

        @Override
        public void readNBT(Capability<IAllies> capability, IAllies instance, EnumFacing side, NBTBase nbt) {
                NetHandlerPlayClient connection = Minecraft.getMinecraft().getConnection();
                NBTTagList nbtlist = (NBTTagList) nbt;

                for(int i = 0; i < nbtlist.tagCount(); i++) {
                        instance.add(UUID.fromString(nbtlist.getStringTagAt(i)));
                }
        }
}
