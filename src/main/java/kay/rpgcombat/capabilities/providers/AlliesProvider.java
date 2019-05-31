package kay.rpgcombat.capabilities.providers;

import kay.rpgcombat.capabilities.interfaces.IAllies;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AlliesProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IAllies.class)
    public static final Capability<IAllies> ALLIES_CAPABILITY = null;

    private IAllies instance = ALLIES_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == ALLIES_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == ALLIES_CAPABILITY ? ALLIES_CAPABILITY.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return ALLIES_CAPABILITY.getStorage().writeNBT(ALLIES_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        ALLIES_CAPABILITY.getStorage().readNBT(ALLIES_CAPABILITY, this.instance, null, nbt);
    }
}
