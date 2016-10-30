/*
 * An assumed virtual slip containing voting information
 */
public class Ballot {
	
	Vote vote; // vote that the ballot holds
	Printer printer; // printer that the software is connected to
	Database database; // stores persistent data (vote and account info)
	
	/*
	 * Submits the held voting information to the database.
	 */
	void castVote()
	{
		// TODO
	}
	
	/*
	 * Assigns a vote to the ballot.
	 */
	void setVote(Vote v)
	{
		vote = v;
	}
	
	/*
	 * Calls on the printer to print the held voting information
	 */
	void printResult()
	{
		// TODO
	}
}
