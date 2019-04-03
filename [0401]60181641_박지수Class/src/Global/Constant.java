package Global;


import Shape.Rectangle;
import Shape.Polygon;
import Shape.Shape;

public class Constant {
	// ���ڿ� ���ڸ� ������ ���̴�.

	public enum EMainFrame {

		x(200), y(100), w(400), h(600),;

		private int value;

		private EMainFrame(int value) {
			this.value = value;
		}

		
		public int getValue() {
			return this.value;
		}
	}

	public enum EMenu {

		fileMenu("File"), editMenu("Edit"),
		// ���߿� resource�� �����س��� ���� ����.
		;

		private String text;

		private EMenu(String Text) {
			this.text = Text;
		}

		public String getText() {
			return this.text;
		}

	}

	public enum EToolBar {

		 
		RectangleButton("�׸�", new Rectangle()),
		RectangleButton1("Rectangle", new Rectangle()),
		PolygonButton("������", new Polygon()),
		;

		private String text;
		private Shape shape;

		private EToolBar(String Text, Shape shape) {
			this.text = Text;
			this.shape = shape;
		}

		public String getText() {
			return this.text;
		}

		public Shape getShape() {
			return this.shape;
		}

	}

	public enum EMenuBar {

		NewItem("New"), OpenItem("Open"),;

		private String text;

		private EMenuBar(String Text) {
			this.text = Text;
		}

		public String getText() {
			return this.text;
		}

	}

}
