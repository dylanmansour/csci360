import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Justin Priester, Dylan Mansour
 * The screen where the voter can either log into an account or opt to register
 * for one (switch to the register screen).
 */
public final class LoginScreen {
	public static Component createComponents(Controller controller)
	{
      JTextField username = new JTextField(2);
      JLabel usernameLabel = new JLabel("Username", SwingConstants.LEFT);
      
      JTextField password = new JTextField(2);
      JLabel passwordLabel = new JLabel("Password", SwingConstants.LEFT);
		
		JButton buttonLogin = new JButton("Login");
      buttonLogin.setActionCommand("login account");
      buttonLogin.addActionListener(controller);
      
      JButton buttonCancel = new JButton("Cancel");
      buttonCancel.setActionCommand("switch to main");
      buttonCancel.addActionListener(controller);
      
      JPanel pane = new JPanel(new GridLayout(0, 2));
      pane.add(username);
      pane.add(usernameLabel);
      pane.add(password);
      pane.add(passwordLabel);
      pane.add(buttonCancel);
      pane.add(buttonLogin);
      return pane;
  }
}
