package cf.wyslmzc.yuhao.additions.gui.registration;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedBrewing;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedBrewingComplete;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedCraftingDyeItem;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedCraftingModifyDamage;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedCraftingModifyNBT;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedCraftingPotionTipping;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedCraftingShaped;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedCraftingShapeless;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedManager;
import cf.wyslmzc.yuhao.additions.addon.recipes.RecipeAddedSmelting;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiRecipeAddedFactory;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiRecipeCardDisplay;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayBrewing;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayBrewingComplete;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayCraftingDyeItem;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayCraftingModifyDamage;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayCraftingModifyNBT;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayCraftingPotionTipping;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayCraftingShaped;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplayCraftingShapeless;
import cf.wyslmzc.yuhao.additions.gui.type.card.recipe.GuiRecipeCardDisplaySmelting;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeBrewing;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeBrewingComplete;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeCraftingDyeItem;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeCraftingModifyDamage;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeCraftingModifyNBT;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeCraftingPotionTipping;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeCraftingShaped;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeCraftingShapeless;
import cf.wyslmzc.yuhao.additions.gui.view.edit.recipe.GuiEditRecipeSmelting;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiFactoryRegistrationRecipeAdded {

	public static void registerGuiFactories() {
		RecipeAddedManager.registerGuiFactory(RecipeAddedCraftingShaped.TYPE, new IGuiRecipeAddedFactory<RecipeAddedCraftingShaped>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.crafting.shaped.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.crafting.shaped.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedCraftingShaped recipe) {
				return new GuiRecipeCardDisplayCraftingShaped(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingShaped recipe) {
				return new GuiEditRecipeCraftingShaped(parent, recipe == null ? I18n.format("gui.edit.recipe.crafting.shaped.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingShaped recipe) {
				GuiEditRecipeCraftingShaped editScreen = new GuiEditRecipeCraftingShaped(parent, I18n.format("gui.edit.recipe.crafting.shaped.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
		
		RecipeAddedManager.registerGuiFactory(RecipeAddedCraftingShapeless.TYPE, new IGuiRecipeAddedFactory<RecipeAddedCraftingShapeless>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.crafting.shapeless.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.crafting.shapeless.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedCraftingShapeless recipe) {
				return new GuiRecipeCardDisplayCraftingShapeless(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingShapeless recipe) {
				return new GuiEditRecipeCraftingShapeless(parent, recipe == null ? I18n.format("gui.edit.recipe.crafting.shapeless.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingShapeless recipe) {
				GuiEditRecipeCraftingShapeless editScreen = new GuiEditRecipeCraftingShapeless(parent, I18n.format("gui.edit.recipe.crafting.shapeless.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
		
		RecipeAddedManager.registerGuiFactory(RecipeAddedCraftingPotionTipping.TYPE, new IGuiRecipeAddedFactory<RecipeAddedCraftingPotionTipping>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.crafting.tipProjectile.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.crafting.tipProjectile.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedCraftingPotionTipping recipe) {
				return new GuiRecipeCardDisplayCraftingPotionTipping(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingPotionTipping recipe) {
				return new GuiEditRecipeCraftingPotionTipping(parent, recipe == null ? I18n.format("gui.edit.recipe.crafting.tipProjectile.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingPotionTipping recipe) {
				GuiEditRecipeCraftingPotionTipping editScreen = new GuiEditRecipeCraftingPotionTipping(parent, I18n.format("gui.edit.recipe.crafting.tipProjectile.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
			
		RecipeAddedManager.registerGuiFactory(RecipeAddedCraftingDyeItem.TYPE, new IGuiRecipeAddedFactory<RecipeAddedCraftingDyeItem>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.crafting.dyeItem.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.crafting.dyeItem.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedCraftingDyeItem recipe) {
				return new GuiRecipeCardDisplayCraftingDyeItem(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingDyeItem recipe) {
				return new GuiEditRecipeCraftingDyeItem(parent, recipe == null ? I18n.format("gui.edit.recipe.crafting.dyeItem.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingDyeItem recipe) {
				GuiEditRecipeCraftingDyeItem editScreen = new GuiEditRecipeCraftingDyeItem(parent, I18n.format("gui.edit.recipe.crafting.dyeItem.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
		
		RecipeAddedManager.registerGuiFactory(RecipeAddedCraftingModifyDamage.TYPE, new IGuiRecipeAddedFactory<RecipeAddedCraftingModifyDamage>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.crafting.modifyDamage.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.crafting.modifyDamage.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedCraftingModifyDamage recipe) {
				return new GuiRecipeCardDisplayCraftingModifyDamage(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingModifyDamage recipe) {
				return new GuiEditRecipeCraftingModifyDamage(parent, recipe == null ? I18n.format("gui.edit.recipe.crafting.modifyDamage.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingModifyDamage recipe) {
				GuiEditRecipeCraftingModifyDamage editScreen = new GuiEditRecipeCraftingModifyDamage(parent, I18n.format("gui.edit.recipe.crafting.modifyDamage.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
		
		RecipeAddedManager.registerGuiFactory(RecipeAddedCraftingModifyNBT.TYPE, new IGuiRecipeAddedFactory<RecipeAddedCraftingModifyNBT>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.crafting.modifyNbt.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.crafting.modifyNbt.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedCraftingModifyNBT recipe) {
				return new GuiRecipeCardDisplayCraftingModifyNBT(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingModifyNBT recipe) {
				return new GuiEditRecipeCraftingModifyNBT(parent, recipe == null ? I18n.format("gui.edit.recipe.crafting.modifyNbt.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedCraftingModifyNBT recipe) {
				GuiEditRecipeCraftingModifyNBT editScreen = new GuiEditRecipeCraftingModifyNBT(parent, I18n.format("gui.edit.recipe.crafting.modifyNbt.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
		
		RecipeAddedManager.registerGuiFactory(RecipeAddedSmelting.TYPE, new IGuiRecipeAddedFactory<RecipeAddedSmelting>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.smelting.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.smelting.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedSmelting recipe) {
				return new GuiRecipeCardDisplaySmelting(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedSmelting recipe) {
				return new GuiEditRecipeSmelting(parent, recipe == null ? I18n.format("gui.edit.recipe.smelting.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedSmelting recipe) {
				GuiEditRecipeSmelting editScreen = new GuiEditRecipeSmelting(parent, I18n.format("gui.edit.recipe.smelting.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
		
		RecipeAddedManager.registerGuiFactory(RecipeAddedBrewing.TYPE, new IGuiRecipeAddedFactory<RecipeAddedBrewing>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.brewing.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.brewing.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedBrewing recipe) {
				return new GuiRecipeCardDisplayBrewing(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedBrewing recipe) {
				return new GuiEditRecipeBrewing(parent, recipe == null ? I18n.format("gui.edit.recipe.brewing.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedBrewing recipe) {
				GuiEditRecipeBrewing editScreen = new GuiEditRecipeBrewing(parent, I18n.format("gui.edit.recipe.brewing.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
		
		RecipeAddedManager.registerGuiFactory(RecipeAddedBrewingComplete.TYPE, new IGuiRecipeAddedFactory<RecipeAddedBrewingComplete>() {
			@Override
			public String getTitle() {
				return I18n.format("type.recipe.brewing.complete.title");
			}
			
			@Override
			public String getDescription() {
				return I18n.format("type.recipe.brewing.complete.description");
			}
			
			@Override
			public IGuiRecipeCardDisplay getRecipeCardDisplay(RecipeAddedBrewingComplete recipe) {
				return new GuiRecipeCardDisplayBrewingComplete(recipe);
			}
			
			@Override
			public GuiEdit getEditScreen(GuiScreen parent, Addon addon, RecipeAddedBrewingComplete recipe) {
				return new GuiEditRecipeBrewingComplete(parent, recipe == null ? I18n.format("gui.edit.recipe.brewing.complete.title") : I18n.format("gui.edit.editing", recipe.getId()), addon, recipe);
			}
			
			@Override
			public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, RecipeAddedBrewingComplete recipe) {
				GuiEditRecipeBrewingComplete editScreen = new GuiEditRecipeBrewingComplete(parent, I18n.format("gui.edit.recipe.brewing.complete.title"), addon, null);
				editScreen.copyFrom(recipe);
				return editScreen;
			}
		});
	}
	
}
