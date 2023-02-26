package cf.wyslmzc.yuhao.additions;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundEventLoader {
	
	public static final SoundEvent BLOCK_CAULDRON_WASH = new SoundEvent(new ResourceLocation(AdditionsMod.MOD_ID, "block.cauldron.wash"));
	public static final SoundEvent ITEM_SHIELD_BASH = new SoundEvent(new ResourceLocation(AdditionsMod.MOD_ID, "item.shield.bash"));
	public static final SoundEvent ITEM_SHIELD_SWING = new SoundEvent(new ResourceLocation(AdditionsMod.MOD_ID, "item.shield.swing"));
	
	public static void registerSoundEvents() {
		ForgeRegistries.SOUND_EVENTS.register(BLOCK_CAULDRON_WASH.setRegistryName(new ResourceLocation(AdditionsMod.MOD_ID, "block.cauldron.wash")));
		ForgeRegistries.SOUND_EVENTS.register(ITEM_SHIELD_BASH.setRegistryName(new ResourceLocation(AdditionsMod.MOD_ID, "item.shield.bash")));
		ForgeRegistries.SOUND_EVENTS.register(ITEM_SHIELD_SWING.setRegistryName(new ResourceLocation(AdditionsMod.MOD_ID, "item.shield.swing")));
	}

}
