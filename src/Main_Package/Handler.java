package Main_Package;

/**
 * @author David O' Dwyer
 *
 */
public class Handler extends Employee{
	
	private static final long serialVersionUID = 1L;
	private boolean handlerStatus;
	private String assignedAgentId;

	/**
	 * @return
	 */
	public boolean getHandlerStatus() {
		return handlerStatus;
	}
	
	/**
	 * @param handlerStatus
	 */
	public void setHandlerStatus(boolean handlerStatus) {
		this.handlerStatus = handlerStatus;
	}
	
	/**
	 * @return
	 */
	public String getAssignedAgentId() {
		return assignedAgentId;
	}
	
	/**
	 * @param assignedAgent
	 */
	public void setAssignedAgentId(String assignedAgent) {
		this.assignedAgentId = assignedAgent;
	}

	/**
	 * @param fName
	 * @param lName
	 * @param countryOfOrigin
	 */
	public Handler(String fName, String lName, String countryOfOrigin) {
		super(fName, lName, countryOfOrigin);
		this.handlerStatus = true;
	}
	
	/* (non-Javadoc)
	 * @see Main_Package.Employee#toString()
	 */
	@Override
	public String toString() {
		String hs = "Assigned";
		if(handlerStatus)
		{hs = "Available";
		 assignedAgentId = "No agent assigned";
		}
		
		return super.toString() + ", Handler Status: " + hs +", Assigned Agent's ID Number: " + assignedAgentId;
	}

}
