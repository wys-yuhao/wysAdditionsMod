package cf.wyslmzc.yuhao.additions.addon.effects.cause;

import java.util.HashSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import cf.wyslmzc.yuhao.additions.AdditionsMod;
import cf.wyslmzc.yuhao.additions.util.EntityCategoryChecker;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

/**
 * Cause for an entity right clicked.
 * 
 * @author Rebeca Rey (Tmtravlr)
 * @date May 2021
 */
public class EffectCauseEntityRightClicked extends EffectCauseEntity {
	public static final ResourceLocation TYPE = new ResourceLocation(AdditionsMod.MOD_ID, "entity_right_clicked");
	
	public boolean targetSelf = false;
	
	public boolean applies(Entity entity) {
		return this.entityMatches(entity);
	}

	public static class Serializer extends EffectCause.Serializer<EffectCauseEntityRightClicked> {
		
		public Serializer() {
			super(TYPE, EffectCauseEntityRightClicked.class);
		}
		
		@Override
		public JsonObject serialize(EffectCauseEntityRightClicked effectCause, JsonSerializationContext context) {
			JsonObject json = new JsonObject();
			
			if (!effectCause.entityTypes.isEmpty()) {
				JsonArray jsonArray = new JsonArray();
				
				effectCause.entityTypes.forEach(type -> jsonArray.add(type.toString()));
				
				json.add("entity_types", jsonArray);
			}
			
			if (!effectCause.entityCategories.isEmpty()) {
				JsonArray jsonArray = new JsonArray();
				
				effectCause.entityCategories.forEach(category -> jsonArray.add(category.toString()));
				
				json.add("entity_categories", jsonArray);
			}
			
			if (effectCause.entityTag != null && !effectCause.entityTag.hasNoTags()) {
				json.addProperty("entity_tag", effectCause.entityTag.toString());
			}
			
			if (effectCause.targetSelf) {
				json.addProperty("target_self", true);
			}
			
			return json;
		}
		
		@Override
		public EffectCauseEntityRightClicked deserialize(JsonObject json, JsonDeserializationContext context) {
			EffectCauseEntityRightClicked effectCause = new EffectCauseEntityRightClicked();
			
			if (JsonUtils.isJsonArray(json, "entity_types")) {
				effectCause.entityTypes = new HashSet<>();
				
				JsonUtils.getJsonArray(json, "entity_types").forEach(jsonElement -> {
					effectCause.entityTypes.add(new ResourceLocation(JsonUtils.getString(jsonElement, "member of entity_types")));
				});
			}
			
			if (JsonUtils.isJsonArray(json, "entity_categories")) {
				effectCause.entityCategories = new HashSet<>();
				
				JsonUtils.getJsonArray(json, "entity_categories").forEach(jsonElement -> {
					String categoryName = JsonUtils.getString(jsonElement, "member of entity_categories");
					
					try {
						effectCause.entityCategories.add(EntityCategoryChecker.EntityCategory.valueOf(categoryName.toUpperCase()));
					} catch (IllegalArgumentException e) {
						throw new JsonSyntaxException("Unknown entity category '" + categoryName + "' in entity_categories.", e);
					}
				});
			}
			
			if (JsonUtils.isString(json, "entity_tag")) {
				try {
					effectCause.entityTag = JsonToNBT.getTagFromJson(JsonUtils.getString(json, "entity_tag"));
                } catch (NBTException nbtexception) {
                    throw new JsonSyntaxException(nbtexception);
                }
			}
			
			effectCause.targetSelf = JsonUtils.getBoolean(json, "target_self", false);
			
			return effectCause;
		}
    }
}
