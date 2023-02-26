package cf.wyslmzc.yuhao.additions;

import org.apache.logging.log4j.Logger;

import cf.wyslmzc.yuhao.additions.addon.AddonLoader;
import cf.wyslmzc.yuhao.additions.addon.advancements.AddonAdvancementManager;
import cf.wyslmzc.yuhao.additions.addon.blocks.BlockAddedManager;
import cf.wyslmzc.yuhao.additions.addon.blocks.mapcolors.BlockMapColorManager;
import cf.wyslmzc.yuhao.additions.addon.blocks.materials.BlockMaterialManager;
import cf.wyslmzc.yuhao.additions.addon.effects.EffectManager;
import cf.wyslmzc.yuhao.additions.addon.effects.cause.EffectCauseManager;
import cf.wyslmzc.yuhao.additions.addon.entities.EntityAddedProjectile;
import cf.wyslmzc.yuhao.additions.addon.functions.AddonFunctionManager;
import cf.wyslmzc.yuhao.additions.addon.items.ItemAddedManager;
import cf.wyslmzc.yuhao.additions.addon.loottables.AddonLootTableManager;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePresetManager;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedManager;
import cf.wyslmzc.yuhao.additions.addon.structures.AddonStructureManager;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsCondition;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsDamage;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsDamageItem;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsDismount;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsEjectPassengers;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsEntityData;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsExplode;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsGrow;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsLoot;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsMount;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsMove;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsStructure;
import cf.wyslmzc.yuhao.additions.commands.CommandAdditionsThrow;
import cf.wyslmzc.yuhao.additions.network.CToSMessage;
import cf.wyslmzc.yuhao.additions.network.PacketHandlerClient;
import cf.wyslmzc.yuhao.additions.network.PacketHandlerServer;
import cf.wyslmzc.yuhao.additions.network.SToCMessage;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeManager;
import cf.wyslmzc.yuhao.additions.type.attribute.AttributeTypeManager;
import cf.wyslmzc.yuhao.additions.util.ProblemNotifier;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Additions allows non-coders to add items, blocks, and many other things to the game through GUIs.
 * 
 * Main mod class.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since July 2017 
 */
@Mod(modid = AdditionsMod.MOD_ID, 
     name = AdditionsMod.MOD_NAME, 
     version = AdditionsMod.VERSION, 
	 guiFactory = "cf.wyslmzc.yuhao.additions.gui.AdditionsGuiFactory",
	 dependencies = "after:*;required:lootoverhaul@[1.2,)"
)
public class AdditionsMod {
	
    public static final String MOD_ID = "wys";
    public static final String MOD_NAME = "wys";
    public static final String VERSION = "1.0";
    
    @Instance(MOD_ID)
	public static AdditionsMod instance;
	
	@SidedProxy(clientSide = "cf.wyslmzc.yuhao.additions.ClientProxy", serverSide = "cf.wyslmzc.yuhao.additions.CommonProxy")
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	public static SimpleNetworkWrapper networkWrapper;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
		
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		networkWrapper.registerMessage(PacketHandlerServer.class, CToSMessage.class, 0, Side.SERVER);
		networkWrapper.registerMessage(PacketHandlerClient.class, SToCMessage.class, 1, Side.CLIENT);
		
        ProblemNotifier.initializeProblemFolder(event);
        ConfigLoader.loadConfigFile(event);
        if (event.getSide() == Side.CLIENT) {
        	ClientConfigLoader.loadInternalConfigFile(event);
        }
        
        BlockMaterialManager.registerDefaultBlockMaterials();
        BlockMapColorManager.registerDefaultBlockMapColors();
        BlockAddedManager.registerDefaultBlocks();
        ItemAddedManager.registerDefaultItems();
        RecipeAddedManager.registerDefaultRecipes();
        LootTablePresetManager.registerDefaultLootTablePresets();
        EffectManager.registerDefaultEffects();
        EffectCauseManager.registerDefaultEffectCauses();
        AdditionTypeManager.registerDefaultAdditionTypes();
        AttributeTypeManager.initVanillaAttributes();
        SoundEventLoader.registerSoundEvents();
        proxy.registerGuiFactories();
        
        AddonLoader.loadAddons();
        
        proxy.refreshResources();
        
        AddonLoader.loadAdditionsPreInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	EntityRegistry.registerModEntity(new ResourceLocation(MOD_ID, "projectile"), EntityAddedProjectile.class, MOD_ID + "_projectile", 0, this, 80, 5, true);
    	proxy.registerEntityRenderers();
    	
    	AddonLoader.loadAdditionsInit(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	AddonLoader.loadAdditionsPostInit(event);
    }
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	AddonLoader.loadAdditionsServerStarting(event);
    	
    	event.registerServerCommand(new CommandAdditionsDamage());
    	event.registerServerCommand(new CommandAdditionsDamageItem());
    	event.registerServerCommand(new CommandAdditionsThrow());
    	event.registerServerCommand(new CommandAdditionsMove());
    	event.registerServerCommand(new CommandAdditionsExplode());
    	event.registerServerCommand(new CommandAdditionsMount());
    	event.registerServerCommand(new CommandAdditionsDismount());
    	event.registerServerCommand(new CommandAdditionsEjectPassengers());
    	event.registerServerCommand(new CommandAdditionsLoot());
    	event.registerServerCommand(new CommandAdditionsStructure());
    	event.registerServerCommand(new CommandAdditionsEntityData());
    	event.registerServerCommand(new CommandAdditionsCondition());
    	event.registerServerCommand(new CommandAdditionsGrow());
    	
    	if (ConfigLoader.replaceManagers.getBoolean(true)) {
    		AddonFunctionManager.replaceFunctionManager(event.getServer());
    		AddonAdvancementManager.replaceAdvancementManager(event.getServer());
    		AddonStructureManager.replaceStructureManager(event.getServer());
    		AddonLootTableManager.replaceLootTableManager(event.getServer());
    	}
    }
    
    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
    	if (ConfigLoader.replaceManagers != null && ConfigLoader.replaceManagers.getBoolean(true)) {
    		AddonFunctionManager.deleteFunctionManager();
    		AddonAdvancementManager.deleteAdvancementManager();
    		AddonStructureManager.deleteStructureManager();
    		AddonLootTableManager.deleteLootTableManger();
    	}
    }
}
