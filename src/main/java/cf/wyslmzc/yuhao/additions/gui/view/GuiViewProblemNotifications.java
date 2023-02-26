package cf.wyslmzc.yuhao.additions.gui.view;

import java.util.ArrayList;
import java.util.List;

import cf.wyslmzc.yuhao.additions.gui.view.components.GuiComponentDisplayText;
import cf.wyslmzc.yuhao.additions.util.ProblemNotifier;

import net.minecraft.client.gui.GuiScreen;

public class GuiViewProblemNotifications extends GuiView {
	
	private List<GuiComponentDisplayText> notificationLines = new ArrayList<>();

	public GuiViewProblemNotifications(GuiScreen parentScreen, String title) {
		super(parentScreen, title);
	}

	@Override
	public void initComponents() {
		notificationLines.clear();
		
		notificationLines.add(new GuiComponentDisplayText(this, ProblemNotifier.notificationProblemCount));
		notificationLines.add(new GuiComponentDisplayText(this, ProblemNotifier.notificationFileLocation));
		ProblemNotifier.notificationLines.forEach(line -> notificationLines.add(new GuiComponentDisplayText(this, line)));
		
		notificationLines.forEach(line -> line.setIgnoreLabel(true));
		
		this.components.addAll(notificationLines);
	}

}
