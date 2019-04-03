package drawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import Global.Constant.EToolBar;
import Shape.Shape;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private enum EActionState {
		eReady, e2pDrawing, eNPDrawing
	};// 2가지의 방법이 다르다 polygondrawing과 2pointdrawing으로 막아라

	private EActionState eActionState;

	private MouseHandler mouseHandler;
	private Vector<Shape> shapeVector;
	//currentTool과 복제 한 애가 분리되어야한다.
	private Shape CurrentShape;
	private Shape CurrentTool;

	public void setCurrentTool(EToolBar currentToole) {
		this.CurrentTool = currentToole.getShape();
	}

	public DrawingPanel() {
		this.eActionState = EActionState.eReady;

		this.setBackground(Color.white);
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);

		this.shapeVector = new Vector<Shape>();// 메모리가 있는 한 늘릴 수 있다.
		CurrentTool = EToolBar.RectangleButton.getShape();
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
       for(Shape shape:this.shapeVector) {
    	   shape.draw(graphics);
       }
     
	}

	private void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		this.CurrentShape.draw(graphics);

	}

	private void initDrawing(int x, int y) {
        //어떤 tool이 선택 되었는지를 선택해서 그 shape을 복사해야 한다. 만약 이곳에 rectangle이 있었으면 rectangle을 쓰는 것이 아니라 그것을 카피해서 새로운 객체를 형성해야 한다.
		//그림을 그릴 때마다 객체를 하나씩 만들어 줘야한다. New를 통해서Memory공간을 확보 해줘야한다. 그래서 InitDrawing을 하면 copy해서 그려 줘야한다.
		//CurrentTool은 참고자료로 쓸라고 쓰는 것이다.일반적인 언어로는 class의 정보를 가져 올 수 없기 때문에 객체를 만든 후에 복사하는 방식을 쓴다.
		//하나는 그리지만 두번째 꺼를 그리게 되면 점을 n+1번째에 찍어버리게 된다. 그렇게 되면 그 다음 점에다가 이어서 그리게 되서 폴리곤이 이어지게 된다 그럼으로 New를 통해서 새로운 Polygon혹은 Recatngel을 만들어 줘야한다.
		//cloning이라고 해서 객체가 스스로 자기 자신을 복제 할 수 있어야 한다. 
		this.CurrentShape = this.CurrentTool.clone();
		//currentToole을 복제 해서 그림을 그릴 때 마다 새롭게 그려서 Vector에다가 붙이는 것이다.
		this.CurrentShape.setOrign(x, y);
		this.drawShape();
	

	}

	private void KeepDrawing(int x, int y) {
		this.drawShape();
		this.CurrentShape.setPoint(x, y);
		this.drawShape();

	}

	private void ContinueDrawing(int x, int y) {
		this.CurrentShape.addPoint(x, y);

	}

	private void FinishDrawing(int x, int y) {
		
		this.shapeVector.add(CurrentShape);
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);

			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		private void mouse1Clicked(MouseEvent e) {
			if (eActionState == EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState = EActionState.eNPDrawing;
			} else if (eActionState == EActionState.eNPDrawing) {
				ContinueDrawing(e.getX(), e.getY());
			}

		}

		private void mouse2Clicked(MouseEvent e) {
			if (eActionState == EActionState.eNPDrawing) {
				FinishDrawing(e.getX(), e.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eActionState == EActionState.eNPDrawing) {
				KeepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (eActionState == EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState = EActionState.e2pDrawing;
			}

			if (eActionState == EActionState.eReady) {
				initDrawing(e.getX(), e.getY());
				eActionState = EActionState.e2pDrawing;
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eActionState == EActionState.e2pDrawing) {
				FinishDrawing(e.getX(), e.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {// 여기서 지운다
			if (eActionState == EActionState.e2pDrawing) {
				KeepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			/* 마우스가 들어오면 작동 */}

		@Override
		public void mouseExited(MouseEvent e) {
			/* 마우스가 나가면 끝 shaed */}
	}

}
