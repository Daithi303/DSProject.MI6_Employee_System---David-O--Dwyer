package Main_Package;


/**
 * @author David O' Dwyer
 *
 */
public class SpecialAgent extends FieldAgent{

	private static final long serialVersionUID = 1L;
	String tempDoubleAgent;
	private Boolean handler;
	private String handlerId = "";
	private boolean doubleAgent;
	
	/**
	 * @return
	 */
	public Boolean isHandler() {
		return handler;
	}
	
	/**
	 * @param handler
	 */
	public void setHandler(Boolean handler) {
		this.handler = handler;
	}
	
	/**
	 * @return
	 */
	public String getHandlerId() {
		return handlerId;
	}
	
	/**
	 * @param handlerId
	 */
	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}
	
	/**
	 * @return
	 */
	public boolean isDoubleAgent() {
		return doubleAgent;
	}
	
	/**
	 * @param doubleAgent
	 */
	public void setDoubleAgent(boolean doubleAgent) {
		this.doubleAgent = doubleAgent;
	}
	
	/**
	 * @param fName
	 * @param lName
	 * @param countryOfOrigin
	 */
	public SpecialAgent(String fName, String lName, String countryOfOrigin) {
		super(fName, lName, countryOfOrigin);
		handler = false;
		doubleAgent = false;
	}

	/* (non-Javadoc)
	 * @see Main_Package.FieldAgent#toString()
	 */
	public String toString() {
		
		String tempHandlerId;
		if(doubleAgent)
		{
			tempDoubleAgent = "Yes";
		}
		else
		{
			tempDoubleAgent = "No";
		}
		
		
		if(handler)
		{
			tempHandlerId = handlerId;
		}
		
		else
		{
			tempHandlerId = "Unassigned";
		}
		return super.toString() + ", Handler ID Number: " + tempHandlerId +", Double Agent: " + tempDoubleAgent;

	}

}
	
	
	
	


