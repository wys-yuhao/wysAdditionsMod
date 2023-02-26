package cf.wyslmzc.yuhao.additions.gui.view.edit.update;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.blocks.IBlockAdded;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTableAdded;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTablePreset;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBoxTwoButton;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeBlock;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeLootTable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Edit screen for block drops, used when first creating the block
 * 
 * @author Rebeca Rey (Tmtravlr)
 * @since May 2019
 */
public class GuiEditBlockDropInitial extends GuiEditBlockDrop {
	
	protected final GuiScreen nextScreen;
	protected LootTablePreset defaultPreset;
	
	public GuiEditBlockDropInitial(GuiEdit parentScreen, GuiScreen nextScreen, LootTableAdded lootTable, IBlockAdded block, Addon addon) {
		super(parentScreen, lootTable, block, addon);
		this.nextScreen = nextScreen;
		this.defaultPreset = lootTable.preset;
	}
	
	@Override
	protected void handleSaved() {
		AdditionTypeLootTable.INSTANCE.saveAddition(this.addon, this.lootTable);
		AdditionTypeBlock.INSTANCE.saveAddition(this.addon, this.block);
		
    	this.mc.displayGuiScreen(this.nextScreen);
	}
	
	@Override
	public void cancelEdit() {
		this.mc.displayGuiScreen(new GuiMessageBoxTwoButton(this, this.nextScreen, I18n.format("gui.edit.blockDrop.cancel.title"), new TextComponentTranslation("gui.edit.blockDrop.cancel.message"), I18n.format("gui.buttons.back"), I18n.format("gui.edit.blockDrop.popup.useDefault")) {
			
			@Override
			public void onSecondButtonClicked() {
				GuiEditBlockDropInitial.this.lootTable.preset = GuiEditBlockDropInitial.this.defaultPreset;
				GuiEditBlockDropInitial.this.handleSaved();
			}
			
		});
	}
	
}
