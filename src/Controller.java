import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Justin Priester, Dylan Mansour
 * The primary controller of the voting software. It is responsible for
 * responding to input from the view classes.
 */
public class Controller implements ActionListener
{
	MasterWindow window;
	ManageUsers manageUsers; //Extended methods for managing user profiles
	VoterProfile profile;
	
	public Controller()
	{
		window = new MasterWindow(this);
		manageUsers = new ManageUsers("users.txt");
		profile = blankProfile();
	}
	
	public static void main(String[] args) throws IOException
	{
		Controller controller = new Controller();
	}
	
	private VoterProfile blankProfile()
	{
		return new VoterProfile("", "", 0, "");
	}

	public static void infoBox(Component parent, String infoMessage)
   {
       JOptionPane.showMessageDialog(parent, infoMessage, "", JOptionPane.INFORMATION_MESSAGE);
   }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		String menuName = window.getMenuName();
		
		if (menuName.equals("main"))
		{
			if (ac.equals("switch to login"))
			{
				window.changeScreen("login");
			}
			else if (ac.equals("switch to register"))
			{
				window.changeScreen("register");
			}
		}
		else if (menuName.equals("register"))
		{
			if (ac.equals("switch to main"))
			{
				window.changeScreen("main");
				profile = blankProfile();
			}
			else if (ac.equals("register account"))
			{
				JTextField usernameField = (JTextField) (window.getMenuComponent(0));
				JTextField passwordField = (JTextField) (window.getMenuComponent(2));
				JTextField ageField = (JTextField) (window.getMenuComponent(4));
				JTextField licenseIDField = (JTextField) (window.getMenuComponent(6));
				
				profile = new VoterProfile(usernameField.getText(),
													passwordField.getText(),
													Integer.parseInt(ageField.getText()),
													licenseIDField.getText());
				
				try
				{
					if (manageUsers.verifyRegistration(profile))
					{
						manageUsers.registerAccount(profile);
						
						if (profile.isRegistered())
						{
							infoBox(window.getFrame(), "Your account has been registered!");
							window.changeScreen("login");
							profile = blankProfile();
						}
						else
						{
							infoBox(window.getFrame(), "For some reason we couldn't register your account.");
						}
					}
					else
					{
						infoBox(window.getFrame(), "Uh oh! You submitted invalid information!");
					}
				}
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		else if (menuName.equals("login"))
		{
			if (ac.equals("switch to main"))
			{
				window.changeScreen("main");
			}
			else if (ac.equals("login account"))
			{
				JTextField usernameField = (JTextField) (window.getMenuComponent(0));
				JTextField passwordField = (JTextField) (window.getMenuComponent(2));
				
				try
				{
					profile = manageUsers.login(usernameField.getText(), passwordField.getText());
				}
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}
				
				if (profile != null)
				{
					infoBox(window.getFrame(), "Successfully logged in!");
					window.changeScreen("vote");
				}
				else
				{
					infoBox(window.getFrame(), "The specified account does not exist.");
				}
			}
		}
		else if (menuName.equals("vote"))
		{
			if (ac.equals("logout"))
			{
				window.changeScreen("main");
				profile = blankProfile();
			}
			else if (ac.equals("cast vote"))
			{
				JPanel box = (JPanel) window.getMenuComponent(0);
				final int numButtons = box.getComponentCount() - 1; //we subtract 1 because the first component of the box is a label
				JRadioButton radioButton;
				String candidateName = "";
				
				for (int i = 0; i < numButtons; i++)
				{
					radioButton = (JRadioButton)box.getComponent(i + 1); //we add 1 because the first component of the box is a label
					
					if (radioButton.isSelected())
					{
						candidateName = radioButton.getActionCommand();
						break;
					}
				}
				
				if (candidateName != "")
				{
					Vote vote = new Vote(); //The voter's vote
					vote.setName(candidateName);
					vote.setID(profile.getVoterID());
					
					Ballot ballot = new Ballot("votes.txt", vote); //A ballot that holds the vote
					
					try
					{
						if (ballot.voteCasted())
						{
							infoBox(window.getFrame(), "You have already casted a vote. Have a good day!");
						}
						else
						{
							ballot.castVote();
							infoBox(window.getFrame(), "Your vote has been cast. Have a good day!");
						}
					}
					catch (FileNotFoundException e1)
					{
						e1.printStackTrace();
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
				else
				{
					infoBox(window.getFrame(), "Attempted to vote for an invalid candidate.");
				}
				
				//log out
				window.changeScreen("main");
				profile = blankProfile();
			}
		}
	}
}
