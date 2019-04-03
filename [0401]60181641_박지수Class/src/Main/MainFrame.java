package Main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import Global.Constant.EMainFrame;
import Menu.MenuBar;
import Toolbar.ToolBar;
import drawingPanel.DrawingPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//component
	private MenuBar menuBar;
	private ToolBar toolBar;
    private DrawingPanel drawingPanel;
    
    
	public MainFrame() {
		// constructor
        //attribute
		this.setLocation(EMainFrame.x.getValue(), EMainFrame.y.getValue());
		this.setLayout(new BorderLayout());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 위에 3가지는 프로그램을 짰을 때 기본적으로 진행해야한다.
		
		
		//menu
		this.menuBar = new MenuBar();
		this.setJMenuBar(this.menuBar);
		
		//toolBar
		this.toolBar = new ToolBar();
		this.add(this.toolBar,BorderLayout.NORTH);
		
		//drawingPaenl
		this.drawingPanel = new DrawingPanel();
		this.add(this.drawingPanel,BorderLayout.CENTER);
		
		//association
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
		
	}
}
