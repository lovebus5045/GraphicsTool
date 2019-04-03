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

		
		// �޴��� �ϳ� ����� 2�پ� �߰����־���Ѵ�.
		// ���� ������ �۵��� ���ϴµ� class�� �и��ؼ� ���������� �۵��ϰ� �������Ѵ�.
	}

}
