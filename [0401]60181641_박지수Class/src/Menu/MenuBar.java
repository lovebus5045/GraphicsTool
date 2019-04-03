package Menu;
import javax.swing.JMenuBar;

import Global.Constant.EMenu;
import drawingPanel.DrawingPanel;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	// component
	private FileMenu fileMenu;
	
	// association
	private DrawingPanel drawingPanel;

	public void associate(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public MenuBar() {
		this.fileMenu = new FileMenu(EMenu.fileMenu.getText());
		this.add(this.fileMenu);

		
		// 메뉴를 하나 만들면 2줄씩 추가해주어야한다.
		// 현재 누르면 작동을 안하는데 class를 분리해서 독립적으로 작동하게 만들어야한다.
	}

}
