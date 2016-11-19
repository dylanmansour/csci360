import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * Justin Priester, Dylan Mansour
 * An assumed virtual slip containing voting information
 */
public class Ballot {
	
	private Vote vote; // vote that the ballot holds
	//Printer printer; // printer that the software is connected to
	private String database; // stores persistent data (vote and account info)
	
	
	public Ballot(String filename, Vote v)
	{
	    database = filename;
	    vote = v;
	}
	/**
	 * Submits the held voting information to the database.
	 */
	public void castVote() throws IOException
	{
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.database),"utf-8"));
		writer.append(this.vote.getName() + ";" + this.vote.getID() + "\n");
		writer.close();
	}
	
	public boolean voteCasted() throws FileNotFoundException
	{
		boolean voteCast = false;
		
		Scanner scanner = new Scanner(new FileInputStream(this.database));
		String str = null;

		while(scanner.hasNextLine())
		{
			str = scanner.nextLine();
			String[] fields = str.split(";");
			if (fields[1].equals(this.vote.getID()))
			{
				voteCast = true;
				break;
			}
		}
		scanner.close();
		return voteCast;
	}
	
	/**
	 * Assigns a vote to the ballot.
	 */
	public void setVote(Vote v)
	{
		vote = v;
	}
	
	/**
	 * Calls on the printer to print the held voting information
	 */
	public void printResult()
	{
		//TODO Will be updated to properly print from a physical printer later.
		System.out.println(this.vote.getName() + "  " + this.vote.getID());
	}
}
