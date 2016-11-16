/**
 * Justin Priester, Dylan Mansour
 * A secondary controller that contains methods for managing profile information.
 */
public final class ManageUsers {
	
	private static Database database; // stores persistent data (vote and account info)
	
	/*
	 * Checks if the specified name and password match the name and password
	 * of a profile stored in the database.
	 */
	public static boolean verifyLogin(String name, String password)
	{
		// TODO
		return true;
	}
	
	/*
	 * Generates and returns a unique ID to be used to mark a profile.
	 */
	public static String generateID()
	{
		// TODO
		return "";
	}
	
	public static boolean verifyRegistration(String name, String password,
			int age, String licenseID)
	{
		// TODO
		return true;
	}
	
	public static boolean registerAccount(String name, String password,
			int age, String licenseID)
	{
		// TODO
		return true;
	}
}
