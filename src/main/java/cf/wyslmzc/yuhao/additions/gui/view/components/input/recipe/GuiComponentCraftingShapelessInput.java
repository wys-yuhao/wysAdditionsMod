package cf.wyslmzc.yuhao.additions.gui.view.components.input.recipe;

import cf.wyslmzc.yuhao.additions.addon.recipes.IngredientOreNBT;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class GuiComponentCraftingShapelessInput extends GuiComponentCraftingRecipeInput {
	
	public GuiComponentCraftingShapelessInput(String label, GuiEdit editScreen) {
		super(label, editScreen);
	}
	
	public void setRecipe(ShapelessOreRecipe recipe) {
		this.setDefaultRecipe(recipe);
		this.editScreen.notifyHasChanges();
	}
	
	public void setDefaultRecipe(ShapelessOreRecipe recipe) {
		if (recipe == null) {
			this.ingredients = NonNullList.withSize(MAX_INGREDIENTS, IngredientOreNBT.EMPTY);
			this.recreateDisplayStacks();
			this.output = ItemStack.EMPTY;
		} else {
			this.setDefaultIngredients(recipe.getIngredients());
			this.output = recipe.getRecipeOutput();
		}
	}
	
	public ShapelessOreRecipe getRecipe() {
		NonNullList<Ingredient> ingredients = NonNullList.create();
		
		for (int i = 0; i < MAX_INGREDIENTS; i++) {
			IngredientOreNBT ingredient = this.ingredients.get(i);
			
			if (!ingredient.isEmpty()) {
				ingredients.add(ingredient);
			}
		}
		
		return new ShapelessOreRecipe(null, ingredients, this.output);
	}
	
	protected void setDefaultIngredients(NonNullList<Ingredient> ingredients) {
		this.ingredients = NonNullList.withSize(MAX_INGREDIENTS, IngredientOreNBT.EMPTY);
		
		for (int i = 0; i < MAX_INGREDIENTS; i++) {
			if (i < ingredients.size()) {
				Ingredient ingredient = ingredients.get(i);
				this.ingredients.set(i, ingredient instanceof IngredientOreNBT ? (IngredientOreNBT) ingredient : IngredientOreNBT.EMPTY);
			}
		}
		
		this.recreateDisplayStacks();
	}
}
