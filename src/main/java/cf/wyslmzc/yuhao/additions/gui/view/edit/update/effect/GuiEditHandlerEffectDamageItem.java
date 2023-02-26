package cf.wyslmzc.yuhao.additions.gui.view.edit.update.effect;

import java.util.Arrays;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.effects.Effect;
import cf.wyslmzc.yuhao.additions.addon.effects.EffectDamageItem;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiEffectEditHandler;
import cf.wyslmzc.yuhao.additions.gui.view.components.GuiComponentDisplayText;
import cf.wyslmzc.yuhao.additions.gui.view.components.IGuiViewComponent;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentFloatInput;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentIntegerInput;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Creates or updates a damage item effect
 * 
 * @author Rebeca Rey (Tmtravlr)
 * @date June 2019
 */
public class GuiEditHandlerEffectDamageItem implements IGuiEffectEditHandler {
	private GuiEdit editScreen;

	private GuiComponentDisplayText description;
	private GuiComponentIntegerInput amountInput;
	private GuiComponentFloatInput chanceInput;

	@Override
	public List<IGuiViewComponent> getViewComponents(GuiEdit editScreen, Effect effect) {
		this.editScreen = editScreen;
		EffectDamageItem effectDamageItem = (effect instanceof EffectDamageItem) ? (EffectDamageItem) effect : null;
		
		this.description = new GuiComponentDisplayText(editScreen, new TextComponentTranslation("type.effect.damageItem.description"));
		
		this.amountInput = new GuiComponentIntegerInput(I18n.format("gui.edit.effect.damageItem.amount.label"), editScreen, false);
		this.amountInput.setInfo(new TextComponentTranslation("gui.edit.effect.damageItem.amount.info"));
		this.amountInput.setMinimum(1);
		this.amountInput.setMaximum(64);
		this.amountInput.setDefaultInteger(effectDamageItem != null ? effectDamageItem.amount : 1);
		
		this.chanceInput = new GuiComponentFloatInput(I18n.format("gui.edit.effect.chance.label"), editScreen, false);
		this.chanceInput.setInfo(new TextComponentTranslation("gui.edit.effect.chance.info"));
		this.chanceInput.setMinimum(0);
		this.chanceInput.setMaximum(1);
		this.chanceInput.setDefaultFloat(effectDamageItem != null ? effectDamageItem.chance : 1);
		
		return Arrays.asList(this.description, this.amountInput, this.chanceInput);
	}

	@Override
	public Effect createEffect() {
		if (this.editScreen == null || this.amountInput == null || this.chanceInput == null) {
			return null;
		}
		
		EffectDamageItem effect = new EffectDamageItem();
		
		effect.amount = this.amountInput.getInteger();
		effect.chance = this.chanceInput.getFloat();
		
		return effect;
	}

}
