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
		
		String input; //used for storing input
		
		Candidate bob = new Candidate();
		bob.setName("Bob");
		bob.setParty("Builder");
		
		Candidate bill = new Candidate();
		bill.setName("Bill");
		bill.setParty("Science");
		
		System.out.println("Welcome to the bestest voting system evar!");
		System.out.println("Type 'register' if you want to register,\n" +
								"or type 'login' if you already have an account.");
		
		input = scanner.nextLine();
		
		if (input.equals("register"))
		{
			System.out.println("Hurrayz! Let's get some information from you!");
			
			//Registration
			System.out.println("Remember that none of your information should have spaces.");
			System.out.println("Also, you have to be 18 or older in order to vote.");
			
			System.out.print("Username (for logging in): ");
			String name = scanner.nextLine();
			
			System.out.print("Password (for logging in): ");
			String password = scanner.nextLine();
			
			System.out.println("Death Certificate: ");
			System.out.println("...I mean...");
			System.out.print("Age: ");
			int age = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("Driver's License ID: ");
			String licenseID = scanner.nextLine();
			
			if (ManageUsers.verifyRegistration(name, password, age, licenseID))
			{
				ManageUsers.registerAccount(name, password, age, licenseID);
				
				System.out.println("Your account has been registered!");
				System.out.println("We totally won't do anything evil with this information! =D");
			}
			else
			{
				System.out.println("Uh oh! You submitted invalid information!");
			}
		}
		else if (input.equals("login"))
		{
			//Logging In
		}
		else
		{
			System.out.println("Uh oh! You submitted invalid information!");
		}
		
		scanner.close();
	}
}
