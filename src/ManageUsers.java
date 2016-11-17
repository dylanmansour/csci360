/**
 * Justin Priester, Dylan Mansour
 * A secondary controller that contains methods for managing profile information.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;


public final class ManageUsers {
	
	private static Database database; // stores persistent data (vote and account info)
	
	/**
	 * Checks if the specified name and password match the name and password
	 * of a profile stored in the database.
	 */
	public static boolean verifyLogin(String username, String password)
	 {
		/*Scanner scanner = new Scanner(new FileInputStream("users.txt"));
		String str = null;

		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split();
			if (fields[0].equals(name) && fields[1].equals(password))
			{
				return true;
			}
		}
		scanner.close();*/
		return false;
	 }
	
	/**
	 * Generates and returns a unique ID to be used to mark a profile.
	 */
	public static String generateID()
	{
		/*String uniqID = null;
		Scanner scanner = new Scanner(new FileInputStream("user.txt"));
		String str = null;

		while(scanner.hasNextLine())
		{
		   str = scanner.nextLine();
		}

		String[] fields = str.split();
		uniqID = (Integer.parseInt(fields[2])+ 1).toString();
		scanner.close();*/
		return "placeholder";
	}
	
	public static boolean verifyRegistration(VoterProfile profile)
	{
		Scanner scanner = new Scanner(new FileInputStream("users.txt"));
		String str = null;

		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split();
			if (fields[0].equals(name) && fields[1].equals(password) && fields[3].equals(age) && fields[4].equals(licenseID))
			{
				return true;
			}
		}
		scanner.close();
		return false;
	}
	
	public static void registerAccount(VoterProfile profile)
	{
		profile.setVoterID(generateID());
		
		//This needs to be fixed
		/*(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("users.txt"), "utf-8"))) 
		{
			writer.write("name" + "password" + uniqID + "age" + "licenseID");
		}*/
		
		profile.setRegistered(true); //The registration was successful.
	}
}
