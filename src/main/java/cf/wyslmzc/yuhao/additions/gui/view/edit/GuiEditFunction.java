package cf.wyslmzc.yuhao.additions.gui.view.edit;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Predicate;
import cf.wyslmzc.yuhao.additions.AdditionsMod;
import cf.wyslmzc.yuhao.additions.addon.Addon;
import cf.wyslmzc.yuhao.additions.addon.functions.FunctionAdded;
import cf.wyslmzc.yuhao.additions.gui.message.GuiMessageBox;
import cf.wyslmzc.yuhao.additions.gui.view.GuiView;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentListInput;
import cf.wyslmzc.yuhao.additions.gui.view.components.input.GuiComponentStringInput;
import cf.wyslmzc.yuhao.additions.type.AdditionTypeFunction;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Page for adding a function or editing an existing one.
 * 
 * @author Tmtravlr (Rebeca Rey)
 * @since August 2018
 */
public class GuiEditFunction extends GuiEdit {
	
	private Addon addon;
	
    private boolean isNew;
    private FunctionAdded oldFunction;
    private FunctionAdded copyFrom;

    private GuiComponentStringInput idInput;
	private GuiComponentListInput<GuiComponentStringInput> commandInput;
    
	public GuiEditFunction(GuiScreen parentScreen, String title, Addon addon, FunctionAdded function) {
		super(parentScreen, title);
		this.addon = addon;
		this.isNew = function == null;
		this.oldFunction = function;
	}

	@Override
	public void initComponents() {
		
		this.idInput = new GuiComponentStringInput(I18n.format("gui.edit.function.id.label"), this);
		if (this.isNew) {
			this.idInput.setRequired();
			this.idInput.setMaxStringLength(32);
			this.idInput.setInfo(new TextComponentTranslation("gui.edit.id.info"));
			this.idInput.setValidator(input -> input.matches("[a-z0-9\\_]*"));
		} else {
			this.idInput.setEnabled(false);
			this.idInput.setMaxStringLength(1024);
			this.idInput.setInfo(new TextComponentTranslation("gui.edit.id.noEdit.info"));
			this.idInput.setDefaultText(this.oldFunction.id.toString());
		}
		
		this.commandInput = new GuiComponentListInput<GuiComponentStringInput>(I18n.format("gui.edit.function.commands.label"), this) {

			@Override
			public GuiComponentStringInput createBlankComponent() {
				GuiComponentStringInput input = new GuiComponentStringInput("", this.editScreen);
				input.setMaxStringLength(Integer.MAX_VALUE);
				return input;
			}
			
		};
		this.commandInput.setRequired();
		if (!this.isNew) {
			this.oldFunction.commands.forEach(toAdd -> {
				GuiComponentStringInput input = this.commandInput.createBlankComponent();
				input.setDefaultText(toAdd);
				this.commandInput.addDefaultComponent(input);
			});
		}
		
		if (this.copyFrom != null) {
			this.copyFromOther();
		}
		
		this.components.add(this.idInput);
		this.components.add(this.commandInput);
	}
	
	@Override
	public void saveObject() {
		ResourceLocation location = this.isNew ? new ResourceLocation(AdditionsMod.MOD_ID, this.addon.id + "-" + this.idInput.getText()) : this.oldFunction.id;
		
		if (this.idInput.getText().isEmpty()) {
			this.mc.displayGuiScreen(new GuiMessageBox(this, I18n.format("gui.edit.function.problem.title"), new TextComponentTranslation("gui.edit.problem.noId", location), I18n.format("gui.buttons.back")));
			return;
		}
		
		if (this.commandInput.getComponents().isEmpty()) {
			this.mc.displayGuiScreen(new GuiMessageBox(this, I18n.format("gui.edit.function.problem.title"), new TextComponentTranslation("gui.edit.function.problem.noCommands", location), I18n.format("gui.buttons.back")));
			return;
		}
		
		if (this.isNew && AdditionTypeFunction.INSTANCE.hasFunctionWithId(this.addon, location)) {
			this.mc.displayGuiScreen(new GuiMessageBox(this, I18n.format("gui.edit.function.problem.title"), new TextComponentTranslation("gui.edit.function.problem.duplicate", location), I18n.format("gui.buttons.back")));
			return;
		}
		
		List<String> commands = this.commandInput.getComponents().stream().map(GuiComponentStringInput::getText).collect(Collectors.toList());
		
		FunctionAdded function = new FunctionAdded(location, commands);

		if (!this.isNew) {
			AdditionTypeFunction.INSTANCE.deleteAddition(this.addon, this.oldFunction);
		}
		AdditionTypeFunction.INSTANCE.saveAddition(this.addon, function);
		
		if (this.parentScreen instanceof GuiView) {
			((GuiView) this.parentScreen).refreshView();
		}
		
		this.mc.displayGuiScreen(new GuiMessageBox(this.parentScreen, I18n.format("gui.edit.function.success.title"), new TextComponentTranslation("gui.edit.function.success.message"), I18n.format("gui.buttons.continue")));
	}
	
	@Override
	public void refreshView() {
		List<String> commands = this.commandInput.getComponents().stream().map(GuiComponentStringInput::getText).collect(Collectors.toList());
		this.commandInput.removeAllComponents();
		commands.forEach(toAdd -> {
			GuiComponentStringInput input = this.commandInput.createBlankComponent();
			input.setDefaultText(toAdd);
			this.commandInput.addDefaultComponent(input);
		});
	}
	
	public void copyFrom(FunctionAdded function) {
		this.copyFrom = function;
		
		if (this.initializedComponents) {
			this.copyFromOther();
		}
	}
	
	public void copyFromOther() {
		this.commandInput.removeAllComponents();
		this.copyFrom.commands.forEach(toAdd->{
			GuiComponentStringInput input = this.commandInput.createBlankComponent();
			input.setDefaultText(toAdd);
			this.commandInput.addDefaultComponent(input);
		});
		
		this.copyFrom = null;
	}

}
