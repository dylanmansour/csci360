import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Justin Priester, Dylan Mansour
 * A secondary controller that contains methods for managing voter information.
 */
public final class ManageVotes {
	
	private static String database; // stores persistent data (vote and account info)
	
	public ManageVotes(String filename)
	{
	   database = filename;
	}
	/*
	* Returns the number of votes from the database that match the name of
	* the specified candidate.
	*/
	public static int countVotes(Candidate candidate) throws FileNotFoundException
	{
		String name = candidate.getName();
		int voteCount = 0;
		
		Scanner scanner = new Scanner(new FileInputStream(database));
		String str = null;

		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split(";");
			if (fields[0].equals(name))
			{
				voteCount = voteCount + 1;
			}
		}
		scanner.close();
		
		return voteCount;
	}
}
