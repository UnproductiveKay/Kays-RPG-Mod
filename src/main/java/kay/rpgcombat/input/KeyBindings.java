package kay.rpgcombat.input;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyBindings {
    public static KeyBinding ability2;
    public static KeyBinding ability3;
    public static KeyBinding ability4;

    public static void init() {
        ability2 = new KeyBinding("Ability 2", Keyboard.KEY_Q, "Kay's RPG Combat Mod");
        ability3 = new KeyBinding("Ability 3", Keyboard.KEY_E, "Kay's RPG Combat Mod");
        ability4 = new KeyBinding("Ability 4", Keyboard.KEY_R, "Kay's RPG Combat Mod");

        ClientRegistry.registerKeyBinding(ability2);
        ClientRegistry.registerKeyBinding(ability3);
        ClientRegistry.registerKeyBinding(ability4);
    }
}
