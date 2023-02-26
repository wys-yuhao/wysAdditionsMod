package cf.wyslmzc.yuhao.additions.gui.view;

import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.creativetabs.CreativeTabAdded;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardCreativeTab;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEditCreativeTab;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeCreativeTab;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the creative tabs added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiViewCreativeTabs extends GuiViewAdditionType {

	public GuiViewCreativeTabs(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.edit.creativeTab.title"), new TextComponentTranslation("gui.view.addon.creativeTabs.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<CreativeTabAdded> additions = AdditionTypeCreativeTab.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.getTabLabel() == null || second == null || second.getTabLabel() == null) {
				return 0;
			}
			return first.getTabLabel().compareTo(second.getTabLabel());
		});
		
		for (CreativeTabAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardCreativeTab(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiEditCreativeTab(this, I18n.format("gui.edit.creativeTab.title"), this.addon, null);
	}

}
