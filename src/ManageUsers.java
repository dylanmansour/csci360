/**
 * Justin Priester, Dylan Mansour
 * A secondary controller that contains methods for managing profile information.
 */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class ManageUsers {
	
	private String database; // stores persistent data (vote and account info)
	private CryptoUtil security;
    private String key;
    
	public ManageUsers(String filename)
	{
		this.database = filename;
		this.key = "1234567890123456";
		this.security = new CryptoUtil();
	}
	
	/**
	* Checks if the specified name and password match the name and password
	* of a profile stored in the database.
	* @throws FileNotFoundException 
	*/
	public VoterProfile login(String username, String password) throws FileNotFoundException
	{
		VoterProfile profile = null; //no starting value for profile
		Scanner scanner = new Scanner(new FileInputStream(this.database)); //scanner reads file
		String str = null; 

		
		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split("\t");
			if (this.security.decrypt(fields[0],this.key).equals(username) && this.security.decrypt(fields[1],this.key).equals(password)) //the login information is correct
			{
				profile = new VoterProfile(this.security.decrypt(fields[0],this.key), this.security.decrypt(fields[1],this.key), Integer.parseInt(fields[3]), this.security.decrypt(fields[4],this.key)); //
				profile.setVoterID(fields[2]);
				profile.setRegistered(true);
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
			System.out.println(str);
			String[] fields = str.split("\t");
			if (this.security.decrypt(fields[4],this.key).equals(profile.getLicenseID()))
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
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	public boolean registerAccount(VoterProfile profile) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new FileInputStream("users.txt"));
        String str = "";

        while(scanner.hasNextLine())
        {
            str = scanner.nextLine();
            String[] fields = str.split("\t");
            if (this.security.decrypt(fields[0],this.key).equals(profile.getUsername()) && this.security.decrypt(fields[1],this.key).equals(profile.getPassword()) && fields[3].equals(Integer.toString(profile.getAge())) && this.security.decrypt(fields[4],this.key).equals(profile.getLicenseID()))
            {
                //User exists, exit the method
                scanner.close();
                return false;
            }
            if(Integer.parseInt(fields[3]) < 18)
            {
                //User is too young, exit the method
                scanner.close();
                return false;
            }
        }
        scanner.close();
        
        profile.setVoterID(generateID());
        try
        {
            FileWriter fw = new FileWriter("users.txt",true);
            fw.write(this.security.encrypt(profile.getUsername(),this.key) + "\t" + this.security.encrypt(profile.getPassword(),this.key) + "\t" + profile.getVoterID() + "\t" + profile.getAge() + "\t" + this.security.encrypt(profile.getLicenseID(),this.key) + System.lineSeparator());
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        
        profile.setRegistered(true); //The registration was successful.
        return true;
    }

}
