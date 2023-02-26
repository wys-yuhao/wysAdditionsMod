package cf.wyslmzc.yuhao.additions.gui.view;

import java.io.File;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.advancements.AdvancementAdded;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBoxTwoButton;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardAdvancement;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeAdvancement;
import cf.wyslmzc.yuhao.additions.util.client.CommonGuiUtils;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the advancements added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class GuiViewAdvancements extends GuiViewAdditionType {

	public GuiViewAdvancements(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.popup.advancement.addAdvancement.title"), new TextComponentTranslation("gui.view.addon.advancements.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<AdvancementAdded> additions = AdditionTypeAdvancement.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.id == null || second == null || second.id == null) {
				return 0;
			}
			return first.id.compareTo(second.id);
		});
		
		for (AdvancementAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardAdvancement(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiMessageBoxAddAdvancement(this);
	}
	
	protected class GuiMessageBoxAddAdvancement extends GuiMessageBoxTwoButton {
		
		public GuiMessageBoxAddAdvancement(GuiScreen parentScreen) {
			super(parentScreen, parentScreen, I18n.format("gui.popup.advancement.addAdvancement.title"), new TextComponentTranslation("gui.popup.advancement.addAdvancement.message"), I18n.format("gui.buttons.back"), I18n.format("gui.buttons.goToFolder"));
		}
		
		@Override
	    protected void onSecondButtonClicked() {
			File advancementFolder = AdditionTypeAdvancement.INSTANCE.getAdvancementFolder(GuiViewAdvancements.this.addon);
			
			if (!advancementFolder.isDirectory()) {
				advancementFolder.mkdirs();
			}

			CommonGuiUtils.openFolder(advancementFolder);

			this.mc.displayGuiScreen(this.parentScreen);
		}
	}

}
