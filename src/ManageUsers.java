/**
 * Justin Priester, Dylan Mansour
 * A secondary controller that contains methods for managing profile information.
 */
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class ManageUsers {
	
	private String database; // stores persistent data (vote and account info)
	
	public ManageUsers(String filename)
	{
		this.database = filename;
	}
	
	/**
	* Checks if the specified name and password match the name and password
	* of a profile stored in the database.
	* @throws FileNotFoundException 
	*/
	public VoterProfile login(String username, String password) throws FileNotFoundException
	{
		VoterProfile profile = null;
		Scanner scanner = new Scanner(new FileInputStream(this.database));
		String str = null;

		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split(";");
			if (fields[0].equals(username) && fields[1].equals(password))
			{
				profile = new VoterProfile(fields[0], fields[1], Integer.parseInt(fields[3]), fields[4]);
				profile.setVoterID(fields[2]);
				break;
			}
		}
		scanner.close();
		return profile;
	}
	
	/**
	* Generates and returns a unique ID to be used to mark a profile.
	* @throws FileNotFoundException 
	*/
	public String generateID() throws FileNotFoundException
	{
		String uniqID = null;
		Scanner scanner = new Scanner(new FileInputStream(this.database));
		
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
	
	/**
	 * Makes sure that the profile can be registered.
	 * @param profile
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean verifyRegistration(VoterProfile profile) throws FileNotFoundException
	{
		boolean canRegister = true;
		Scanner scanner = new Scanner(new FileInputStream(this.database));
		String str = null;
		
		//Check if the license ID has already been used (prevents duplicate accounts)
		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split("[;]");
			if (fields[4].equals(profile.getLicenseID()))
			{
				canRegister = false;
			}
		}
		
		//Check if age requirement is met (prevents underaged voters)
		if (profile.getAge() < 18)
		{
			canRegister = false;
		}
		
		//Check if there are no spaces in the information
		if (
				profile.getUsername().contains(" ") ||
				profile.getPassword().contains(" ") ||
				profile.getLicenseID().contains(" "))
		{
			canRegister = false;
		}
		
		
		scanner.close();
		
		return (canRegister);
	}
	
	
	/**
	 * Officially registers the profile by storing it.
	 * @param profile
	 * @throws IOException
	 */
	public void registerAccount(VoterProfile profile) throws IOException
	{
		profile.setVoterID(generateID());
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.database),"utf-8"));
		writer.append(profile.getUsername() + ";" + profile.getPassword() + ";" +
							profile.getVoterID() + ";" + profile.getAge() + ";" +
							profile.getLicenseID() + "\n");
		
		profile.setRegistered(true); //The registration was successful.
		
		writer.close();
	}
}