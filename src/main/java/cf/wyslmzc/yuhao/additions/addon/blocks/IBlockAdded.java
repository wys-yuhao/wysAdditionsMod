package cf.wyslmzc.yuhao.additions.addon.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import cf.wyslmzc.yuhao.additions.AdditionsMod;
import cf.wyslmzc.yuhao.additions.addon.items.IItemAdded;
import cf.wyslmzc.yuhao.additions.addon.items.ItemAddedManager;
import cf.wyslmzc.yuhao.additions.addon.items.blocks.IItemAddedBlock;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeItem;
import cf.wyslmzc.yuhao.additions.util.OtherSerializers;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

/**
 * Represents an added block.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since December 2018 
 */
public interface IBlockAdded {
	
	public void setItemBlock(@Nullable IItemAddedBlock itemBlock);
	
	public void setDisplayName(String displayName);
	
	public void setBlockMaterial(Material blockMaterial);
	
	public void setPlaceSound(SoundEvent sound);
	
	public void setBreakSound(SoundEvent sound);
	
	public void setHitSound(SoundEvent sound);
	
	public void setStepSound(SoundEvent sound);
	
	public void setFallSound(SoundEvent sound);
	
	public void setBlockMapColor(@Nullable MapColor mapColor);
	
	public void setHarvestLevel(int harvestLevel);
	
	public void setHarvestTool(String harvestTool);
	
	public void setEffectiveTools(List<String> effectiveTools);
	
	public void setBookshelfStrength(int bookshelfStrength);
	
	public void setBeaconColorMultiplier(@Nullable float[] beaconColorMultiplier);
	
	public void setSlipperiness(float slipperiness);
	
	public void setIsSlime(boolean isSlime);
	
	public void setIsBeaconBase(boolean isBeaconBase);
	
	public void setCanPistonsPush(boolean canPistonsPush);
	
	public void setSemiTransparent(boolean semiTransparent);
	
	public void setXpDroppedMin(int xpMin);
	
	public void setXpDroppedMax(int xpMax);
	
	public void setDroppedFromExplosions(Boolean droppedFromExplosions);
	
	public default void setCanEndermenCarry(boolean canEndermenCarry) {
		if (EntityEnderman.getCarriable(this.getAsBlock()) != canEndermenCarry) {
			EntityEnderman.setCarriable(this.getAsBlock(), canEndermenCarry);
		}
	}
	
	@Nullable
	public IItemAddedBlock getItemBlock();
	
	public String getDisplayName();
	
	public Material getBlockMaterial();
	
	public SoundEvent getPlaceSound();
	
	public SoundEvent getBreakSound();
	
	public SoundEvent getHitSound();
	
	public SoundEvent getStepSound();
	
	public SoundEvent getFallSound();
	
	@Nullable
	public MapColor getBlockMapColor();
	
	public int getHarvestLevel();
	
	public String getHarvestTool();
	
	public List<String> getEffectiveTools();
	
	public int getBookshelfStrength();
	
	@Nullable
	public float[] getBeaconColorMultiplier();
	
	public float getSlipperiness();
	
	public boolean isSlime();
	
	public boolean isBeaconBase();
	
	public boolean canPistonsPush();
	
	public float getHardness();
	
	public float getResistance();
	
	public int getOpacity();
	
	public boolean isSemiTransparent();
	
	public int getLightLevel();
	
	public int getXpDroppedMin();
	
	public int getXpDroppedMax();
	
	public Boolean getDroppedFromExplosions();
	
	public default boolean canEndermenCarry() {
		return EntityEnderman.getCarriable(this.getAsBlock());
	}
	
	public default void registerModels() {
		AdditionsMod.proxy.registerBlockRender(this.getAsBlock());
	}
	
	public default Block getAsBlock() {
		if (!(this instanceof Block)) {
			throw new IllegalArgumentException("An IBlockAdded must be an instance of Block.");
		}
		return (Block) this;
	}
	
    public default String getId() {
    	ResourceLocation registryName = this.getAsBlock().getRegistryName();
    	
    	if (registryName != null) {
    		return registryName.getResourcePath();
    	}
    	
        String unlocalizedName = this.getAsBlock().getUnlocalizedName();
        
        if (unlocalizedName.startsWith("tile.")) {
        	return unlocalizedName.substring(5);
        }
        
        return unlocalizedName;
    }

    public abstract static class Serializer<T extends IBlockAdded> {
		
		private final ResourceLocation blockAddedType;
        private final Class<T> blockAddedClass;

        protected Serializer(ResourceLocation location, Class<T> clazz)
        {
            this.blockAddedType = location;
            this.blockAddedClass = clazz;
        }

		public ResourceLocation getBlockAddedType() {
			return this.blockAddedType;
		}
		
		public Class<T> getBlockAddedClass() {
			return this.blockAddedClass;
		}

		public JsonObject serialize(T blockAddedObj, JsonSerializationContext context) {
			JsonObject json = new JsonObject();
			Block blockAdded = blockAddedObj.getAsBlock();
			
			if (blockAddedObj.getItemBlock() != null) {
				json.add("item_block", AdditionTypeItem.GSON.toJsonTree(blockAddedObj.getItemBlock()));
			}
			
			json.addProperty("name", blockAddedObj.getDisplayName());
			json.add("material", OtherSerializers.BlockMaterialSerializer.serialize(blockAddedObj.getBlockMaterial()));
			
			if (blockAddedObj.getPlaceSound() != null) {
				json.add("place_sound", OtherSerializers.SoundEventSerializer.serialize(blockAddedObj.getPlaceSound()));
			}
			
			if (blockAddedObj.getBreakSound() != null) {
				json.add("break_sound", OtherSerializers.SoundEventSerializer.serialize(blockAddedObj.getBreakSound()));
			}
			
			if (blockAddedObj.getHitSound() != null) {
				json.add("hit_sound", OtherSerializers.SoundEventSerializer.serialize(blockAddedObj.getHitSound()));
			}
			
			if (blockAddedObj.getStepSound() != null) {
				json.add("step_sound", OtherSerializers.SoundEventSerializer.serialize(blockAddedObj.getStepSound()));
			}
			
			if (blockAddedObj.getFallSound() != null) {
				json.add("fall_sound", OtherSerializers.SoundEventSerializer.serialize(blockAddedObj.getFallSound()));
			}
			
			if (blockAddedObj.getBlockMapColor() != null) {
				json.add("map_color", OtherSerializers.BlockMapColorSerializer.serialize(blockAddedObj.getBlockMapColor()));
			}
			
			if (blockAddedObj.getHardness() > 0) {
				json.addProperty("hardness", blockAddedObj.getHardness());
			}
			
			if (blockAddedObj.getResistance() > 0) {
				json.addProperty("resistance", blockAddedObj.getResistance() / 3.0f);
			}
			
			if (blockAddedObj.getHarvestLevel() != 0) {
				json.addProperty("harvest_level", blockAddedObj.getHarvestLevel());
			}
			
			if (!blockAddedObj.getHarvestTool().isEmpty()) {
				json.addProperty("harvest_tool", blockAddedObj.getHarvestTool());
			}
			
			if (!blockAddedObj.getEffectiveTools().isEmpty()) {
				json.add("effective_tools", OtherSerializers.StringListSerializer.serialize(blockAddedObj.getEffectiveTools()));
			}
			
			if (blockAdded.getLightOpacity(blockAdded.getDefaultState()) != 15) {
				json.addProperty("light_opacity", blockAdded.getLightOpacity(blockAdded.getDefaultState()));
			}
			
			if (blockAdded.getLightValue(blockAdded.getDefaultState()) > 0) {
				json.addProperty("light_level", blockAdded.getLightValue(blockAdded.getDefaultState()));
			}
			
			if (Blocks.FIRE.getFlammability(blockAdded) > 0) {
				json.addProperty("flammability", Blocks.FIRE.getFlammability(blockAdded));
			}
			
			if (Blocks.FIRE.getEncouragement(blockAdded) > 0) {
				json.addProperty("fire_spread_speed", Blocks.FIRE.getEncouragement(blockAdded));
			}
			
			if (blockAddedObj.getBookshelfStrength() > 0) {
				json.addProperty("bookshelf_strength", blockAddedObj.getBookshelfStrength());
			}
			
			float[] beaconColorsMultiplier = blockAddedObj.getBeaconColorMultiplier();
			if (beaconColorsMultiplier != null && beaconColorsMultiplier.length == 3) {
				JsonObject beaconColorObj = new JsonObject();
				beaconColorObj.addProperty("red", beaconColorsMultiplier[0]);
				beaconColorObj.addProperty("green", beaconColorsMultiplier[1]);
				beaconColorObj.addProperty("blue", beaconColorsMultiplier[2]);
				json.add("beacon_color_multiplier", beaconColorObj);
			}
			
			if (blockAddedObj.getSlipperiness() != 0.6f) {
				json.addProperty("slipperiness", blockAddedObj.getSlipperiness());
			}
			
			if (blockAddedObj.isSlime()) {
				json.addProperty("is_slime", true);
			}
			
			if (blockAddedObj.isBeaconBase()) {
				json.addProperty("is_beacon_base", true);
			}
			
			if (!blockAddedObj.canPistonsPush()) {
				json.addProperty("can_pistons_push", false);
			}
			
			if (blockAddedObj.isSemiTransparent()) {
				json.addProperty("semi_transparent", true);
			}
			
			if (blockAddedObj.getXpDroppedMin() != 0) {
				json.addProperty("xp_dropped_min", blockAddedObj.getXpDroppedMin());
			}
			
			if (blockAddedObj.getXpDroppedMax() != 0) {
				json.addProperty("xp_dropped_max", blockAddedObj.getXpDroppedMax());
			}
			
			if (blockAddedObj.getDroppedFromExplosions() != null) {
				json.addProperty("dropped_from_explosions", blockAddedObj.getDroppedFromExplosions());
			}
			
			if (blockAddedObj.canEndermenCarry()) {
				json.addProperty("can_endermen_carry", true);
			}
			
			return json;
        }

		public abstract T deserialize(JsonObject json, JsonDeserializationContext context);
		
		public <V extends Block & IBlockAdded> V deserializeDefaults(JsonObject json, JsonDeserializationContext context, V blockAdded) {
			
			if (json.has("item_block")) {
				IItemAdded itemAdded = AdditionTypeItem.GSON.fromJson(json.get("item_block"), IItemAdded.class);
				
				if (itemAdded instanceof IItemAddedBlock) {
					IItemAddedBlock itemAddedBlock = (IItemAddedBlock)itemAdded;
					itemAddedBlock.setBlock(blockAdded);
					blockAdded.setItemBlock(itemAddedBlock);
				}
			}
			
        	blockAdded.setDisplayName(JsonUtils.getString(json, "name"));
        	blockAdded.setBlockMaterial(OtherSerializers.BlockMaterialSerializer.deserialize(json, "material"));
			
			if (json.has("map_color")) {
				blockAdded.setBlockMapColor(OtherSerializers.BlockMapColorSerializer.deserialize(json, "map_color"));
			}
			
			blockAdded.setHardness(JsonUtils.getFloat(json, "hardness", 0));
			blockAdded.setResistance(JsonUtils.getFloat(json, "resistance", 0));
			blockAdded.setHarvestLevel(JsonUtils.getInt(json, "harvest_level", 0));
			blockAdded.setHarvestTool(JsonUtils.getString(json, "harvest_tool", ""));
			
			if (json.has("effective_tools")) {
				blockAdded.setEffectiveTools(OtherSerializers.StringListSerializer.deserialize(json.get("effective_tools"), "effective_tools"));
			}
			
			blockAdded.setLightOpacity(MathHelper.clamp(JsonUtils.getInt(json, "light_opacity", 15), 0, 15));
			blockAdded.setLightLevel(MathHelper.clamp(JsonUtils.getInt(json, "light_level", 0), 0, 15) / 15F);
			
			int flammability = JsonUtils.getInt(json, "flammability", 0);
			int fireSpreadSpeed = JsonUtils.getInt(json, "fire_spread_speed", 0);
			if (flammability > 0 || fireSpreadSpeed > 0) {
				Blocks.FIRE.setFireInfo(blockAdded, fireSpreadSpeed, flammability);
			}
			
			blockAdded.setBookshelfStrength(JsonUtils.getInt(json, "bookshelf_strength", 0));
			
			if (json.has("beacon_color_multiplier")) {
				JsonObject beaconColorObj = JsonUtils.getJsonObject(json, "beacon_color_multiplier");
				blockAdded.setBeaconColorMultiplier(new float[]{JsonUtils.getFloat(beaconColorObj, "red"), JsonUtils.getFloat(beaconColorObj, "green"), JsonUtils.getFloat(beaconColorObj, "blue")});
			}
			
			blockAdded.setSlipperiness(JsonUtils.getFloat(json, "slipperiness", 0.6f));
			blockAdded.setIsSlime(JsonUtils.getBoolean(json, "is_slime", false));
			blockAdded.setIsBeaconBase(JsonUtils.getBoolean(json, "is_beacon_base", false));
			blockAdded.setCanPistonsPush(JsonUtils.getBoolean(json, "can_pistons_push", true));
			blockAdded.setSemiTransparent(JsonUtils.getBoolean(json, "semi_transparent", false));
			blockAdded.setXpDroppedMin(JsonUtils.getInt(json, "xp_dropped_min", 0));
			blockAdded.setXpDroppedMax(JsonUtils.getInt(json, "xp_dropped_max", 0));
			blockAdded.setCanEndermenCarry(JsonUtils.getBoolean(json, "can_endermen_carry", false));
			
			if (json.has("dropped_by_explosions")) {
				blockAdded.setDroppedFromExplosions(JsonUtils.getBoolean(json, "dropped_by_explosions"));
			}
        	
        	return blockAdded;
        }
		
		public void postDeserializeBlockAdded(JsonObject json, IBlockAdded blockAddedObj) {
			if (blockAddedObj.getClass() != this.blockAddedClass) {
				throw new IllegalArgumentException("Tried to call post serialize for an object of the wrong type! Expected: " + this.blockAddedClass + ", Actual: " + blockAddedObj.getClass());
			}
			
			postDeserialize(json, (T) blockAddedObj);
		}
		
		public void postDeserialize(JsonObject json, T blockAdded) {
			this.postDeserializeDefaults(json, blockAdded);
		}
		
		public void postDeserializeDefaults(JsonObject json, T blockAdded) {
        	
        	IItemAdded itemAdded = blockAdded.getItemBlock();
        	if (itemAdded != null) {
        		ItemAddedManager.Serializer.postDeserialize(JsonUtils.getJsonObject(json, "item_block"), itemAdded);
        	}
        	
        	if (json.has("place_sound")) {
        		blockAdded.setPlaceSound(OtherSerializers.SoundEventSerializer.deserialize(json, "place_sound"));
        	}
        	
        	if (json.has("break_sound")) {
        		blockAdded.setBreakSound(OtherSerializers.SoundEventSerializer.deserialize(json, "break_sound"));
        	}
        	
        	if (json.has("hit_sound")) {
        		blockAdded.setHitSound(OtherSerializers.SoundEventSerializer.deserialize(json, "hit_sound"));
        	}
        	
        	if (json.has("step_sound")) {
        		blockAdded.setStepSound(OtherSerializers.SoundEventSerializer.deserialize(json, "step_sound"));
        	}
        	
        	if (json.has("fall_sound")) {
        		blockAdded.setFallSound(OtherSerializers.SoundEventSerializer.deserialize(json, "fall_sound"));
        	}
        }
    }
}
