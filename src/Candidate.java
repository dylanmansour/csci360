/**
 * Justin Priester, Dylan Mansour
 * Information holder for one of the candidates that can be voted for.
 */
public class Candidate {
	
	private String name; // name of the candidate
	private String party; // party the candidate is associated with
	
	public String getName()
	{
		return name;
	}
	
	public String getParty()
	{
		return party;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setParty(String party)
	{
		this.party = party;
	}
}
