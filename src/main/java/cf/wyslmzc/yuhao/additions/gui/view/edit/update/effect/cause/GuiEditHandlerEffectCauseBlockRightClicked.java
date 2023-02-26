package cf.wyslmzc.yuhao.additions.gui.view.edit.update.effect.cause;

import java.util.Arrays;
import java.util.List;

import cf.wyslmzc.yuhao.additions.addon.effects.cause.EffectCause;
import cf.wyslmzc.yuhao.additions.addon.effects.cause.EffectCauseBlockRightClicked;
import cf.wyslmzc.yuhao.additions.api.gui.IGuiEffectCauseEditHandler;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBox;
import cf.wyslmzc.yuhao.additions.gui.view.components.GuiComponentDisplayText;
import cf.wyslmzc.yuhao.additions.gui.view.components.IGuiViewComponent;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentBlockStateInfoInput;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentBooleanInput;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentNBTInput;
import cf.wyslmzc.yuhao.additions.gui.view.edit.GuiEdit;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Creates components and handles saving the block right clicked effect cause.
 * 
 * @author Rebeca Rey (Tmtravlr)
 * @date May 2021
 */
public class GuiEditHandlerEffectCauseBlockRightClicked implements IGuiEffectCauseEditHandler {
	
	private GuiEdit editScreen;
	private GuiComponentDisplayText description;
	private GuiComponentBlockStateInfoInput stateInput;
	private GuiComponentNBTInput nbtInput;
	private GuiComponentBooleanInput targetSelfInput;

	@Override
	public List<IGuiViewComponent> createViewComponents(GuiEdit editScreen, EffectCause cause) {
		this.editScreen = editScreen;
		EffectCauseBlockRightClicked specificCause = (cause instanceof EffectCauseBlockRightClicked) ? (EffectCauseBlockRightClicked) cause : null;
		
		this.description = new GuiComponentDisplayText(editScreen, new TextComponentTranslation("type.effectCause.blockRightClicked.description"));
		
		this.stateInput = new GuiComponentBlockStateInfoInput(I18n.format("gui.edit.effectCause.block.blockState.label"), editScreen);
		this.stateInput.setRequired();
		if (specificCause != null) {
			this.stateInput.setDefaultBlockStateInfo(specificCause.blockState);
		}
		
		this.nbtInput = new GuiComponentNBTInput(I18n.format("gui.edit.effectCause.block.blockTag.label"), editScreen);
		if (specificCause != null) {
			this.nbtInput.setDefaultText(specificCause.blockTag != null ? specificCause.blockTag.toString() : "{}");
		}
		
		this.targetSelfInput = new GuiComponentBooleanInput(I18n.format("gui.edit.effectCause.targetSelf.label"), editScreen);
		if (specificCause != null) {
			this.targetSelfInput.setDefaultBoolean(specificCause.targetSelf);
		}
		
		return Arrays.asList(this.description, this.stateInput, this.nbtInput, this.targetSelfInput);
	}

	@Override
	public EffectCause createEffectCause() {
		if (this.editScreen == null || this.stateInput == null || this.targetSelfInput == null) {
			return null;
		}
		
		if (this.stateInput.getBlockStateInfo() == null) {
			this.editScreen.mc.displayGuiScreen(new GuiMessageBox(this.editScreen, I18n.format("gui.edit.effectCause.problem.title"), new TextComponentTranslation("gui.edit.effectCause.block.problem.noBlockState.message"), I18n.format("gui.buttons.back")));
			return null;
		}
		
		EffectCauseBlockRightClicked cause = new EffectCauseBlockRightClicked();
		cause.blockState = this.stateInput.getBlockStateInfo();
		cause.blockTag = this.nbtInput.getTag();
		cause.targetSelf = this.targetSelfInput.getBoolean();
		
		return cause;
	}

}
