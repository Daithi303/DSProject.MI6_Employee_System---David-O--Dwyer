package Main_Package;



/**
 * @author David O' Dwyer
 *
 */
public class OoAgent extends FieldAgent{

	private static final long serialVersionUID = 1L;
	private String companyCar;

	/**
	 * @return
	 */
	public String getCompanyCar() {
		return companyCar;
	}

	/**
	 * @param companyCar
	 */
	public void setCompanyCar(String companyCar) {
		this.companyCar = companyCar;
	}

	/**
	 * @param fName
	 * @param lName
	 * @param countryOfOrigin
	 */
	public OoAgent(String fName, String lName, String countryOfOrigin) {
		super(fName, lName, countryOfOrigin);
		companyCar = "";
		super.setLicenceToKill(true);
	}

	/* (non-Javadoc)
	 * @see Main_Package.FieldAgent#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ", Company car: " + companyCar;
	}

}
