package cf.wyslmzc.yuhao.additions.type;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import cf.wyslmzc.yuhao.additions.AdditionsMod;
import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.AddonLoader;
import cf.wyslmzc.yuhao.additions.addon.items.IItemAdded;
import cf.wyslmzc.yuhao.additions.addon.items.ItemAddedManager;
import cf.wyslmzc.yuhao.additions.util.ProblemNotifier;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Added items
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
@EventBusSubscriber(modid = AdditionsMod.MOD_ID)
public class AdditionTypeItem extends AdditionType<IItemAdded> {

	public static final ResourceLocation NAME = new ResourceLocation(AdditionsMod.MOD_ID, "item");
	public static final String FOLDER_NAME = "data" + File.separator + "items";
	public static final String FILE_POSTFIX = ".json";
	public static final AdditionTypeItem INSTANCE = new AdditionTypeItem();
	
	public static final Gson GSON = new GsonBuilder()
			.registerTypeHierarchyAdapter(IItemAdded.class, new ItemAddedManager.Serializer())
			.setPrettyPrinting()
			.create();
	
	private Multimap<Addon, IItemAdded> loadedItems = HashMultimap.create();
	
	// Can't load things like lists of ammo right away, since the items they need may not have loaded yet
	private Map<IItemAdded, JsonObject> itemsToPostDeserialize = new HashMap<>();
	
	@SubscribeEvent
	public static void onRegisterItems(Register<Item> event) {
		INSTANCE.registerItems(event);
	}

	public void registerItems(Register<Item> event) {
		AdditionsMod.logger.info("Loading addon items.");
		
		for (Addon addon : AddonLoader.addonsLoaded) {
			List<String> filePaths = new ArrayList<>();
			
			try {
				filePaths = AddonLoader.getAddonFilePaths(addon.addonFolder, FOLDER_NAME);
			} catch (IOException e) {
				AdditionsMod.logger.error("Error loading item files for addon " + addon.id + ". The items will not load.", e);
				ProblemNotifier.addProblemNotification(new TextComponentTranslation("gui.view.addon.items.title", addon.id), new TextComponentString(e.getMessage()));
			}
			
			for (String filePath : filePaths) {
				try {
					String fileString = AddonLoader.readAddonFile(addon.addonFolder, filePath);
					JsonObject itemJson = GSON.fromJson(fileString, JsonObject.class);
					IItemAdded itemAdded = GSON.fromJson(itemJson, IItemAdded.class);
					this.itemsToPostDeserialize.put(itemAdded, itemJson);
					
					Item item = itemAdded.getAsItem();
					
					String itemName = filePath;
					if (itemName.contains(File.separator)) {
						itemName = itemName.substring(itemName.lastIndexOf(File.separator) + 1);
					}
					
					if (itemName.endsWith(FILE_POSTFIX)) {
						itemName = itemName.substring(0, itemName.length() - FILE_POSTFIX.length());
					
						ResourceLocation itemRegistryName = new ResourceLocation(AdditionsMod.MOD_ID, itemName);
						
						this.loadedItems.put(addon, itemAdded);
						event.getRegistry().register(item.setUnlocalizedName(itemName).setRegistryName(itemRegistryName));
						
						for (String oreName : itemAdded.getOreDict()) {
							OreDictionary.registerOre(oreName, item);
						}
					}
				} catch (IOException | JsonParseException e) {
					AdditionsMod.logger.error("Error loading item " + filePath + " for addon " + addon.id + ". The item will not load.", e);
					ProblemNotifier.addProblemNotification(ProblemNotifier.createLabelFromPath(addon.addonFolder, filePath), new TextComponentString(e.getMessage()));
				}
			}
		}
	}

	@Override
	public void loadInit(List<Addon> addons, FMLInitializationEvent event) {
		AdditionsMod.logger.info("Registering item models.");
		List<Item> colorItemsToRegister = new ArrayList<>();
		
		AdditionsMod.logger.info("Initializing addon items.");
		for (Entry<IItemAdded, JsonObject> itemEntry : this.itemsToPostDeserialize.entrySet()) {
			try {
				ItemAddedManager.Serializer.postDeserialize(itemEntry.getValue(), itemEntry.getKey());
			} catch (JsonParseException e) {
				AdditionsMod.logger.error("There was a problem initializing item " + itemEntry.getKey().getId()  + ". If things continue, the game will probably majorly break, so it should stop loading here.");
				throw e; 
			}
		}
		
		this.itemsToPostDeserialize.clear();
		
		for (Addon addon : addons) {
			for (IItemAdded item : this.loadedItems.get(addon)) {
				item.registerModels();
				colorItemsToRegister.add(item.getAsItem());
			}
		}
		
		if(!colorItemsToRegister.isEmpty()) {
			AdditionsMod.proxy.registerItemColors(colorItemsToRegister.toArray(new Item[0]));
		}
	}
	
	@Override
	public List<IItemAdded> getAllAdditions(Addon addon) {
		return new ArrayList<>(this.loadedItems.get(addon));
	}
	
	public List<IItemAdded> getAllAdditions() {
		return new ArrayList<>(this.loadedItems.values());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void saveAddition(Addon addon, IItemAdded addition) {
		if (!this.loadedItems.containsEntry(addon, addition)) {
			this.loadedItems.put(addon, addition);
		}
		
		File itemFolder = new File(addon.addonFolder, FOLDER_NAME);
		
		if (!itemFolder.isDirectory()) {
			itemFolder.mkdir();
		}
		
		File additionFile = new File(itemFolder, addition.getId() + FILE_POSTFIX);
		
		try {
			String fileContents = GSON.toJson(addition);
			FileUtils.write(additionFile, fileContents, StandardCharsets.UTF_8);
		} catch (IOException | IllegalArgumentException e) {
			AdditionsMod.logger.error("Error saving addon " + addon.name, e);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void deleteAddition(Addon addon, IItemAdded addition) {
		if (this.loadedItems.containsEntry(addon, addition)) {
			this.loadedItems.remove(addon, addition);
		}
		
		File itemFolder = new File(addon.addonFolder, FOLDER_NAME);

		File additionFile = new File(itemFolder, addition.getId() + FILE_POSTFIX);
		
		if (additionFile.exists()) {
			additionFile.delete();
		}
	}
}
