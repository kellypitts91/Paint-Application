//package nz.ac.massey.graphics.assignment1;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawing {

	private ControlPanel m_control;
	private ContentPanel m_content;
	private static JMenuItem quit, about;
	public static int x1 = -1, y1 = -1, x2 = -1, y2 = -1;

	public Drawing(final ControlPanel control, final ContentPanel content) {
		m_control = control;
		m_content = content;

		// register for events
		m_content.setController(this.new ContentEventHandler());
	}

  /**
   * A ContentEventHandler handles events from the ContentPanel view.
   */
	private class ContentEventHandler extends MouseInputAdapter implements
      ContentPanel.ContentViewListener {
			
		@Override
		public void mousePressed(MouseEvent event) {
		//get coordinates for mouse press
			x1 = event.getX();
			y1 = event.getY();
		}

		@Override
		public void mouseReleased(MouseEvent event) {
      //get coordinates for mouse release
			x2 = event.getX();
			y2 = event.getY();
			//storing shape and colour in temp variables for readability
			String tempName = m_control.getCurrentShape().name().toString();
			Color tempColor = m_control.getCurrentColour();
			if(tempName  != "Text") {
				m_content.listOfShapes.add(new Shape(tempName, 
							tempColor, x1, y1, x2, y2, m_control.getCurrentShapeFillSetting()));
			}
			else {
				m_content.listOfShapes.add(new Shape(tempName, 
							tempColor, x1, y1, m_control.getCurrentText()));
			}
			m_content.repaint();
		}
	}

  /**
   * Creates the application GUI.
   */
	private static void createGui() {
		// set-up the frame
		final JFrame frame = new JFrame("Drawing");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Menu -- modified from Arno example code.
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu ApplicationMenu = new JMenu("Application");
		menu.add(ApplicationMenu);
		
		quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ApplicationMenu.add(quit);
		
		JMenu helpMenu = new JMenu("Help");
		menu.add(helpMenu);
		
		about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Kelly Pitts");
			}
		});
		helpMenu.add(about);
		
		// set up the content pane
		final JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		
		// create the control and content panels
		final ControlPanel control = new ControlPanel();
		final ContentPanel content = new ContentPanel();
		contentPane.add(control, BorderLayout.PAGE_START);
		contentPane.add(content, BorderLayout.CENTER);
		
		// create the controller
		new Drawing(control, content);
		// adjust the frame size to fit its contents and make it visible
		frame.pack();
		frame.setVisible(true);
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getY2() {
		return y2;
	}

	public static void main(String[] args) {
		System.out.println("-------------------------------------");
		System.out.println("159.235 Assignment 1, Semester 2 2016");
		System.out.println("Submitted by: Pitts, Kelly, 09098321");
		System.out.println("-------------------------------------");

		SwingUtilities.invokeLater(Drawing::createGui);
	}
}
