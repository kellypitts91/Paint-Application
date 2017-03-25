//package nz.ac.massey.graphics.assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * The ContentPanel implements the canvas and forwards any user events to the
 * registered controller.
 */
public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int x, y, w, h;
	public static ArrayList<Shape> listOfShapes = new ArrayList<Shape>();
	ControlPanel control = new ControlPanel();
	
  /**
   * The callback interface for controllers of this class.
   */
	public interface ContentViewListener extends MouseListener {
	}

	public ContentPanel() {
		super(null); // no layout manager
		setPreferredSize(new Dimension(600, 400));
		setOpaque(true);
	}

  /**
   * Registers the controller for this view, which will be notified of all user
   * actions.
   *
   * @param controller The controller.
   */
	public void setController(final ContentViewListener controller) {
		// update the event listeners
		addMouseListener(controller);
	}

	@Override
	protected void paintComponent(Graphics g) {
		//create new graphics object
		final Graphics2D graphics = (Graphics2D) g.create();
		graphics.setBackground(Color.white);
		graphics.clearRect(0, 0, getWidth(), getHeight());
		
		//checking mouse clicks have been registered first
		if(x1.getX1() != -1 || x2.getX2() != -1 || y1.getY1() != -1 || y2.getY2() != -1) {
			GetShapeSelected(graphics);
		}
		graphics.dispose();
	}
	
	public void GetShapeSelected(Graphics2D g) {
		
		//goes through every shape in the array list
		for(int i = 0; i < listOfShapes.size(); i++) {
			g.setColor(listOfShapes.get(i).getColor());
			//getting the minimum values for x and y.
			//getting the absolute value so width and height will not be negative values
			//taken from stack overflow.
			x = Math.min(listOfShapes.get(i).getX1(), listOfShapes.get(i).getX2());
			y = Math.min(listOfShapes.get(i).getY1(), listOfShapes.get(i).getY2());
			w = Math.abs(listOfShapes.get(i).getX1() - listOfShapes.get(i).getX2());
			h = Math.abs(listOfShapes.get(i).getY1() - listOfShapes.get(i).getY2());
			
			//determines which shape to draw
			switch(listOfShapes.get(i).getShapeName()) {
				case "Rectangle":
				drawRectangle(g, i);
				break;
			case "Circle":
				drawCircle(g, i);
				break;
			case "Ellipse":
				drawEllipse(g, i);
				break;
			case "Line":
				drawingLine(g, i);
				break;
			case "Text":
				drawText(g, i);
				break;
			}
		}
	}
	
	public void drawRectangle(Graphics2D g1, int i) {
		//determines if filled in or outline only.
		switch(listOfShapes.get(i).getFillState()) {
			case 1:
				g1.fillRect(x, y, w, h);
				break;
			case 0:
				g1.drawRect(x, y, w, h);
				break;
		}
	}

	public void drawCircle(Graphics2D g1, int i) {
		//checks if width or height is bigger then uses the biggest value to draw perfect circle 
		int size = 0;
		if(w >= h) {
			size = w;
		}
		else {
			size = h;
		}
		switch(listOfShapes.get(i).getFillState()) {
			case 1:
				g1.fillOval(x, y, size, size);
				break;
			case 0:
				g1.drawOval(x, y, size, size);
				break;
		}
	}
	
	public void drawEllipse(Graphics2D g1, int i) {
		
		switch(listOfShapes.get(i).getFillState()) {
			case 1:
				g1.fillOval(x, y, w, h);
				break;
			case 0:
				g1.drawOval(x, y, w, h);
				break;
		}
	}
	
	public void drawingLine(Graphics2D g1, int i) {
		//used the same concept from GetShapeSelected()
		int x1 = Math.min(listOfShapes.get(i).getX1(), listOfShapes.get(i).getX2());
		int y1 = Math.min(listOfShapes.get(i).getY1(), listOfShapes.get(i).getY2());
		int x2 = Math.max(listOfShapes.get(i).getX1(), listOfShapes.get(i).getX2());
		int y2 = Math.max(listOfShapes.get(i).getY1(), listOfShapes.get(i).getY2());
		
		//checking which way the user is clicking so can draw from any dirrection
		if((listOfShapes.get(i).getX1() <= listOfShapes.get(i).getX2() && 
			listOfShapes.get(i).getY1() <= listOfShapes.get(i).getY2()) || 
			(listOfShapes.get(i).getX1() >= listOfShapes.get(i).getX2() && 
			listOfShapes.get(i).getY1() >= listOfShapes.get(i).getY2())) {
				
			g1.drawLine(x1, y1, x2, y2);
		}
		else {
			g1.drawLine(x1, y2, x2, y1);
		}
	}
	
	public void drawText(Graphics2D g1, int i) {
		g1.drawString(listOfShapes.get(i).getText(), listOfShapes.get(i).getX1(), listOfShapes.get(i).getY1());
	}
}
