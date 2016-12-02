import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Justin Priester, Dylan Mansour
 * The screen where the voter can choose to either register or login.
 */
public final class MainScreen
{	
	public static Component createComponents(Controller controller)
	{
      JButton buttonLogin = new JButton("Login");
      buttonLogin.setActionCommand("switch to login");
      buttonLogin.addActionListener(controller);
      
      JButton buttonRegister = new JButton("Register");
      buttonRegister.setActionCommand("switch to register");
      buttonRegister.addActionListener(controller);
      
      JPanel pane = new JPanel(new GridLayout(0, 1));
      pane.add(buttonLogin);
      pane.add(buttonRegister);
      return pane;
  }
}
