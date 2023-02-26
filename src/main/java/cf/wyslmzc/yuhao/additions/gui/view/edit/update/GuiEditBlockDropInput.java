package cf.wyslmzc.yuhao.additions.gui.view.edit.update;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.blocks.IBlockAdded;
import cf.wyslmzc.yuhao.additions.addon.loottables.LootTableAdded;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentBlockDropInput;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

/**
 * Edit screen for block drops, used from the block drop input
 * 
 * @author Rebeca Rey (Tmtravlr)
 * @since May 2019
 */
public class GuiEditBlockDropInput extends GuiEditBlockDrop {
	
	protected GuiComponentBlockDropInput input;
	
	public GuiEditBlockDropInput(GuiEdit parentScreen, LootTableAdded lootTable, IBlockAdded block, Addon addon, GuiComponentBlockDropInput input) {
		super(parentScreen, lootTable, block, addon);
		this.input = input;
	}
	
	@Override
	protected void handleSaved() {
		input.setBlockDropPreset(this.lootTable.preset);
		
    	super.handleSaved();
	}
	
}
