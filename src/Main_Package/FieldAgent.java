package Main_Package;



/**
 * @author David O' Dwyer
 *
 */
public class FieldAgent extends Employee{

	private static final long serialVersionUID = 1L;
	private boolean status;
	private int unauthorisedKills;
	private boolean licenceToKill;
	
	/**
	 * @param fName
	 * @param lName
	 * @param countryOfOrigin
	 */
	public FieldAgent(String fName, String lName, String countryOfOrigin) {
		super(fName, lName, countryOfOrigin);
		this.status = true;
		this.unauthorisedKills = 0;
		this.licenceToKill = false;
	}

	/**
	 * @return
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return
	 */
	public int getUnauthorisedKills() {
		return unauthorisedKills;
	}
	
	/**
	 * @param unauthorisedKills
	 */
	public void setUnauthorisedKills(int unauthorisedKills) {
		this.unauthorisedKills = unauthorisedKills;
	}
	
	/**
	 * @return
	 */
	public boolean isLicenceToKill() {
		return licenceToKill;
	}
	
	/**
	 * @param licenceToKill
	 */
	public void setLicenceToKill(boolean licenceToKill) {
		this.licenceToKill = licenceToKill;
	}
	/* (non-Javadoc)
	 * @see Main_Package.Employee#toString()
	 */
	@Override
	public String toString() {
	String tempStatus;
	if(status)
	{tempStatus = "On active duty";}
	else
	{tempStatus = "Suspended";}
	
	String lTC;
	if(licenceToKill)
	{lTC = "Authorised";}
	else
	{lTC = "Withheld";}
		return super.toString() + ", Status: " + tempStatus + ", Unathorised Kills: " + unauthorisedKills + ", Licence to Kill: " + lTC;
	}
		
}
