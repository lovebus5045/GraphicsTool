package Global;


import Shape.Rectangle;
import Shape.Polygon;
import Shape.Shape;

public class Constant {
	// 글자와 숫자를 정리할 것이다.

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
		// 나중에 resource를 분해해놓는 것이 좋다.
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

		 
		RectangleButton("네모", new Rectangle()),
		RectangleButton1("Rectangle", new Rectangle()),
		PolygonButton("폴리곤", new Polygon()),
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
