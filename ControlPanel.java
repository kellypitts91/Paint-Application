//package nz.ac.massey.graphics.assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The control panel implements a pull mechanism to retrieve the current user
 * settings.
 */
public class ControlPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

  /**
   * The geometric shapes supported by this control panel.
   */
	//Creating radio buttons, Rectangle ticked by default.
	private JRadioButton rectangleButton = new JRadioButton("Rectangle", true);
	private JRadioButton circleButton = new JRadioButton("Circle");
	private JRadioButton ellipseButton = new JRadioButton("Ellipse");
	private JRadioButton lineButton = new JRadioButton("Line");
	private JRadioButton textButton = new JRadioButton("Text");
	
	//Creating tools for the options at top of frame.
	private JColorChooser colorChooser = new JColorChooser(Color.BLACK);	
	private JButton ColourChooserBtn = new JButton("Choose a colour");
	private JCheckBox fillCB = new JCheckBox("Fill");
	private JTextField textArea = new JTextField("Press Text first.");
	
	//declaring private variables
	private static String shapeSelected = "";
	private static String text = "";
	private static int fillState = 0;
	private static Color color = Color.black;
	
	public enum MyShape {
		Rectangle, Circle, Ellipse, Line, Text, Unknown
	}

	public ControlPanel() {
		super(new GridBagLayout());
		
		//Setting default constraints for GridBag Layout -- modified from Arno example code.
		final GridBagConstraints cDefault = new GridBagConstraints();
		cDefault.gridx = GridBagConstraints.RELATIVE;
		cDefault.fill = GridBagConstraints.HORIZONTAL;
		cDefault.insets = new Insets(2, 0, 2, 10);
		cDefault.anchor = GridBagConstraints.LINE_START;
		
		//Calling all methods
		addActionListenersToButtons();
		ShapeButtons(cDefault);
		Properties(cDefault);
		TextArea(cDefault);
	}
	
	//Creating action listeners to all buttons.
	public void addActionListenersToButtons() {
		
		//Researched and modified all action listeners from Arno and Stack overflow
		rectangleButton.setActionCommand("Rectangle");
		rectangleButton.addActionListener(this);
		
		circleButton.setActionCommand("Circle");
		circleButton.addActionListener(this);
		
		ellipseButton.setActionCommand("Ellipse");
		ellipseButton.addActionListener(this);
		
		lineButton.setActionCommand("Line");
		lineButton.addActionListener(this);
		
		textButton.setActionCommand("Text");
		textButton.addActionListener(this);
		
		//unenabling textArea so cant type anything until user clicks "Text" radio button.
		textArea.setEnabled(false);
		textArea.setActionCommand("TextBox");
		textArea.addActionListener(this);
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
			}
		});
		
		ColourChooserBtn.setActionCommand("ColourBtn");
		ColourChooserBtn.addActionListener(this);
		
		fillCB.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				fillState = ((e.getStateChange()==1? 1 : 0));
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//determines which button has been pressed
		textArea.setEnabled(false);
		switch(e.getActionCommand()) {
			case "Rectangle":
				shapeSelected = "Rectangle";
				break;
			case "Circle":
				shapeSelected = "Circle";
				break;
			case "Ellipse":
				shapeSelected = "Ellipse";
				break;
			case "Line":
				shapeSelected = "Line";
				break;
			case "Text":
				//enables text area and prompts user to press enter after typing
				shapeSelected = "Text";
				textArea.setEnabled(true);
				textArea.setText("Enter text followed by enter.");
				break;
			case "TextBox":
				text = textArea.getText();
				break;
			case  "ColourBtn":
				color = JColorChooser.showDialog(null, "Choose a colour", Color.BLACK);
				ColourChooserBtn.setBackground(color);
				break;
		}
	}
	
	public void ShapeButtons(GridBagConstraints Default) {
		//positions radio buttons and groups them together
		//all gridbag constraints were researched from java documentation and Arno demo program.
		GridBagConstraints shapeBtn = null;
		
		shapeBtn = (GridBagConstraints) Default.clone();
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(rectangleButton);
		buttons.add(circleButton);
		buttons.add(ellipseButton);
		buttons.add(lineButton);
		buttons.add(textButton);
		
		//row 1
		shapeBtn.gridy = 0;
		add(new JLabel("Select a shape: "), shapeBtn);
		add(rectangleButton, shapeBtn);
		add(circleButton, shapeBtn);
		add(ellipseButton, shapeBtn);
		add(lineButton, shapeBtn);
		add(textButton, shapeBtn);
	}
	
	public void Properties(GridBagConstraints Default) {
		//positions colour button on 2nd row
		GridBagConstraints properties = null;
		
		properties = (GridBagConstraints) Default.clone();
		//row 2
		properties.gridy = 1;
		add(new JLabel("Properties: "), properties);
		
		//adding colour chooser button		
		properties.gridwidth = 2;	//sets button to width of 2 cells
		properties.ipady = 15;
		properties.insets = new Insets(2, 2, 2, 2);
		ColourChooserBtn.setBackground(getCurrentColour());
		add(ColourChooserBtn, properties);
		add(fillCB, properties);
	}
	
	public void TextArea(GridBagConstraints Default) {
		//Positions text field box on 3rd row
		GridBagConstraints text = null;
		
		text = (GridBagConstraints) Default.clone();
		text.insets.right = 0;
		text.gridx = 0;
		//row 3
		text.gridy = 2;
		add(new JLabel("Enter some text: "), text);
		
		text.gridx = 1;
		text.insets.right = 0;
		text.gridwidth = GridBagConstraints.REMAINDER;
		text.fill = GridBagConstraints.HORIZONTAL;
		text.weightx = 2;
		text.weighty = 2;
		add(new JScrollPane(textArea), text);
	}
	//returns current colour
	public Color getCurrentColour() {
		return color;
	}
	
	//determines and returns the shape selected from radio button
	public MyShape getCurrentShape() {
		switch(shapeSelected) {
			case "Circle":
				return MyShape.Circle;
			case "Ellipse":
				return MyShape.Ellipse;
			case "Line":
				return MyShape.Line;
			case "Text":
				return MyShape.Text;
			default:
					return MyShape.Rectangle;
		}
	}
	
	//returns the text entered in the text field
	public String getCurrentText() {
		return text;
	}
	
	//returns if the checkbox (fill) is ticked or not
	//1 for true and 0 for false
	public int getCurrentShapeFillSetting() {
		return fillState;
	}
}