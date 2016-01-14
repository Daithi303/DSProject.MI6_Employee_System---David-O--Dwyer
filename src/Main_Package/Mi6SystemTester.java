package Main_Package;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Mi6SystemTester
{
	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * This method uses a 'do while' loop and a 'switch' statement which first calls the menuMain() method
	 * which asks the user to choose an option. Depending on that option, the 'do while' loop will either
	 * end and the program will close, or a menu-type method will be called which can, in turn, call other methods.
	 */
	public static void main(String args[]) throws ClassNotFoundException, IOException
	{
		 int option;
		 EmployeeManager employeeList = new EmployeeManager();
		   
		   do {
		   option = employeeList.menuMain();
		 
		  	switch(option)
		   		{
		   	case 1  : employeeList.menuAddEmployee();
		   			  break;
		   	case 2  : employeeList.menuRemoveEmployee();
		   			  break;
		   	case 3  : employeeList.menuListEmployee();
		   		      break;		 
		   	case 4  : employeeList.menuPromoteDemoteEmployee();
		   			  break;	
			case 5  : employeeList.menuAssignHandlerToSpecAgent();
					  break;	
			case 6  : employeeList.menuUnassignHandlerToSpecAgent();
					  break;		  
			case 7  : employeeList.menuChangeDoubleAgentStatus();
			  		  break;  		  
			case 8  : employeeList.menuAddUnauthorisedKillToAgent();
	  		  		  break;
			case 9  : employeeList.menuLiftSuspension();
					  break;
			case 10  :employeeList.menuLoadFile();
			  		  break;
			case 11  :employeeList.menuSaveFile();
			  		  break;
			case 12  :employeeList.menuAboutThisSystem();
					  break;
		   	default : break;
		   		}
		   } while(option != 13);
		
		JOptionPane.showMessageDialog(null,"Thank you for using the system.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
		
	}
}
		

