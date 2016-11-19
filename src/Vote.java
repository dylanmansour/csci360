/**
 * Justin Priester, Dylan Mansour
 * Information holder for a vote.
 */
public class Vote {
	private String candidate; // the name of the candidate that the vote is for
	private String voterID; // the generated ID of the voter who submitted this vote
	
	/**
	 * @return the candidate
	 */
	public String getName()
	{
	   return candidate; //returns the candidate on the vote
	}
	
	/**
	 * @return the voter ID
	 */
	public String getID()
	{
	   return voterID; //returns the voter ID on the vote
	}
	
	/**
	 * @param name
	 */
	public void setName(String name)
	{
	   this.candidate = name; //sets candidate's name for the vote
	}
	
	/**
	 * @param ID
	 */
	public void setID(String ID)
	{
	   this.voterID = ID; //sets voter's ID for the vote
	}
}
