import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Justin Priester, Dylan Mansour
 * The screen where the voter can register for an account.
 */
public final class RegisterScreen {
	public static Component createComponents(Controller controller)
	{
      JTextField username = new JTextField(2);
      JLabel usernameLabel = new JLabel("Username", SwingConstants.LEFT);
      
      JTextField password = new JTextField(2);
      JLabel passwordLabel = new JLabel("Password", SwingConstants.LEFT);
      
      JTextField age = new JTextField(2);
      JLabel ageLabel = new JLabel("Age", SwingConstants.LEFT);
      
      JTextField licenseID = new JTextField(2);
      JLabel licenseIDLabel = new JLabel("Driver's License ID", SwingConstants.LEFT);
		
		JButton buttonRegister = new JButton("Register");
      buttonRegister.setActionCommand("register account");
      buttonRegister.addActionListener(controller);
      
      JButton buttonCancel = new JButton("Cancel");
      buttonCancel.setActionCommand("switch to main");
      buttonCancel.addActionListener(controller);
      
      JPanel pane = new JPanel(new GridLayout(0, 2));
      pane.add(username); //0th component
      pane.add(usernameLabel);
      pane.add(password); //2nd component
      pane.add(passwordLabel);
      pane.add(age); //4th component
      pane.add(ageLabel);
      pane.add(licenseID); //6th component
      pane.add(licenseIDLabel);
      pane.add(buttonCancel);
      pane.add(buttonRegister);
      return pane;
  }
}
