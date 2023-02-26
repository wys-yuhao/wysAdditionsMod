package cf.wyslmzc.yuhao.additions.gui.view;

import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.functions.FunctionAdded;
import cf.wyslmzc.yuhao.additions.gui.type.card.GuiAdditionCardFunction;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEditFunction;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeFunction;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Shows a list of cards with info about the functions added
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018 
 */
public class GuiViewFunctions extends GuiViewAdditionType {

	public GuiViewFunctions(GuiScreen parentScreen, String title, Addon addon) {
		super(parentScreen, title, addon, I18n.format("gui.edit.function.title"), new TextComponentTranslation("gui.view.addon.functions.none.message", addon.name));
	}
	
	@Override
	public void addAdditions() {
		List<FunctionAdded> additions = AdditionTypeFunction.INSTANCE.getAllAdditions(this.addon);
		
		additions.sort((first, second) -> {
			if (first == null || first.id == null || second == null || second.id == null) {
				return 0;
			}
			return first.id.compareTo(second.id);
		});
		
		for (FunctionAdded addition : additions) {
			this.additions.addAdditionCard(new GuiAdditionCardFunction(this, this.addon, addition));
		}
	}

	@Override
	protected GuiScreen getNewAdditionScreen() {
		return new GuiEditFunction(this, I18n.format("gui.edit.function.title"), this.addon, null);
	}

}
