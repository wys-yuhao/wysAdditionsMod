package cf.wyslmzc.yuhao.additions.api.gui;

import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.effects.Effect;
import cf.wyslmzc.yuhao.additions.gui.view.components.IGuiViewComponent;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

/**
 * Handles creating the components and saving the effect.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since May 2019
 */
public interface IGuiEffectEditHandler {

	/**
	 * Creates a list of components to display on the edit screen for this effect.
	 * The effect could be any type, and a null cause means this is creating a new one
	 */
	public List<IGuiViewComponent> getViewComponents(GuiEdit editScreen, Effect effect);
	
	/**
	 * Save the effect
	 */
	public Effect createEffect();
}
