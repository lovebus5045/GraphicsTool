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
	};// 2������ ����� �ٸ��� polygondrawing�� 2pointdrawing���� ���ƶ�

	private EActionState eActionState;

	private MouseHandler mouseHandler;
	private Vector<Shape> shapeVector;
	//currentTool�� ���� �� �ְ� �и��Ǿ���Ѵ�.
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

		this.shapeVector = new Vector<Shape>();// �޸𸮰� �ִ� �� �ø� �� �ִ�.
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
        //� tool�� ���� �Ǿ������� �����ؼ� �� shape�� �����ؾ� �Ѵ�. ���� �̰��� rectangle�� �־����� rectangle�� ���� ���� �ƴ϶� �װ��� ī���ؼ� ���ο� ��ü�� �����ؾ� �Ѵ�.
		//�׸��� �׸� ������ ��ü�� �ϳ��� ����� ����Ѵ�. New�� ���ؼ�Memory������ Ȯ�� ������Ѵ�. �׷��� InitDrawing�� �ϸ� copy�ؼ� �׷� ����Ѵ�.
		//CurrentTool�� �����ڷ�� ����� ���� ���̴�.�Ϲ����� ���δ� class�� ������ ���� �� �� ���� ������ ��ü�� ���� �Ŀ� �����ϴ� ����� ����.
		//�ϳ��� �׸����� �ι�° ���� �׸��� �Ǹ� ���� n+1��°�� �������� �ȴ�. �׷��� �Ǹ� �� ���� �����ٰ� �̾ �׸��� �Ǽ� �������� �̾����� �ȴ� �׷����� New�� ���ؼ� ���ο� PolygonȤ�� Recatngel�� ����� ����Ѵ�.
		//cloning�̶�� �ؼ� ��ü�� ������ �ڱ� �ڽ��� ���� �� �� �־�� �Ѵ�. 
		this.CurrentShape = this.CurrentTool.clone();
		//currentToole�� ���� �ؼ� �׸��� �׸� �� ���� ���Ӱ� �׷��� Vector���ٰ� ���̴� ���̴�.
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
		public void mouseDragged(MouseEvent e) {// ���⼭ �����
			if (eActionState == EActionState.e2pDrawing) {
				KeepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			/* ���콺�� ������ �۵� */}

		@Override
		public void mouseExited(MouseEvent e) {
			/* ���콺�� ������ �� shaed */}
	}

}
