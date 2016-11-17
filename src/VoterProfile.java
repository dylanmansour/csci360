/**
 * Justin Priester, Dylan Mansour
 * Information holder for a profile.
 */
public class VoterProfile {
	private boolean registered; // whether or not the profile is officially registered
	private String username; // name of the profile
	private String password; // password of the profile
	private String voterID; // generated ID of the voter who owns the profile
	private int age; // age of the voter who owns the profile
	private String licenseID; // driver's license ID of the voter who owns the profile (encrypted)
	
	public VoterProfile(String username, String password, int age, String licenseID) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.licenseID = licenseID;
		
		this.registered = false;
		this.voterID = "undefined"; // to be set by the software
	}

	/**
	 * @return the registered
	 */
	public boolean isRegistered() {
		return registered;
	}
	
	/**
	 * @param registered the registered to set
	 */
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	/**
	 * @return the voterID
	 */
	public String getVoterID() {
		return voterID;
	}

	/**
	 * @param voterID the voterID to set
	 */
	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the licenseID
	 */
	public String getLicenseID() {
		return licenseID;
	}
}
