package cf.wyslmzc.yuhao.additions.gui.view.edit.block;

import java.util.Collection;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.blocks.BlockAddedManager;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiBlockAddedFactory;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEditSelectType;

import net.minecraft.client.gui.GuiScreen;

/**
 * Page for selecting a block type to add.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since December 2018
 */
public class GuiEditSelectTypeBlock extends GuiEditSelectType<IGuiBlockAddedFactory> {

	public GuiEditSelectTypeBlock(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon);
	}

	@Override
	public Collection<IGuiBlockAddedFactory> getTypes() {
		return BlockAddedManager.getAllGuiFactories().values();
	}

	@Override
	public String getDescription(IGuiBlockAddedFactory editFactory) {
		return editFactory.getDescription();
	}
	
	@Override
	public String getTitle(IGuiBlockAddedFactory editFactory) {
		return editFactory.getTitle();
	}

	@Override
	public void createObject() {
		this.mc.displayGuiScreen(this.getSelectedType().getEditScreen(this.parentScreen, this.addon, null));
	}

}
