import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Justin Priester, Dylan Mansour
 * The screen where the voter can view candidate information and submit a vote.
 */
public abstract class VoteScreen
{
	public static Component createComponents(Controller controller)
	{
		final int numButtons = 2;
		final ButtonGroup group = new ButtonGroup();
		
      JRadioButton[] radioButtons = new JRadioButton[numButtons];
      JButton voteButton = null;
      JButton logoutButton = null;
      
      //sample candidate
 		Candidate bob = new Candidate();
 		bob.setName("Bob");
 		bob.setParty("Builder");
 		
 		//sample candidate
 		Candidate bill = new Candidate();
 		bill.setName("Bill");
 		bill.setParty("Science");
      
      radioButtons[0] = new JRadioButton(
	      "<html>" + bob.getName() + ": <font color=red>" + bob.getParty() + " Party</font></html>");
	   radioButtons[0].setActionCommand(bob.getName());
	
	   radioButtons[1] = new JRadioButton(
	   	"<html>" + bill.getName() + ": <font color=red>" + bill.getParty() + " Party</font></html>");
	   radioButtons[1].setActionCommand(bill.getName());
	   
	   for (int i = 0; i < numButtons; i++)
	   {
         group.add(radioButtons[i]);
	   }
	   
	   //Select the first button by default.
      radioButtons[0].setSelected(true);
      
      voteButton = new JButton("Vote");
      voteButton.setActionCommand("cast vote");
      voteButton.addActionListener(controller);
      
      logoutButton = new JButton("Logout");
      logoutButton.setActionCommand("logout");
      logoutButton.addActionListener(controller);
      
      JPanel box = new JPanel();
      JLabel label = new JLabel("The candidates: ");

      box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
      box.add(label); //0th component

      for (int i = 0; i < numButtons; i++) {
          box.add(radioButtons[i]); //1st...numButtons(th) components
      }
      
      JPanel pane = new JPanel(new GridLayout(0, 1));
      pane.add(box); //0th component
      pane.add(voteButton); //1st component
      pane.add(logoutButton); //2nd component
      return pane;
  }
}
