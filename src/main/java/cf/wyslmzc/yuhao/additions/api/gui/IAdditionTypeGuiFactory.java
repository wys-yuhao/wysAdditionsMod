package cf.wyslmzc.yuhao.additions.api.gui;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.gui.type.button.GuiAdditionTypeButton;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;

/**
 * Info for rendering an AdditionType. Used by the GUI to add a button in the addon's screen and to add/edit the additions.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2017
 */
public interface IAdditionTypeGuiFactory {
	
	/**
	 * Create the button for this addition type that shows up on the addon page.
	 * When clicked on, it should display a screen showing the additions for the given addon.
	 */
	public GuiAdditionTypeButton createButton(GuiView parent, Addon addon);
	
}
