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
	   return candidate;
	}
	
	/**
	 * @return the voter ID
	 */
	public String getID()
	{
	   return voterID;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name)
	{
	   this.candidate = name;
	}
	
	/**
	 * @param ID
	 */
	public void setID(String ID)
	{
	   this.voterID = ID;
	}
}
