import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Justin Priester, Dylan Mansour
 * The primary controller of the voting software. It is responsible for
 * responding to input from the view classes.
 */
public class Controller {
	public static void main(String[] args) throws IOException
	{
		ManageUsers manageUsers = new ManageUsers("users.txt");
		
		Scanner scanner = new Scanner(System.in); //hold, will read user input
		
		String nextMenu = "mainMenu"; //the next menu to go to after the current one terminates
		
		while (!nextMenu.equals("exit"))
		{
			switch (nextMenu)
			{
			case "mainMenu":
				nextMenu = simulateMainMenu(scanner);
				break;
				
			case "registerMenu":
				nextMenu = simulateRegisterMenu(scanner, manageUsers);
				break;
				
			case "loginMenu":
				nextMenu = simulateLoginMenu(scanner, manageUsers);
				break;
			}
		}
		
		scanner.close();
	}
	
	private static String simulateMainMenu(Scanner scanner)
	{
		String nextMenu = ""; //to contain information on which menu to go to after this one ends
		
		System.out.println("Welcome to the bestest voting system evar!");
		System.out.println("Type 'register' if you want to register,\n" +
								"or type 'login' if you already have an account.");
		System.out.println("Type 'exit' to close the software.");
		
		String input = scanner.nextLine();
		
		if (input.equals("register"))
		{
			nextMenu = "registerMenu";
		}
		else if (input.equals("login"))
		{
			nextMenu = "loginMenu";
		}
		else if (input.equals("exit"))
		{
			nextMenu = "exit";
		}
		else
		{
			System.out.println("You submitted invalid information!");
			nextMenu = "mainMenu";
		}
		
		return nextMenu;
	}
	
	private static String simulateRegisterMenu(Scanner scanner, ManageUsers manageUsers) throws IOException
	{
		String nextMenu = ""; //to contain information on which menu to go to after this one ends
		VoterProfile newProfile; //to hold information of the new profile
		
		System.out.println("Let's get some information from you.");
		
		//Registration
		System.out.println("Remember that none of your information should have spaces.");
		System.out.println("Also, you have to be 18 or older in order to vote.");
		System.out.println("Type 'exit' at the start to back out of registration.");
		
		System.out.print("Username (for logging in): ");
		String username = scanner.nextLine();
		if (username.equals("exit"))
		{
			return "mainMenu";
		}
		
		System.out.print("Password (for logging in): ");
		String password = scanner.nextLine();
		
		System.out.print("Age: ");
		int age = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Driver's License ID: ");
		String licenseID = scanner.nextLine();
		
		newProfile = new VoterProfile(username, password, age, licenseID);
		
		if (manageUsers.verifyRegistration(newProfile))
		{
			manageUsers.registerAccount(newProfile);
			
			if (newProfile.isRegistered())
			{
				System.out.println("Your account has been registered!");
				System.out.println("We totally won't do anything evil with this information! =D");
				
				nextMenu = "loginMenu";
			}
			else
			{
				System.out.println("For some reason we couldn't register your account.");
			}
		}
		else
		{
			System.out.println("Uh oh! You submitted invalid information!");
			nextMenu = "registerMenu";
		}
		
		return nextMenu;
	}
	
	private static String simulateLoginMenu(Scanner scanner, ManageUsers manageUsers) throws IOException
	{
		String nextMenu = ""; //to contain information on which menu to go to after this one ends
		
		System.out.print("Username: ");
		String username = scanner.nextLine();
		
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		VoterProfile profile = manageUsers.login(username, password);
		
		if (profile != null)
		{
			while (nextMenu != "logout")
			{
				nextMenu = simulateVoteMenu(scanner, profile);
			}
			
			nextMenu = "mainMenu";
		}
		else
		{
			System.out.println("The specified account does not exist.");
			nextMenu = "loginMenu";
		}
		
		
		return nextMenu;
	}
	
	private static String simulateVoteMenu(Scanner scanner, VoterProfile profile) throws IOException
	{
		String nextMenu = ""; //to contain information on which menu to go to after this one ends
		String input;
		
		//sample candidate
		Candidate bob = new Candidate();
		bob.setName("Bob");
		bob.setParty("Builder");
		
		//sample candidate
		Candidate bill = new Candidate();
		bill.setName("Bill");
		bill.setParty("Science");
		
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		candidates.add(bob);
		candidates.add(bill);
		
		System.out.println("Below you have the candidates that you can vote for.");
		System.out.println("Type the name of the  candidate that you want to vote for.");
		System.out.println("Type 'logout' to log out.");
		
		for (Candidate candidate : candidates)
		{
			System.out.println("Name: " + candidate.getName());
			System.out.println("Party: " + candidate.getParty());
			System.out.println("---");
		}
		
		input = scanner.nextLine();
		
		if (input.equals("logout"))
		{
			nextMenu = "logout";
		}
		else
		{
			int voteIndex = -1;
			
			for (int i = 0; i < candidates.size(); i++)
			{
				if (input.equals(candidates.get(i)))
				{
					voteIndex = i;
					break;
				}
			}
			
			if (voteIndex == -1)
			{
				System.out.println("Invalid candidate. Let's try again!");
				nextMenu = "voteMenu";
			}
			else
			{
				Vote vote = new Vote();
				vote.setName(candidates.get(voteIndex).getName());
				vote.setID(profile.getVoterID());
				
				Ballot ballot = new Ballot("votes.txt", vote);
				
				if (ballot.voteCasted())
				{
					System.out.println("You have already casted a vote. Have a good day!");
				}
				else
				{
					ballot.castVote();
					System.out.println("Your vote has been cast. Have a good day!");
				}
				nextMenu = "logout";
			}
		}
		
		return nextMenu;
	}
}
