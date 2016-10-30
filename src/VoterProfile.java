/**
 * Justin Priester, Dylan Mansour
 * Information holder for a profile.
 */
public class VoterProfile {
	boolean registered; // whether or not the profile is officially registered
	String name; // name of the profile
	String voterID; // generated ID of the voter who owns the profile
	int age; // age of the voter who owns the profile
	String licenseID; // driver's license ID of the voter who owns the profile (encrypted)
}
