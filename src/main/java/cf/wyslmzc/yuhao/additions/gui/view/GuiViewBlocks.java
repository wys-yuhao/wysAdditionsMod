package cf.wyslmzc.yuhao.additions.gui.view;

import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.blocks.IBlockAdded;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardBlock;
import cf.wyslmzc.yuhao.additions.gui.view.edit.block.GuiEditSelectTypeBlock;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeBlock;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the blocks added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since December 2018 
 */
public class GuiViewBlocks extends GuiViewAdditionType {

	public GuiViewBlocks(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.edit.block.title"), new TextComponentTranslation("gui.view.addon.blocks.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<IBlockAdded> additions = AdditionTypeBlock.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.getId() == null || second == null || second.getId() == null) {
				return 0;
			}
			return first.getId().compareTo(second.getId());
		});
		
		for (IBlockAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardBlock(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiEditSelectTypeBlock(this, I18n.format("gui.edit.block.title"), this.addon);
	}

}
