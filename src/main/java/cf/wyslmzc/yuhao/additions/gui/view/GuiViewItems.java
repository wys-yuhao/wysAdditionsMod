package cf.wyslmzc.yuhao.additions.gui.view;

import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.items.IItemAdded;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardItem;
import cf.wyslmzc.yuhao.additions.gui.view.edit.item.GuiEditSelectTypeItem;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeItem;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeManager;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the items added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiViewItems extends GuiViewAdditionType {

	public GuiViewItems(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.edit.item.title"), new TextComponentTranslation("gui.view.addon.items.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {		
		List<IItemAdded> additions = AdditionTypeManager.getAdditionType(AdditionTypeItem.NAME).getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.getId() == null || second == null || second.getId() == null) {
				return 0;
			}
			return first.getId().compareTo(second.getId());
		});
		
		for (IItemAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardItem(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiEditSelectTypeItem(this, I18n.format("gui.edit.item.title"), this.addon);
	}

}
