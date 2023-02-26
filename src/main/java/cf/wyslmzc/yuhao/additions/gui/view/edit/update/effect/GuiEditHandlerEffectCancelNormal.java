package cf.wyslmzc.yuhao.additions.gui.view.edit.update.effect;

import java.util.Collections;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.effects.Effect;
import cf.wyslmzc.yuhao.additions.addon.effects.EffectCancelNormal;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiEffectEditHandler;
import cf.wyslmzc.yuhao.additions.gui.view.components.GuiComponentDisplayText;
import cf.wyslmzc.yuhao.additions.gui.view.components.IGuiViewComponent;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

import net.minecraft.util.text.TextComponentTranslation;

/**
 * Creates or updates a cancel normal effect
 * 
 * @author Rebeca Rey (Tmtravlr)
 * @date June 2019
 */
public class GuiEditHandlerEffectCancelNormal implements IGuiEffectEditHandler {
	private GuiEdit editScreen;

	private GuiComponentDisplayText description;

	@Override
	public List<IGuiViewComponent> getViewComponents(GuiEdit editScreen, Effect effect) {
		this.editScreen = editScreen;
		
		this.description = new GuiComponentDisplayText(editScreen, new TextComponentTranslation("type.effect.cancelNormal.description"));
		
		return Collections.singletonList(this.description);
	}

	@Override
	public Effect createEffect() {
		if (this.editScreen == null) {
			return null;
		}
		
		return new EffectCancelNormal();
	}

}
