import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Justin Priester, Dylan Mansour
 * The primary controller of the voting software. It is responsible for
 * responding to input from the view classes.
 */
public class Controller {
	public static void main(String[] args)
	{
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
				nextMenu = simulateRegisterMenu(scanner);
				break;
				
			case "loginMenu":
				nextMenu = simulateLoginMenu(scanner);
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
		
		String input = scanner.nextLine();
		
		if (input.equals("register"))
		{
			nextMenu = "registerScreen";
		}
		else if (input.equals("login"))
		{
			nextMenu = "loginScreen";
		}
		else
		{
			System.out.println("You submitted invalid information!");
			nextMenu = "mainMenu";
		}
		
		return nextMenu;
	}
	
	private static String simulateRegisterMenu(Scanner scanner) throws IOException
	{
		String nextMenu = ""; //to contain information on which menu to go to after this one ends
		VoterProfile newProfile; //to hold information of the new profile
		
		System.out.println("Let's get some information from you.");
		
		//Registration
		System.out.println("Remember that none of your information should have spaces.");
		System.out.println("Also, you have to be 18 or older in order to vote.");
		
		System.out.print("Username (for logging in): ");
		String username = scanner.nextLine();
		
		System.out.print("Password (for logging in): ");
		String password = scanner.nextLine();
		
		System.out.print("Age: ");
		int age = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Driver's License ID: ");
		String licenseID = scanner.nextLine();
		
		newProfile = new VoterProfile(username, password, age, licenseID);
		
		if (ManageUsers.verifyRegistration(newProfile))
		{
			ManageUsers.registerAccount(newProfile);
			
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
	
	private static String simulateLoginMenu(Scanner scanner)
	{
		String nextMenu = ""; //to contain information on which menu to go to after this one ends
		
		System.out.print("Username: ");
		String username = scanner.nextLine();
		
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		VoterProfile profile = ManageUsers.login(username, password);
		
		if (profile != null)
		{
			while (nextMenu != "logout")
			{
				nextMenu = simulateVoteMenu(scanner, profile);
			}
		}
		else
		{
			System.out.println("The specified account does not exist.");
		}
		
		return nextMenu;
	}
	
	private static String simulateVoteMenu(Scanner scanner, VoterProfile profile)
	{
		String nextMenu = ""; //to contain information on which menu to go to after this one ends
		
		//sample candidate
		Candidate bob = new Candidate();
		bob.setName("Bob");
		bob.setParty("Builder");
		
		//sample candidate
		Candidate bill = new Candidate();
		bill.setName("Bill");
		bill.setParty("Science");
		
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		
		System.out.println("You have the candidates that you can vote for.");
		
		return nextMenu;
	}
}
