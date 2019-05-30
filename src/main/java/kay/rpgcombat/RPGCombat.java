package kay.rpgcombat;
import kay.rpgcombat.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = RPGCombat.MODID, name = RPGCombat.MODNAME, version = RPGCombat.MODVERSION, dependencies = "required-after:forge@[14.23.5.2768,)", useMetadata = true)
public class RPGCombat {
    public static final String MODID = "rpgcombat";
    public static final String MODNAME = "Kay's Customizable RPG Classes and Combat Mod";
    public static final String MODVERSION= "alpha";

    @SidedProxy(clientSide = "kay.rpgcombat.proxy.ClientProxy", serverSide = "kay.rpgcombat.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static RPGCombat instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
