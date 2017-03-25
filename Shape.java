import javax.swing.*;
import java.awt.*;

public class Shape {
	//Declaring private variables
	private String shapeName;
	private String text;
	private Color color;
	private int x, y, w, h;
	private int fillState = 0;
	
	//Constructor for all shapes and line
	public Shape(String s, Color c, int x1, int y1, int x2, int y2, int fill) {
		shapeName = s;
		color = c;
		x = x1;
		y = y1;
		w = x2;
		h = y2;
		fillState = fill;
	}
	
	//Constructor for Text
	public Shape(String sName, Color c, int x1, int y1, String t) {
		shapeName = sName;
		color = c;
		x = x1;
		y = y1;
		text = t;
	}
	
	//Getters for all variables
	public String getShapeName() { return shapeName; }
	public String getText() { return text; }
	public Color getColor() { return color; }
	public int getX1() { return x; }
	public int getY1() { return y; }
	public int getX2() { return w; }
	public int getY2() { return h; }
	public int getFillState() { return fillState; }
}