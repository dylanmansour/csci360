import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Justin Priester, Dylan Mansour
 * The main window of the software that organizes the multiple screens of the
 * software.
 */
public class MasterWindow
{
	private Controller controller; //The controller to send signals to
	private JFrame frame;
	private String menuName;
	
	public MasterWindow(Controller controller)
	{
		this.controller = controller;
		createWindow();
		menuName = "";
	}
	
	public String getMenuName()
	{
		return menuName;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	private void createAndShowGUI()
	{
		//Make sure we have nice window decorations.
      JFrame.setDefaultLookAndFeelDecorated(true);
      
      //Create and set up the window.
      frame = new JFrame("Voting Software");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Component mainScreen = MainScreen.createComponents(controller);
      frame.getContentPane().add(mainScreen, BorderLayout.CENTER);
      menuName = "main";
      
      //Display the window.
      frame.pack();
      frame.setVisible(true);
	}
	
	private void createWindow()
	{
		//Schedule a job for the event-dispatching thread:
      //creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(new Runnable()
      {
          public void run()
          {
              createAndShowGUI();
          }
      });
	}
	
	public void changeScreen(String menuName)
	{	
		Component screen;
		Container pane = frame.getContentPane();
		this.menuName = menuName;
		
		if (menuName.equals("main"))
		{
			screen = MainScreen.createComponents(controller);
			pane.removeAll();
			pane.add(screen, BorderLayout.CENTER);
		}
		else if (menuName.equals("register"))
		{
			screen = RegisterScreen.createComponents(controller);
			pane.removeAll();
			pane.add(screen, BorderLayout.CENTER);
		}
		else if (menuName.equals("login"))
		{
			screen = LoginScreen.createComponents(controller);
			pane.removeAll();
			pane.add(screen, BorderLayout.CENTER);
		}
		else if (menuName.equals("vote"))
		{
			screen = VoteScreen.createComponents(controller);
			pane.removeAll();
			pane.add(screen, BorderLayout.CENTER);
		}
		
		frame.pack();
      frame.setVisible(true);
	}
	
	public Component getMenuComponent(int n)
	{
		JPanel panel = (JPanel)frame.getContentPane().getComponent(0);
		
		return panel.getComponent(n);
	}
}
