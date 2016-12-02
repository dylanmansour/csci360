import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;

/**
 * Justin Priester, Dylan Mansour
 * The main window of the software that organizes the multiple screens of the
 * software.
 */
public class MasterWindow
{
	Controller controller; //The controller to send signals to
	
	public MasterWindow(Controller controller)
	{
		this.controller = controller;
	}
	
	private void createAndShowGUI()
	{
		//Make sure we have nice window decorations.
      JFrame.setDefaultLookAndFeelDecorated(true);
      
      //Create and set up the window.
      JFrame frame = new JFrame("Main Menu");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Component mainScreen = MainScreen.createComponents(controller);
      frame.getContentPane().add(mainScreen, BorderLayout.CENTER);
      
      //Display the window.
      frame.pack();
      frame.setVisible(true);
	}
	
	public void createWindow()
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
}
