package cf.wyslmzc.yuhao.additions.api.gui;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.blocks.IBlockAdded;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

import net.minecraft.client.gui.GuiScreen;

/**
 * Creates the edit screen for the block type.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since December 2018
 */
public interface IGuiBlockAddedFactory<T extends IBlockAdded> {
	
	/**
	 * Returns a friendlier title for the block type
	 */
	public String getTitle();

	/**
	 * Returns a short description about the block to show in the block type select screen
	 */
	public String getDescription();

	/**
	 * Creates a screen to edit the block, or to add it if block is passed in as null.
	 */
	public GuiEdit getEditScreen(GuiScreen parent, Addon addon, T block);

	/**
	 * Creates a screen to create a copy of the block.
	 */
	public GuiEdit getDuplicateScreen(GuiScreen parent, Addon addon, T block);
	
}
