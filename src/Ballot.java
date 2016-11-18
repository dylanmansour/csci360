/*/**
 * Justin Priester, Dylan Mansour
 * An assumed virtual slip containing voting information
 */
public class Ballot {
	
	Vote vote; // vote that the ballot holds
	//Printer printer; // printer that the software is connected to
	String database; // stores persistent data (vote and account info)
	
	
	public Ballot(String filename, Vote v)
	{
	    database = filename;
	    vote = v;
	}
	/*
	 * Submits the held voting information to the database.
	 */
	void castVote()
	{
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.database),"utf-8"))
		try{
		    writer.append(this.vote.getName() + ";" + this.vote.getID() + "\n");
		   }
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
		System.out.println(this.vote.getName() + "  " + this.vote.getID());
	}
}
