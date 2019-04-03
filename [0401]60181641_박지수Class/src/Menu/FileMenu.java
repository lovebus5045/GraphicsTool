package Menu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Global.Constant.EMenuBar;


public class FileMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	
	private JMenuItem newItem;
	
	public FileMenu(String text) {
		super(text);
		
		this.newItem = new JMenuItem(EMenuBar.NewItem.getText());
		this.add(newItem);
	}	
}

