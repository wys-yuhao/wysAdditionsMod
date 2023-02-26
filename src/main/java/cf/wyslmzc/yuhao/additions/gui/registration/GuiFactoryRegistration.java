package cf.wyslmzc.yuhao.additions.gui.registration;

import javax.annotation.Nullable;

import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonAdvancement;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonBlock;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonCreativeTab;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonEffect;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonFunction;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonItem;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonItemMaterial;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonLootTable;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonRecipe;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonSoundEvent;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButtonStructure;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeManager;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class GuiFactoryRegistration {

	public static void registerGuiFactories() {
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonBlock(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonItem(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonCreativeTab(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonRecipe(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonItemMaterial(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonEffect(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonAdvancement(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonFunction(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonLootTable(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonStructure(parent, addon));
		AdditionTypeManager.registerAdditionTypeGuiFactory((parent, addon) -> new GuiAdditionTypeButtonSoundEvent(parent, addon));
		
		GuiFactoryRegistrationItemAdded.registerGuiFactories();
		GuiFactoryRegistrationBlockAdded.registerGuiFactories();
		GuiFactoryRegistrationLootTablePreset.registerGuiFactories();
		GuiFactoryRegistrationRecipeAdded.registerGuiFactories();
		GuiFactoryRegistrationEffectCause.registerGuiFactories();
		GuiFactoryRegistrationEffect.registerGuiFactories();
	}
	
}
