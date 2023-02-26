package cf.wyslmzc.yuhao.additions.gui.view.edit.item;

import java.util.Collection;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.items.ItemAddedManager;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiItemAddedFactory;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEditSelectType;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of items you can create.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiEditSelectTypeItem extends GuiEditSelectType<IGuiItemAddedFactory> {
	
	public GuiEditSelectTypeItem(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon);
	}

	@Override
	public Collection<IGuiItemAddedFactory> getTypes() {
		return ItemAddedManager.getAllGuiFactories().values();
	}

	@Override
	public String getDescription(IGuiItemAddedFactory editFactory) {
		return editFactory.getDescription();
	}
	
	@Override
	public String getTitle(IGuiItemAddedFactory editFactory) {
		return editFactory.getTitle();
	}

	@Override
	public void createObject() {
		this.mc.displayGuiScreen(this.getSelectedType().getEditScreen(this.parentScreen, this.addon, null));
	}

}
