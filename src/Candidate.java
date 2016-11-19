/**
 * Justin Priester, Dylan Mansour
 * Information holder for one of the candidates that can be voted for.
 */
public class Candidate {
	
	private String name; // name of the candidate
	private String party; // party the candidate is associated with
	
	public String getName()
	{
		return name; //gets the name of the candidate 
	}
	
	public String getParty()
	{
		return party; //get the party of the candidate
	}
	
	public void setName(String name)
	{
		this.name = name; //sets the name of the candidate
	}
	
	public void setParty(String party)
	{
		this.party = party; //sets the party of the candidate 
	}
}
