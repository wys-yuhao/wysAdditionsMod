package cf.wyslmzc.yuhao.additions.gui.view.edit.update.effect.cause;

import java.util.Arrays;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.effects.cause.EffectCause;
import cf.wyslmzc.yuhao.additions.addon.effects.cause.EffectCauseItemRightClick;
import cf.wyslmzc.yuhao.additions.addon.effects.cause.HandType;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiEffectCauseEditHandler;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBox;
import cf.wyslmzc.yuhao.additions.gui.view.components.GuiComponentDisplayText;
import cf.wyslmzc.yuhao.additions.gui.view.components.IGuiViewComponent;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentItemStackInput;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.dropdown.GuiComponentDropdownInput;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Creates components and handles saving the right click effect cause.
 * 
 * @author Rebeca Rey (Tmtravlr)
 * @date June 2019
 */
public class GuiEditHandlerEffectCauseItemRightClick implements IGuiEffectCauseEditHandler {
	
	private GuiEdit editScreen;
	private GuiComponentDisplayText description;
	private GuiComponentItemStackInput stackInput;
	private GuiComponentDropdownInput<HandType> handInput;

	@Override
	public List<IGuiViewComponent> createViewComponents(GuiEdit editScreen, EffectCause cause) {
		this.editScreen = editScreen;
		EffectCauseItemRightClick specificCause = (cause instanceof EffectCauseItemRightClick) ? (EffectCauseItemRightClick) cause : null;
		
		this.description = new GuiComponentDisplayText(editScreen, new TextComponentTranslation("type.effectCause.itemRightClicked.description"));
		
		this.stackInput = new GuiComponentItemStackInput(I18n.format("gui.edit.effectCause.item.item.label"), editScreen);
		this.stackInput.setRequired();
		this.stackInput.enableAnyDamage();
		this.stackInput.disableCount();
		if (specificCause != null) {
			this.stackInput.setDefaultItemStack(specificCause.itemStack);
		}
		
		this.handInput = new GuiComponentDropdownInput<HandType>(I18n.format("gui.edit.effectCause.held.hand.label"), editScreen) {
			
			@Override
			public String getSelectionName(HandType selected) {
				switch(selected) {
				case MAINHAND:
					return HandTypeLabel.MAINHAND_LABEL.getLabel();
				case OFFHAND:
					return HandTypeLabel.OFFHAND_LABEL.getLabel();
				case BOTH:
					return HandTypeLabel.BOTH_HANDS_LABEL.getLabel();
				default:
					return "";	
				}
			}
			
		};
		this.handInput.setSelections(HandType.values());
		this.handInput.setRequired();
		this.handInput.disallowDelete();
		if (specificCause != null) {
			this.handInput.setDefaultSelected(specificCause.handType);
		} else {
			this.handInput.setDefaultSelected(HandType.BOTH);
		}
		
		return Arrays.asList(this.description, this.stackInput, this.handInput);
	}

	@Override
	public EffectCause createEffectCause() {
		if (this.editScreen == null || this.stackInput == null || this.handInput == null) {
			return null;
		}
		
		if (this.stackInput.getItemStack().isEmpty()) {
			this.editScreen.mc.displayGuiScreen(new GuiMessageBox(this.editScreen, I18n.format("gui.edit.effectCause.problem.title"), new TextComponentTranslation("gui.edit.effectCause.item.problem.noItem.message"), I18n.format("gui.buttons.back")));
			return null;
		}
		
		if (this.handInput.getSelected() == null) {
			this.editScreen.mc.displayGuiScreen(new GuiMessageBox(this.editScreen, I18n.format("gui.edit.effectCause.problem.title"), new TextComponentTranslation("gui.edit.effectCause.held.problem.noHand.message"), I18n.format("gui.buttons.back")));
			return null;
		}
		
		EffectCauseItemRightClick cause = new EffectCauseItemRightClick();
		cause.itemStack = this.stackInput.getItemStack();
		cause.handType = this.handInput.getSelected();
		
		return cause;
	}

}
