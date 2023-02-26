package cf.wyslmzc.yuhao.additions.gui.view;

import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.effects.EffectList;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardEffect;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEditEffectList;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeEffect;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the recipes added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since September 2017 
 */
public class GuiViewEffects extends GuiViewAdditionType {

	public GuiViewEffects(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.edit.effectList.title"), new TextComponentTranslation("gui.view.addon.effects.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<EffectList> additions = AdditionTypeEffect.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.id == null || second == null || second.id == null) {
				return 0;
			}
			return first.id.compareTo(second.id);
		});
		
		for (EffectList addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardEffect(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiEditEffectList(this, I18n.format("gui.edit.effectList.title"), this.addon, null);
	}

}
