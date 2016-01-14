package Main_Package;

import java.io.Serializable;

/**
 * @author David O'Dwyer
 *
 */

public abstract class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static Integer x = new Integer(1);
	private String fName;
	private String lName;
	private String countryOfOrigin;
	private String idNumber;
	
	
	/**
	 * @return
	 */
	public static Integer getX() {
		return x;
	}
	/**
	 * @param x
	 */
	public static void setX(Integer x) {
		Employee.x = x;
	}
	/**
	 * @return
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * @param fName
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * @return
	 */
	public String getlName() {
		return lName;
	}
	/**
	 * @param lName
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	/**
	 * @return
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	/**
	 * @param countryOfOrigin
	 */
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	/**
	 * @return
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * @param fName
	 * @param lName
	 * @param countryOfOrigin
	 */
	public Employee(String fName, String lName, String countryOfOrigin) {
		this.fName = fName;
		this.lName = lName;
		this.countryOfOrigin = countryOfOrigin;
		this.idNumber = ("00" + x.toString());
		setX(new Integer (x.intValue() + 1));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Name: "+ fName + " " + lName + ", Country of Origin: " + countryOfOrigin + ", ID Number: "
				+ idNumber;
	}

	}
	
