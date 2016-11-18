/**
 * Justin Priester, Dylan Mansour
 * A secondary controller that contains methods for managing profile information.
 */
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;


public final class ManageUsers {
	
	private static Database database; // stores persistent data (vote and account info)
	
	/**
	* Checks if the specified name and password match the name and password
	* of a profile stored in the database.
	 * @throws FileNotFoundException 
	*/
	public static boolean verifyLogin(String username, String password) throws FileNotFoundException
	{
		boolean result = false;
		Scanner scanner = new Scanner(new FileInputStream("users.txt"));
		String str = null;

		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split(";");
			if (fields[0].equals(username) && fields[1].equals(password))
			{
				result = true;
				break;
			}
		}
		scanner.close();
		return result;
	}
	
	/**
	* Generates and returns a unique ID to be used to mark a profile.
	 * @throws FileNotFoundException 
	*/
	public static String generateID() throws FileNotFoundException
	{
		String uniqID = null;
		Scanner scanner = new Scanner(new FileInputStream("user.txt"));
		
		int uniqIDnum = 1;

		while (scanner.hasNextLine())
		{
		  scanner.nextLine();
		  uniqIDnum++;
		}
		
		uniqID = Integer.toString(uniqIDnum);
		scanner.close();
		return uniqID;
	}
	
	public static boolean verifyRegistration(VoterProfile profile)
	{
		/*Scanner scanner = new Scanner(new FileInputStream("users.txt"));
		String str = null;
		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split("[;]");
			if (fields[0].equals(profile.getUsername()) && fields[1].equals(profile.getPassword()) && fields[3].equals(profile.getAge()) && fields[4].equals(profile.getLicenseID()))
			{
				profile.setRegistered(true);
			   return true;
			}
		}
		scanner.close();
		profile.setRegistered(false);
		return false;*/
		return (profile.isRegistered());
	}
	
	public static void registerAccount(VoterProfile profile) throws IOException
	{
		profile.setVoterID(generateID());
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("users.txt"),"utf-8"));
		writer.append(profile.getUsername() + ";" + profile.getPassword() + ";" +
							profile.getVoterID() + ";" + profile.getAge() +
							profile.getLicenseID() + "\n");
		
		profile.setRegistered(true); //The registration was successful.
		
		writer.close();
	}
}