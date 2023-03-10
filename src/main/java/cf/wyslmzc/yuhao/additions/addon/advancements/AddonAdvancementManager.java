package cf.wyslmzc.yuhao.additions.addon.advancements;

import java.io.File;

import javax.annotation.Nonnull;

import net.minecraft.advancements.AdvancementManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

/**
 * Loads and manages addon advancements
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class AddonAdvancementManager {
	
	private static ExtendedAdvancementManager extendedAdvancementManager;
	
	public static void replaceAdvancementManager(@Nonnull MinecraftServer server) {
		File advancementDir = ObfuscationReflectionHelper.getPrivateValue(AdvancementManager.class, server.getAdvancementManager(), "field_192785_d", "advancementsDir");
		extendedAdvancementManager = new ExtendedAdvancementManager(advancementDir);
		ObfuscationReflectionHelper.setPrivateValue(World.class, server.getWorld(0), extendedAdvancementManager, "field_191951_C", "advancementManager");
	}
	
	public static void deleteAdvancementManager() {
		extendedAdvancementManager = null;
	}
}
