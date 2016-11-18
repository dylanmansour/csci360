/**
 * Justin Priester, Dylan Mansour
 * Information holder for a vote.
 */
public class Vote {
	String candidate; // the name of the candidate that the vote is for
	String voterID; // the generated ID of the voter who submitted this vote
	
	public String getName()
	{
	   return candidate;
	}
	  
	public String getID()
	{
	   return voterID;
	}
	
	public void setName(String name)
	{
	   this.candidate = name;
	}
	  
	public void setID(String ID)
	{
	   this.voterID = ID;
	}
}
