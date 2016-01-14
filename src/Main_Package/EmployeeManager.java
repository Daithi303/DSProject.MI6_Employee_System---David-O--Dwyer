package Main_Package;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.io.*;
import java.util.regex.*;

/**
 * @author David O'Dwyer
 *
 */

    public class EmployeeManager implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<Employee> list;
	private File customFile = new File("employeeArrayListCustom.dat");
	private File defaultFile = new File("employeeArrayListDefault.dat");
	ImageIcon icon = new ImageIcon(EmployeeManager.class.getResource("icon_header.jpg"));
	
	/**
	 * This methods constructs an object of type EmployeeManager which consists of
	 * an ArrayList of type Employee
	 */
	public EmployeeManager()
	{
		list = new ArrayList<Employee>();
	}
	
	/**
	 * @return
	 * 
	 * This method is called in the tester class. It displays the system 
	 * options to the user. It returns an int which is used to store the 
	 * choice of the user.
	 */
	public int menuMain()
	{
		int option = 0;
		
		   String opt1 = new String("1. Add an employee");
		   String opt2 = new String("2. Remove an employee");
		   String opt3 = new String("3. List all employees' details");
		   String opt4 = new String("4. Promote/demote an employee");
		   String opt5 = new String("5. Assign a handler to a special agent");
		   String opt6 = new String("6. Unassign a handler from a special agent");
		   String opt7 = new String("7. Change the double agent status of a special agent");
		   String opt8 = new String("8. Increment the unauthorised kill count of an agent by one");
		   String opt9 = new String("9. Lift suspension");
		   String opt10 = new String("10. Load a file");
		   String opt11 = new String("11. Save a file");
		   String opt12 = new String("12. About this system...");
		   String msg = new String("Enter Option:");
		   JTextField opt = new JTextField("");
		   
		   Object message[] = new Object[15];
 
		   message[0] = icon;
		   message[1] = opt1;
		   message[2] = opt2;
		   message[3] = opt3;
		   message[4] = opt4;
		   message[5] = opt5;
		   message[6] = opt6;
		   message[7] = opt7;
		   message[8] = opt8;
		   message[9] = opt9;
		   message[10] = opt10;
		   message[11] = opt11;
		   message[12] = opt12;
		   message[13] = msg;
		   message[14] = opt;
 
		  int response = JOptionPane.showConfirmDialog(null,message,"MI6 Employee System",JOptionPane.OK_CANCEL_OPTION,
		   JOptionPane.PLAIN_MESSAGE);
		   
		   if(response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION)
		   {JOptionPane.showMessageDialog(null,"Thank you for using the system.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			System.exit(0);}
		   else
		   	{
			   try {
				   option = Integer.parseInt( opt.getText());
			   	   }
			   catch (Exception e)
			   {
			   JOptionPane.showMessageDialog(null,"That is not a valid option. Please re-enter your option.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			   }
		   	}
		   return option;
	}	
	
	/**
	 * This method asks the user to select which type of employee they wish to add
	 * to the system. 'if' statements are used to call the appropriate method to 
	 * create the desired employee object.
	 */
	public void menuAddEmployee()
	{
		   String msg = new String("Please select the type of employee you wish to enter:");
		   String[] options = {"Handler", "Field Agent", "Special Agent", "00 Agent",};
		   int option = comboBox(msg, options);
		   
		   try
		   {	   if(option == -666)
		   			{throw new Exception ("Cancelled");}
			
				   if(option == 0)
				   {
					   createHandler();
			   	   }
				   else if(option == 1)
				   {
					   createFieldAgent();
				   }
				   
				   else if(option== 2)
				   {
					   createSpecialAgent();
				   }
				   
				   else
				   {
					   createOoAgent();
				   }
				   
		   }catch (Exception e) {}
					  
	}
	
	/**
	 * This method asks the user to select whether they would like to remove an
	 * employee by ID Number' or by 'List selection'.
	 * 'if' statements are used to select the appropriate method for removing an employee.
	 */
	public void menuRemoveEmployee()
	{
		String msg = new String("Please select the method by which you would like to remove an employee.");
		String[] options = {"Remove by list selection", "Remove by ID Number"};
		int option = comboBox(msg, options);
		 
		try
		{	   
			if(option == -666)
			{throw new Exception ("Cancelled");}
			

			if(option == 0)
			{
				removeByList();
			}
			else
			{
			removeById();
			   }

		}catch (Exception e) {}
		 
	}
	
	/**
	 * This method uses a 'for' loop and 'if' statements to concatenate a string
	 * of the details of each object present in the system. It then prints the result
	 * to the user
	 */
	public void menuListEmployee()
	{
		try
		{if(list.isEmpty())
		{throw new Exception();}
		String temp = "";
		for(int i = 0;i < list.size();i++)
		{
			if(list.get(i) instanceof Handler)
			{ temp = temp + ("Handler - " + list.get(i).toString() + "\n");}
			
			else if(list.get(i) instanceof SpecialAgent)
			{ temp = temp + ("Special Agent - " + list.get(i).toString() + "\n");}
			
			else if(list.get(i) instanceof OoAgent)
			{ temp = temp + ("00 Agent - " + list.get(i).toString() + "\n");}
			
			else if(list.get(i) instanceof FieldAgent)
			{ temp = temp + ("Field Agent - " + list.get(i).toString() + "\n");}
			
		}
		
			JOptionPane.showMessageDialog(null,temp,"MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		}catch (Exception e){JOptionPane.showMessageDialog(null,"There are currently no employees held on the system.");}
	}
	
	/**
	 * This method asks the user whether they would like to promote or demote an 
	 * agent.'if' statements are used to call the appropriate method depending on 
	 * the user's selection.
	 */
	public void menuPromoteDemoteEmployee()
	{
		String msg = new String("Would you like to promote or demote an agent?");
		String[] options = {"Promote", "Demote"};
		int option = comboBox(msg, options);
		   
		   try {
			   if(option == -666)
			   {throw new Exception();}

			   if(option == 0)
			   {
				   promoteEmployee();
			   }
			   else
			   {
				   demoteEmployee();
			   }
 
		   }
		   catch (Exception e) {}
		   
	}
	
	/**
	 * This method asks the user with which method they would like to assign 
	 * a handler to a special agent. 'if' statements are used to call the 
	 * appropriate method depending on the user's selection.
	 */
	public void menuAssignHandlerToSpecAgent()
	{
		String msg = new String("Please select the method by which you wish to select a special agent:");
		String[] options = {"Select by list selection", "Select by ID Number"};
		
		int option = comboBox(msg, options);
		   try {

			   if(option == -666)
			   {throw new Exception();}
			   if(option == 0)
			   {
				   assignHandlerByList();
			   }
			   else
			   {
				   assignHandlerById();
			   } 
		   }
		   catch (Exception e) {}
		   
	}
	
	/**
	 * This method asks the user with which method they would like to unassign 
	 * a handler from a special agent. 'if' statements are used to call the 
	 * appropriate method depending on the user's selection.
	 */
	public void menuUnassignHandlerToSpecAgent()
	{
		String msg = new String("Please select the method by which you wish to select a special agent:");
		String[] options = {"Select by list selection", "Select by ID Number"};
		
		int option = comboBox(msg, options);
		try {

			if(option == -666)
			{throw new Exception();}
			if(option == 0)
			{
				unassignHandlerByList();
			}
			else
			{
				unassignHandlerById();
			} 
		}catch (Exception e) {}

	}
	
	/**
	 * This method asks the user with which method they would like to 
	 * change the 'double agent' status of a special agent. 'if' statements 
	 * are used to call the appropriate method depending on the user's selection.
	 */
	public void menuChangeDoubleAgentStatus()
	{
		String msg = new String("Please select the method by which you wish to select a special agent:");
		String[] options = {"Select by list selection", "Select by ID Number"};
		int option = comboBox(msg, options);
		
		 try {

			  if(option == -666)
			  {throw new Exception();}
			  
			   if(option == 0)
			   {
				   changeStatusByList();
			   }
			   else
			   {
				   changeStatusById();
			   } 
		   }
		   catch (Exception e) {}
		
	}
	
	/**
	 * This method asks the user with which method they would like to 
	 * increment the unauthorised kill count of an agent. 'if' statements 
	 * are used to call the appropriate method depending on the user's selection.
	 */
	public void menuAddUnauthorisedKillToAgent()
	{
		String msg = new String("Please select the method by which you would like to select an agent for unauthorised kill count appending.");
		String[] options = {"By list selection", "By ID Number"};
		int option = comboBox(msg, options);
		
			try
			{
				if(option == -666)
				{throw new Exception ();}
			   if(option == 0)
			   {
				   incrementUnauthorisedKillByList();
			   }
			   else
			   {
				   incrementUnauthorisedKillById();
			   }
			}catch(Exception e) {}
			
	}
	
	/**
	 * This method asks the user with which method they would like to 
	 * lift the suspension of an agent. 'if' statements 
	 * are used to call the appropriate method depending on the user's selection.
	 */
	public void menuLiftSuspension()
	{
		String msg = new String("Please select the method by which you wish to select an agent:");
		String[] options = {"Select by list selection", "Select by ID Number"};
		int option = comboBox(msg, options);
		 try {

			 if(option == -666)
			 {throw new Exception();}
			   if(option == 0)
			   {
				   liftSuspensionByList();
			   }
			   else
			   {
				   liftSuspensionById();
			   } 
		   }
		   catch (Exception e) {}
		
	}
	
	/**
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * 
	 * This method asks the user whether they would like to load the custom file or 
	 * the default file. 'if' statements are used to call the appropriate method 
	 * depending on the user's selection.
	 */
	public void menuLoadFile() throws ClassNotFoundException, IOException
	{
		String msg = new String("Would you like to load the default file or the custom file?"
				+ "\nNOTE: loading a file will replace the current file heldon the system.");
		String [] options = {"Load custom file", "Load default file"};
		int option = comboBox(msg,options);
		
		if(option == 0)
		{loadCustomFile();}
		
		if(option == 1)
		{loadDefaultFile();}
	}
	
	/**
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * 
	 * This method initially displays a jOptionPane stating that pressing OK
	 * will result in the current file state stored on the system being saved to
	 * the custom file on the disk. 'if' statements are used to determine the users
	 * choice. e.g., hitting CANCEL throws an exception which jumps over the
	 * saveCustomFileToDisk() method, hitting OK will not throw the exception and the 
	 * saveCustomFileToDisk() method is called.
	 */
	public void menuSaveFile() throws ClassNotFoundException, IOException
	{
		int response = JOptionPane.showConfirmDialog(null,"Pressing OK will result in the custom file "
				+ "being overwritten by the current file held on the system.",null, JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		
		try
		{
			if(response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION)
			{throw new Exception();}
		saveCustomFileToDisk();
		}catch(Exception e) {}
		
	}
	
	/**
	 * This method displays a description to the user of how the system functions.
	 */
	public void menuAboutThisSystem()
	{String a = new String("This system stores information on FOUR different types of MI6 employee:\n"
				+ "\tHandler.\n"
				+ "\tField Agent.\n"
				+ "\tSpecial Agent.\n"
				+ "\t00 Agent.\n\n"
				+ "A Handler can neither be promoted nor demoted.\n\n"
				+ "A Handler can ONLY be assigned to a Special Agent.\n\n"
				+ "All three types of agent can be promoted or demoted,\n"
				+ "but a Field Agent cannot be demoted nor a 00 agent be promoted.\n\n"
				+ "Special Agents can be either a Double Agent or a non-Double Agent.\n\n"
				+ "All agents have an 'Unathorised Kill' count. Field agents and 'non-Double Agent' Special Agents \n"
				+ "are allowed ONE unauthorised kill, they are suspended on the second. But a 'Double Agent' Special Agent\n"
				+ "is allowed TWO unauthorised kills, they are suspended on the third.\n\n"
				+ "00 agents have a 'licence to kill', therefore they are never suspended regardless of their unauthorised kill count.\n\n"
				+ "ALL types of employee can be ADDED or REMOVED.");
		JOptionPane.showMessageDialog(null,new JTextArea(a),"MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * 
	 * This method first tests if the custom file doesn't exist,
	 * if it doesn't it throws an exception, skipping the code that
	 * reads in a file. If the test proves false (as in, the file exists)
	 * the file is read in.
	 */
	@SuppressWarnings("unchecked")
	private void loadCustomFile() throws ClassNotFoundException, IOException
	{
		try
		{
			if(!customFile.exists())
			{throw new FileNotFoundException();}
		
		FileInputStream fis = new FileInputStream (customFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		list = (ArrayList<Employee>)ois.readObject();
		Integer x = new Integer(ois.readInt());
		Employee.setX(x);
		ois.close();
		fis.close();
		JOptionPane.showMessageDialog(null,"The custom file has been loaded.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		}catch(FileNotFoundException e) {JOptionPane.showMessageDialog(null,"There is currently no custom file saved to disk.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
	}
	
	/**
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 * This method first tests if the default file doesn't exist. If it doesn't
	 *  an exception is thrown where the catch calls the saveDefaultFileToDisk() method
	 *  which writes the default file to the disk. If the test is false (as in, the 
	 *  file exists) the default file is read in.
	 */
	@SuppressWarnings("unchecked")
	private void loadDefaultFile() throws ClassNotFoundException, IOException, FileNotFoundException
	{
		try
		{
		if(!defaultFile.exists())
		{throw new FileNotFoundException();}
		
		}catch(FileNotFoundException e)
		{JOptionPane.showMessageDialog(null,"File doesn't exist, writing file to disk.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		saveDefaultFileToDisk();}
		
		FileInputStream fis = new FileInputStream (defaultFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		//list.addAll((ArrayList<Employee>)ois.readObject());
		list = (ArrayList<Employee>)ois.readObject();
		Integer x = new Integer(ois.readInt());
		Employee.setX(x);
		ois.close();
		fis.close();
		JOptionPane.showMessageDialog(null,"The default file has been loaded.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * @throws IOException
	 * 
	 * This method creates an ArrayList called tempList which is then assigned 
	 * the value of the system's current state, which is stored in the ArrayList 
	 * called list. tempList is then written to the custom file on the disk. 
	 */
	private void saveCustomFileToDisk() throws IOException
	{
		ArrayList<Employee> tempList = list;
		int tempTotal = Employee.getX().intValue();
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = new FileOutputStream (customFile);

		oos = new ObjectOutputStream(fos);
		oos.writeObject(tempList);
		oos.writeInt(tempTotal);
		oos.close();
		fos.close();	
		JOptionPane.showMessageDialog(null,"The file has been saved to the custom file.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * @throws IOException
	 * 
	 * This method creates an ArrayList of type Employee called tempList, 
	 * then populates it with various sub class objects. It also creates
	 * an int called tempTotal which is used to re-value a static Integer variable (x) in the
	 * employee class which is used to auto-generate ID numbers. The ArrayList is then 
	 * written to the default file.
	 */
	private void saveDefaultFileToDisk() throws IOException
	{
		ArrayList<Employee> tempList = new ArrayList<Employee>();
		Handler h1 = new Handler("Bob", "Walsh", "Ireland");
		tempList.add(h1);
		FieldAgent f = new FieldAgent("Claire", "Richards", "America");
		tempList.add(f);
		SpecialAgent s = new SpecialAgent("David","Marcos","Mexico");
		tempList.add(s);
		OoAgent o = new OoAgent("Frank","Penning","England");
		o.setCompanyCar("Alfa Romeo Supercharged Straight-8");
		tempList.add(o);
		Handler h2 = new Handler("Bartek ", "Skibicki", "Poland");
		tempList.add(h2);
		SpecialAgent s2 = new SpecialAgent("Adelaide","Collitard","France");
		tempList.add(s2);
		int tempTotal = tempList.size() + 1;
		
		ObjectOutputStream oos = null;
		
		FileOutputStream fos = new FileOutputStream (defaultFile);

		oos = new ObjectOutputStream(fos);
		
		oos.writeObject(tempList);
		oos.writeInt(tempTotal);
		oos.close();
		fos.close();	
	}
	
	/**
     * @param index
     * This method takes in an index value of a handler-assigned special agent
     * (validated through other methods) and uses the Handler ID variable to identify
     * the special agent's handler. Then both employee objects have the relevant variables
     * re-valued so that the handler is no longer assigned to that agent. 
     */
    private void unassignHandler(int index)
	{
    	if(index == -999)//try and catch here
    	{}
    	else
    	{SpecialAgent s = (SpecialAgent)list.get(index);
    	if(s.isHandler())
    	{Handler h = (Handler)list.get(searchById(s.getHandlerId(),getList()));
		h.setHandlerStatus(true);
		s.setHandlerId("");
		s.setHandler(false);}
    	}
	}

    /**
     * This method calls on other methods to create an ArrayList of type Integer
     * which stores the index values of any Handler-assigned special agents stored on
     * the system. It then uses other methods to display that ArrayList to the user
     * who then chooses a special agent from the list to be unassigned.
     */
    private void unassignHandlerByList()
	{	
		ArrayList<Integer> total = getAssignedSpecAgentList(getSpecAgentList());
		try
		{
		if(total.isEmpty())
		{throw new IOException();}
		String msg = new String("Please select a Special agent");
		int option = comboBox(msg, getStringArrayList(total));
		if(option == -666)
		{throw new Exception();}
		unassignHandler(total.get(option).intValue());
		JOptionPane.showMessageDialog(null,"The handler has been unassigned.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);	
		}catch (IOException e) {JOptionPane.showMessageDialog(null,"There are currently no special agents with a handler assigned.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		 catch (Exception e) {}
		
	}
    
    /**
     * This method asks the user to enter an ID Number of the 
     * special agent they wish to unassign. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered the Handler is unassigned.
     */
    private void unassignHandlerById()
	{
		String msg = new String("Please enter the ID Number of the special agent.");
		int index = byId(msg, getAssignedSpecAgentList(getSpecAgentList()));
		try
		{
		if(index == -666)
		{throw new IOException();}
		
		if(index == -999)
		{throw new Exception();}
		
		unassignHandler(index);
		JOptionPane.showMessageDialog(null,"The handler has been unassigned.");
		}catch(IOException e) {}
		 catch (Exception e){JOptionPane.showMessageDialog(null,"There are no assigned special agents with that ID Number present in the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
	}

    /**
	 * @param index
	 * This method takes in an index value (validated through other methods)
	 * of a chosen special agent with no handler assigned. A 'for' loop
	 * is then used to iterate over the list on the system to find an unassigned handler. If 
	 * one is found the relevant variables of both the handler and the special agent are re-valued so 
	 * that the handler is assigned to the special agent.
	 */
	private int assignHandler(int index)
	{
		for(int i = 0;i < list.size(); i++)
		{
			if(list.get(i) instanceof Handler)
			{	Handler h = (Handler)list.get(i);
				if(h.getHandlerStatus())
				{	
					h.setAssignedAgentId(list.get(index).getIdNumber());
					h.setHandlerStatus(false);
					SpecialAgent s = (SpecialAgent)list.get(index);
					s.setHandlerId(list.get(i).getIdNumber());
					s.setHandler(true);
					return i;}
			}
		}		
		return -999;	
	}
	
	/**
     * This method calls on other methods to create an ArrayList of type Integer
     * which stores the index values of any special agents without handlers stored on
     * the system. It then uses other methods to display that ArrayList to the user
     * who then chooses a special agent from the list to be assigned a handler.
     */
	private void assignHandlerByList()
	{	
		ArrayList<Integer> total = getUnassignedSpecAgentList(getSpecAgentList());
		try
		{
		if(total.isEmpty())
		{throw new IOException();}
		
		String msg = new String("Please select a Special agent");
		int option = comboBox(msg, getStringArrayList(total));   
		if(option == -666)
		{throw new Exception();}
		assignHandler(total.get(option).intValue());   
		JOptionPane.showMessageDialog(null,"A handler has been assigned.");	
		}catch (IOException e) {JOptionPane.showMessageDialog(null,"There are currently no special agents available for handler assignment.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		 catch(Exception e){}
	}
	
	/**
     * This method asks the user to enter an ID Number of the 
     * special agent they wish to assign a handler. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered a Handler is assigned.
     */
	private void assignHandlerById()
	{
		String msg = new String("Please enter the ID Number of the special agent.");
		int index = byId(msg, getUnassignedSpecAgentList(getSpecAgentList()));
		try
		{
			if(index == -666)
			{throw new IOException();}
			
			if(index == -999)
			{throw new Exception();}
			assignHandler(index);
			JOptionPane.showMessageDialog(null,"The handler has been unassigned.");
			}catch(IOException e) {}
			catch (Exception e){JOptionPane.showMessageDialog(null,"There are no unassigned special agents with that ID Number present in the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		
	}

	/**
	 * @param index
	 * This method re-values the 'Status' variable of a suspended agent from 
	 * 'false' to 'true'. ('False' meaning the agent was suspended.
	 */
	private void liftSuspension(int index)
	{
		((FieldAgent)list.get(index)).setStatus(true);
	}

	/**
	 *This method uses another method to create an ArrayList of type Integer
	 *which stores the index values of any suspended agents stored on the system.
	 *The ArrayList is validated through methods and exceptions. The list is displayed 
	 *to the user who then selects an agent. If no exception are thrown the selected agent has their suspension lifted
	 *via the liftSuspension() method.
	 */
	private void liftSuspensionByList()
	{
		ArrayList<Integer> total = getSuspendedAgentList();
		try
		{
		if(total.isEmpty())
		{throw new IOException();}
		String msg = new String("Please select an agent");
		int option = comboBox(msg, getStringArrayList(total));  
		if(option == -666)
		{throw new Exception();}
		liftSuspension(total.get(option).intValue());
		JOptionPane.showMessageDialog(null,"The suspension has been lifted.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		}catch (IOException e) {JOptionPane.showMessageDialog(null,"There are currently no suspended agents in the system.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		 catch(Exception e) {}
	}
	
	/**
     * This method asks the user to enter an ID Number of a 
     * suspended agent. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered the agent has their suspension
     * lifted via the liftSuspension() method.
     */
	private void liftSuspensionById()
	{
		String msg = new String("Please enter the ID Number of the agent.");
		int index = byId(msg,getSuspendedAgentList());
		
		try
		{
		if(index == -666)
		{throw new IOException();}	
			
		if(index == -999)
		{throw new Exception();}

		liftSuspension(index);
		JOptionPane.showMessageDialog(null,"The suspension has been lifted.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		}catch(IOException e) {}
		catch (Exception e){JOptionPane.showMessageDialog(null,"There are no suspended agents with that ID Number present in the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		
	}
	
	/**
	 * @return
	 * This method iterates over the list on the system to find the index value
	 * of all agents on the system who are suspended. The method then returns an
	 * ArrayList of type Integer containing the index values of those employees.
	 */
	private ArrayList<Integer> getSuspendedAgentList()
	{	
		Integer index = new Integer(0);
		ArrayList<Integer> total = new ArrayList<Integer>();
		for(int i = 0;i < list.size();i++)
		{	
			if(!(list.get(i) instanceof Handler))
			
				{if(((FieldAgent)list.get(i)).isStatus() == false)
					{index = new Integer(i);
					total.add(index);}
				}
		}
		return total;
		
	}
	
	/**
	 * @param total
	 * @return
	 * This method iterates over the list on the system to find the index value
	 * of all special agents on the system who do not have a handler assigned. The method then returns an
	 * ArrayList of type Integer containing the index values of those employees.
	 */
	private ArrayList<Integer> getUnassignedSpecAgentList(ArrayList<Integer> total)
	{
		Integer index = new Integer(0);
		ArrayList<Integer> newTotal = new ArrayList<Integer>();
		for(int i = 0;i < total.size();i++)
		{
			
				if(((SpecialAgent)list.get(total.get(i).intValue())).isHandler() == false)
					{index = new Integer(total.get(i).intValue());
					newTotal.add(index);}
		}
		
		return newTotal;
		
	}
	
	/**
	 * @param total
	 * @return
	 * This method iterates over the list on the system to find the index value
	 * of all special agents on the system who have a handler assigned. The method then returns an
	 * ArrayList of type Integer containing the index values of those employees.
	 */
	private ArrayList<Integer> getAssignedSpecAgentList(ArrayList<Integer> total)
	{	
		Integer index = new Integer(0);
		ArrayList<Integer> newTotal = new ArrayList<Integer>();
		for(int i = 0;i < total.size();i++)
		{
		
			if(((SpecialAgent)list.get(total.get(i).intValue())).isHandler())
				{index = new Integer(total.get(i).intValue());
				newTotal.add(index);}
		}
		return newTotal;
	
	}

	/**
	 * @return
	 * This method iterates over the list on the system to find the index value
	 * of all special agents on the system. The method then returns an
	 * ArrayList of type Integer containing the index values of those employees.
	 */
	private ArrayList<Integer> getSpecAgentList()
	{
		Integer index = new Integer(0);
		ArrayList<Integer> total = new ArrayList<Integer>();
		for(int i = 0;i < list.size();i++)
		{
			if(list.get(i) instanceof SpecialAgent)
				{index = new Integer(i);
					total.add(index);}
		}
		
		return total;
	}
	
	/**
	 * @return
	  * This method iterates over the list on the system to find the index value
	 * of all employees on the system. The method then returns an
	 * ArrayList of type Integer containing the index values of those employees.
	 */
	private ArrayList<Integer> getList()
	{
		Integer index = new Integer(0);
		ArrayList<Integer> total = new ArrayList<Integer>();
		for(int i = 0;i < list.size();i++)
		{
			index = new Integer(i);
			total.add(index);
		}
		
		return total;
	}
	
	/**
	 * @param total
	 * @return
	 * This method takes in an ArrayList of type Integer which contains the index values
	 * of a particular subset of the list on the system, depending on the method
	 * used to create the ArrayList. It then uses a 'for' loop to format some of 
	 * the data stored in the objects stored at those index locations into a string 
	 * Array, with each string representing an object stored in the list.
	 */
	private String[] getStringArrayList(ArrayList<Integer> total)
	{
		String agentSelection = null;
		String[] options = new String[total.size()];
		for(int i = 0;i < total.size();i++)
		{
			if(list.get(total.get(i).intValue()) instanceof Handler)
				agentSelection = "Handler";
			else if(list.get(total.get(i).intValue()) instanceof SpecialAgent)
				agentSelection = "Special Agent";
			else if(list.get(total.get(i).intValue()) instanceof OoAgent)
				agentSelection = "00 Agent";
			else if(list.get(total.get(i).intValue()) instanceof FieldAgent)
				agentSelection = "Field Agent";
			
			
			options[i] = (agentSelection + " - " + "Name: "+ list.get(total.get(i).intValue()).getfName() + " " + 
		list.get(total.get(i).intValue()).getlName() + ", ID Number: " + 
		list.get(total.get(i).intValue()).getIdNumber());}
		return options;
		
	}
	
	/**
	 * This method takes in an ArrayList,created by another method,
	 * which is made up of the index values of any special agents stored on the system.
	 * It then calls the toDoubleAgent() method which changes the value of the
	 * boolean variable 'doubleAgent'.
	 */
	private void changeStatusByList()
	{
		ArrayList<Integer> total = getSpecAgentList();
		String msg = new String("Please select a Special agent");
		
		try
		{
		if(total.isEmpty())
		{throw new IOException();}
		int option = comboBox(msg, getStringArrayList(total)); 
		if(option == -666)
		{throw new Exception();}
		toDoubleAgent(total.get(option).intValue()); 
		((SpecialAgent)list.get(total.get(option).intValue())).setUnauthorisedKills(0);
		JOptionPane.showMessageDialog(null,"The double agent status of this agent has been changed and its unauthorised \n"
				+ "kill count reset to zero.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);	
		}catch (IOException e) {JOptionPane.showMessageDialog(null,"There are currently no special agents held within the system.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		 catch(Exception e){}
	}
	
	/**
	 * This method asks the user to enter an ID Number of a 
     * special agent. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered the 
     * agent has their 'doubleAgent' boolean variable switched via the toDoubleAgent() method.
     */
	private void changeStatusById()
	{
		String msg = new String("Please enter the ID Number of the special agent.");
		int index = byId(msg,getSpecAgentList());
		try
		{
			if(index == -666)
			{throw new IOException();}
			
			if(index == -999)
			{throw new Exception();}
		
		toDoubleAgent(index);
		((SpecialAgent)list.get(index)).setUnauthorisedKills(0);
		JOptionPane.showMessageDialog(null, "The double agent status of this agent has been changed and its unauthorised \n"
				+ "kill count reset to zero.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		}catch(IOException e) {}
		catch (Exception e){JOptionPane.showMessageDialog(null,"There are no special agents with that ID Number present in the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
	}
	
	/**
	 * @param index
	 * This method switches the 'doubleAgent' boolean variable from it's 
	 * current state to it's alternate state.
	 */
	private void toDoubleAgent(int index)
	{   
		SpecialAgent s = (SpecialAgent)list.get(index);
		if(s.isDoubleAgent())
		((SpecialAgent)list.get(index)).setDoubleAgent(false);
		else
		((SpecialAgent)list.get(index)).setDoubleAgent(true);
	}

	/**
	 * @param index
	 * This method increments an employee's unauthorised kill count by one and also
	 * performs additional functions such as suspending an agent if the agent has reached their
	 * unauthorised kill limit.
	 */
	private void incrementUnauthorisedKill(int index)
	{
		if(list.get(index) instanceof Handler)
			JOptionPane.showMessageDialog(null,"A Handler does not have an authorised kill count.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		else if(((FieldAgent)list.get(index)).isStatus() == false)
			{JOptionPane.showMessageDialog(null,"An agent's unauthorised kill count can't be incremented while the agent is suspended.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		
		else
		{	int a = ((FieldAgent)list.get(index)).getUnauthorisedKills() + 1;
			((FieldAgent)list.get(index)).setUnauthorisedKills(a);
			
			if(list.get(index) instanceof SpecialAgent)
			{
				if(((SpecialAgent)list.get(index)).isDoubleAgent())
				{
					if(((SpecialAgent)list.get(index)).getUnauthorisedKills() % 3 == 0)
					{((SpecialAgent)list.get(index)).setStatus(false);
					JOptionPane.showMessageDialog(null,"The agent is now suspended","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
				}
				
				else if(((SpecialAgent)list.get(index)).isDoubleAgent() == false)
				{
					if(((SpecialAgent)list.get(index)).getUnauthorisedKills() % 2 == 0)
					{((SpecialAgent)list.get(index)).setStatus(false);
					JOptionPane.showMessageDialog(null,"The agent is now suspended","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
				}
			}
			
			else if(list.get(index) instanceof FieldAgent)
			{
				if(((FieldAgent)list.get(index)).getUnauthorisedKills() % 2 == 0 && ((FieldAgent)list.get(index)).isLicenceToKill() == false)
				{((FieldAgent)list.get(index)).setStatus(false);
				JOptionPane.showMessageDialog(null,"The agent is now suspended","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
			}
		}
			
	}
	
	/**
	 * This method creates an ArrayList of type Integer, created via another method
	 * which contains the index values of a particular subset of the list stored on the system.
	 * In this case the method called returns the index values of the entire list stored on the system.
	 * It then displays the objects with the respective index values to the user. The object's index value
	 * chosen by the user, after being validated through methods and exceptions, is then sent into the 
	 * incrementUnauthorisedKill() method.
	 */
	private void incrementUnauthorisedKillByList()
	{
		String msg = new String("Please select an agent.");
		
		ArrayList<Integer> total = getList();
		
		try
		{
		if(total.isEmpty())
		{throw new IOException();}
		
		int option = comboBox(msg, getStringArrayList(total));
		if(option == -666)
		{throw new Exception();}
		incrementUnauthorisedKill(option);
		JOptionPane.showMessageDialog(null,"The agent's kill count has been incremented by one.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		}catch (IOException e) {JOptionPane.showMessageDialog(null,"There are currently no employees held in the system.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
		 catch(Exception e) {}
	}
	
	/**
	 * This method asks the user to enter an ID Number of an 
     * agent. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered the 
     * agent has their unauthorised kill count incremented by one via the incrementUnauthorisedKill() method.
     */
	private void incrementUnauthorisedKillById()
	{
		String msg = new String("Please enter the ID Number of an agent.");
		int index = byId(msg, getList());
		try
		{
		if(index == -666)
		{throw new IOException();}
			
		if(index == -999)
		{throw new Exception();}
		
		incrementUnauthorisedKill(index);
		JOptionPane.showMessageDialog(null,"The agent's kill count has been incremented by one.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
		}catch(IOException e) {}
		catch (Exception e){JOptionPane.showMessageDialog(null,"There are no agents with that ID Number present in the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
	}
	
	/**
	 * This method calls the takeInEmployees() method which takes in data 
	 * from the user. Then the data is used to create an object of type Handler
	 * which is added to the list held on the system by the addEmployeeToList() method.
	 */
	private void createHandler()
	{
			try
			{
			String[] temp = takeInEmployeeValues();
			if(temp == null)
			{throw new Exception();}
			Handler h = new Handler(temp[0], temp[1], temp[2]);
	   		addEmployeeToList(h);
	   		JOptionPane.showMessageDialog(null,"Handler is added to the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			}
			catch (Exception e){}
	}
	
	/**
	 * This method calls the takeInEmployees() method which takes in data 
	 * from the user. Then the data is used to create an object of type FieldAgent
	 * which is added to the list held on the system by the addEmployeeToList() method.
	 */
	private void createFieldAgent()
	{	
			try
			{
			String[] temp = takeInEmployeeValues();
			if(temp == null)
			{throw new Exception();}
			FieldAgent f = new FieldAgent(temp[0], temp[1], temp[2]);
			addEmployeeToList(f);
			JOptionPane.showMessageDialog(null,"Field Agent is added to the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			}catch (Exception e){}
	}
	
	/**
	 * This method calls the takeInEmployees() method which takes in data 
	 * from the user. Then the data is used to create an object of type SpecialAgent
	 * which is added to the list held on the system by the addEmployeeToList() method.
	 */
	private void createSpecialAgent()
	{	
			try
			{
			String[] temp = takeInEmployeeValues();
			if(temp == null)
			{throw new Exception();}
			SpecialAgent s = new SpecialAgent(temp[0], temp[1], temp[2]);
			addEmployeeToList(s);
			JOptionPane.showMessageDialog(null,"Special Agent is added to the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			}catch (Exception e){}
	}
		
	/**
	 * This method calls the takeInEmployees() method which takes in data 
	 * from the user. Then the data is used to create an object of type OoAgent
	 * which is added to the list held on the system by the addEmployeeToList()
	 * method.
	 */	
	private void createOoAgent()
	{
			try
			{
			String[] temp = takeInEmployeeValues();
			if(temp == null)
			{throw new IOException();}
			OoAgent o = new OoAgent(temp[0], temp[1], temp[2]);
			String a = companyCar();
			if( a == null)
			{throw new Exception();}
			o.setCompanyCar(a);
			addEmployeeToList(o);
			
			JOptionPane.showMessageDialog(null,"00 Agent is added to the system","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			}catch (IOException e){}
			 catch(Exception e){}
	}
	
	/**
	 * @return
	 * This method displays to the user a selection of company cars to choose from.
	 * The user's choice is then returned as a string to the calling method (createOoAgent())
	 */
	private String companyCar()
	{
			String msg = new String("Please select a company car.");
			String[] carOptions = {"Aston Martin DB5","Alfa Romeo Supercharged Straight-8","AMC Matador","1930 Bentley 4½ Litre"}; 
			
			int a = comboBox(msg, carOptions);
			if(a == -666)
			{return null;}
			return carOptions[a];
	}
		
	/**
	 * @return
	 * This method asks the user to input three fields of data which are required for
	 * the construction of an employee object. The three data fields are then stored as 
	 * type String. Regular Expressions are used to validate the user's input.
	 */
	private String[] takeInEmployeeValues()
	{
			String[] temp = new String[3];  
			String msg1 = new String("1. Please enter first name :");
			String msg2 = new String("2. Please enter second name :");
			String msg3 = new String("3. Please enter country of origin :");
			JTextField ans1 = new JTextField("");
			JTextField ans2 = new JTextField("");
			JTextField ans3 = new JTextField("");
			   
			Object message[] = new Object[6];

			message[0] = msg1;
			message[1] = ans1;
			message[2] = msg2;
			message[3] = ans2;
			message[4] = msg3;
			message[5] = ans3;

			boolean formComplete = true;
			do
			{
			formComplete = true;
			int response = JOptionPane.showConfirmDialog(null,message,"MI6 Employee System",JOptionPane.OK_CANCEL_OPTION,
					                                        JOptionPane.PLAIN_MESSAGE);
			   
			if(response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION)
				   return null;
			else
			{ 		
				try {  		
					temp[0] = (ans1.getText());
					temp[1] = (ans2.getText());
					temp[2] = (ans3.getText());

					Pattern p = Pattern.compile("  |.*\\d.*|[^a-zA-Z'\\s]");
					Matcher m; 
					
					for(int i = 0; i < temp.length;i++)
					{
						m = p.matcher(temp[i]);
						if(temp[i].length() == 0 || m.find())
						{throw new Exception("Form Incomplete!");}
						temp[i] = capitalise(temp[i]);
						
					}
				   	} 
				  	catch (Exception e)
				  	{
					formComplete = false;
					JOptionPane.showMessageDialog(null,"Form Incomplete! Please fill in all fields, leave ONE space only between words\n"
							+ " and ensure there are no numerical values used","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
				    }
			   	}
			   } while(!formComplete);
			   return temp;
	}
	
	/**
	 * @param temp
	 * @return
	 * This method takes in a string, splits the string where ever a space is present
	 * then stores the split sub strings in an array titled 'words'. Nested 'for' loops are then used to
	 * re-format each word contained in the string array to a 'first letter: to upper case' format.
	 * The separated words are then reconstituted via a StringBuilder object. A String made from the StringBuilder
	 * object is then returned.
	 */
	private String capitalise(String temp)
	{
			String[] words = temp.split("\\s");
		   	for(int i = 0;i < words.length;i++)
		   	{words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase() + " ";}

		   	StringBuilder builder = new StringBuilder();
		   	for (String value : words) 
		   	{
		   	    builder.append(value);
		   	}
		   	temp = (builder.toString()).trim();
		   	
		   	return temp;
	}

	/**
	 * @param p
	 * This method adds an employee object to the lit on the system.
	 */	
	private void addEmployeeToList(Employee p)
	{
			try 
			{
				list.add(p);
			}
				
			catch (Exception e)
					{
					JOptionPane.showMessageDialog(null,"Insert Into List Error","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);	
					}	
	}
		
	/**
     * This method calls on other methods to create an ArrayList of type Integer
     * which stores the index values of any agents stored on
     * the system. It then uses other methods to display that ArrayList to the user
     * who then chooses an agent from the list to be removed.
     */
	private void removeByList()
	{	
			ArrayList<Integer> total = getList();
			try
			{
			if(total.isEmpty())
			{throw new IOException ();}
			
			
			String msg = new String("Please select the employee you would like to remove.");
			int index = comboBox(msg, getStringArrayList(total));
			if(index == -666)
			{throw new Exception();}
			remove(index);
			JOptionPane.showMessageDialog(null,"The employee has been removed","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			}catch(IOException e){JOptionPane.showMessageDialog(null,"The are currently no employees to remove.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
			 catch(Exception e) {};
	}
		
	/**
	 * This method asks the user to enter an ID Number of an 
     * agent. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered the 
     * agent is removed from the system.
     */	
	private void removeById()
	{
			String msg = new String("Please enter the ID Number of the Employee you wish to remove.");
			int index = byId(msg, getList());
			try
			{
			if(index == -666)
			{throw new IOException();}
			
			if(index == -999)
			{throw new Exception();}

			remove(index);
			JOptionPane.showMessageDialog(null,"The employee has been removed","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			}catch(IOException e) {}
			catch(Exception e){JOptionPane.showMessageDialog(null,"ID does not Exist.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
			 
	}
	
	/**
	 * @param index
	 * This method, before removing an object from the list, performs additional functions
	 * depending on the type of object it is.
	 */
	private void remove(int index)
	{
			if(list.get(index) instanceof SpecialAgent)
			{unassignHandler(index);
			list.remove(index);}
			else if(list.get(index) instanceof Handler)
			{int tempIndex = searchById(((Handler)list.get(index)).getAssignedAgentId(), getList());
			unassignHandler(tempIndex);
			list.remove(index);}
			else
			{list.remove(index);}
	}

	/**
	 * This method asks the user how they would like to promote an employee. Then calls 
	 * the appropriate method depending on the user's choice.
	 */
	private void promoteEmployee()
	{
			String msg = new String("PLease select the method by which you wish to promote an employee.");
			String[] options = {"Promote by list selection", "Promote by ID Number"};
			int option = comboBox(msg, options);
			  try {

				  if(option == -666)
				  {throw new Exception();}
				   if(option == 0)
				   {
					   promoteByList();
				   }
				   else
				   {
					   promoteById();
				   } 
			   }
			   catch (Exception e) {}		
	}
	
	/**
	 * This method asks the user how they would like to demote an employee. Then calls 
	 * the appropriate method depending on the user's choice.
	 */	
	private void demoteEmployee()
	{
			String msg = new String("PLease select the method by which you wish to demote an employee.");
			String[] options = {"demote by list selection", "demote by ID Number"};
			int option = comboBox(msg, options);
			
			  try {
				  if(option == -666)
				  {throw new Exception();}
				   if(option == 0)
				   {
					   demoteByList();
				   }
				   else
				   {
					   demoteById();
				   }

				 
			   }
			   catch (Exception e) {}	
			
	}
	
	/**
     * This method calls on other methods to create an ArrayList of type Integer
     * which stores the index values of any agents stored on
     * the system. It then uses other methods to display that ArrayList to the user
     * who then chooses an agent from the list to be promoted.
     */
	private void promoteByList()
	{	
			ArrayList<Integer> total = getList();
			
			try
			{if(total.isEmpty())
			{throw new IOException();}
			String msg = new String("Please select the employee you would like to promote.");
			int index = comboBox(msg, getStringArrayList(total));
			if(index == -666)
			{throw new Exception();}
			promote(index);	 
			}catch (IOException e){JOptionPane.showMessageDialog(null,"The are currently no employees to promote.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
			 catch(Exception e) {}
	}	
	
	/**
	 * This method asks the user to enter an ID Number of an 
     * agent. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered the 
     * agent is promoted. 
	 */
	private void promoteById()
	{
			String msg = new String("Please enter the ID Number of the Employee you wish to promote.");
			int index = byId(msg, getList());
			try
			{
				if(index == -666)
				{throw new IOException();}
				
				if(index == -999)
				{throw new Exception();}
			promote(index);	
			}catch(IOException e) {}
			 catch(Exception e){JOptionPane.showMessageDialog(null,"ID does not Exist.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
	}
		
	/**
	 * This method calls on other methods to create an ArrayList of type Integer
     * which stores the index values of any agents stored on
     * the system. It then uses other methods to display that ArrayList to the user
     * who then chooses an agent from the list to be demoted. 
	 */
	private void demoteByList()
	{
			String msg = new String("Please select the employee you would like to demote.");
			ArrayList<Integer> total = getList();
			try
			{if(total.isEmpty())
			{throw new IOException();}
			int index = comboBox(msg, getStringArrayList(total));
			if(index == -666)
			{throw new Exception();}
			demote(index);	
			}catch (IOException e){JOptionPane.showMessageDialog(null,"The are currently no employees to demote.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
			 catch(Exception e) {}
	}
	
	/**
	 * This method asks the user to enter an ID Number of an 
     * agent. Their choice is validated through
     * other methods and exceptions. If a valid ID Number is entered the 
     * agent is demoted. 
	 */
	private void demoteById()
	{
			String msg = new String("Please enter the ID Number of the Employee you wish to demote.");
			int index = byId(msg, getList());
			try
			{
				if(index == -666)
				{throw new IOException();}
				
				if(index == -999)
				{throw new Exception();}
			demote(index);	
			}catch(IOException e) {}
			catch(Exception e){JOptionPane.showMessageDialog(null,"ID does not Exist.","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
	}
	
	/**
	 * @param index
	 * This method uses 'if else' statements to determine the type of employee
	 * being promoted. Based on the type of employee it then builds an object of higher rank, then
	 * copies the objects values into the new object. The new object is then 
	 * stored at the previous object's position in the list.
	 */
	private void promote(int index)
	{
			try
			{
			 if(list.get(index) instanceof Handler || list.get(index) instanceof OoAgent)
				 JOptionPane.showMessageDialog(null,"A handler or a 00 agent cannot be promoted","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			 	else
			 	{
			 		if(list.get(index) instanceof SpecialAgent)
			 		{OoAgent o = new OoAgent(list.get(index).getfName(),list.get(index).getlName(),
			 		 list.get(index).getCountryOfOrigin());
			 		 String a = companyCar();
					 if( a == null)
					 {throw new Exception();}
					 o.setCompanyCar(a);
			 		 unassignHandler(index);
			 		 list.set(index, o);
		 			 JOptionPane.showMessageDialog(null,"The employee has been promoted with its unauthorised kill count\n "
		 					+ "reset to zero and a new ID Number is issued","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			 		}	
			 		
			 		else
			 		{SpecialAgent s = new SpecialAgent(list.get(index).getfName(),list.get(index).getlName(),
			 		list.get(index).getCountryOfOrigin());
			 		list.set(index, s);
			 		JOptionPane.showMessageDialog(null,"The employee has been promoted with its unauthorised kill count\n "
		 					+ "reset to zero and a new ID Number is issued","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			 		}
			 		
			 	}
			}catch (Exception e) {}
	}
		
	/**
	 * @param index
	 * This method uses 'if else' statements to determine the type of employee
	 * being demoted. Based on the type of employee it then builds an object of lower rank, then
	 * copies the objects values into the new object. The new object is then 
	 * stored at the previous object's position in the list.
	 */	
	private void demote(int index)
	{
		
			 try
			 {
			 		if(list.get(index) instanceof SpecialAgent)
			 		{FieldAgent f = new FieldAgent(list.get(index).getfName(),list.get(index).getlName(),
			 		list.get(index).getCountryOfOrigin());
			 		unassignHandler(index);
			 		list.set(index, f);
		 			JOptionPane.showMessageDialog(null,"The employee has been demoted with its unauthorised kill count\n "
		 					+ "reset to zero and a new ID Number is issued","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			 		}	
			 		
			 		else if(list.get(index) instanceof OoAgent)
			 		{SpecialAgent s = new SpecialAgent(list.get(index).getfName(),list.get(index).getlName(),
			 		list.get(index).getCountryOfOrigin());
			 		list.set(index, s);
			 		JOptionPane.showMessageDialog(null,"The employee has been demoted with its unauthorised kill count\n "
		 					+ "reset to zero and a new ID Number is issued","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);
			 		}
			 		
			 		else if(list.get(index) instanceof Handler || list.get(index) instanceof FieldAgent)
			 		{throw new Exception("Demotion not possible");}
			 		
			 }
			 catch (Exception e)
			 {JOptionPane.showMessageDialog(null,"A handler or a field agent cannot be demoted","MI6 Employee System",JOptionPane.PLAIN_MESSAGE);}
			 	
	}
	
	/**
	 * @param msg
	 * @param options
	 * @return
	 * This method displays a message and a string array in the form of a ComboBox
	 * to the user. The index of the selected ComboBox option is then returned.
	 */
	private int comboBox(String msg,String[] options)
	{
			JComboBox<String> option = new JComboBox<String>(options);
			Object message[] = new Object[2];
			
			message[0] = msg;
			message[1] = option;
			
			int response = JOptionPane.showConfirmDialog(null,message,"MI6 Employee System",JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.PLAIN_MESSAGE);
			if(response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION)
				{return -666;}
			
			return option.getSelectedIndex();
		}
	
	/**
	 * @param msg
	 * @param list
	 * @return
	 * This method displays a message to the user and takes in string representing
	 * an ID Number. The string is then sent into the searchById()method along with an
	 * ArrayList of type Integer. The returned result of the searchById() method is an int.
	 */
	private int byId(String msg, ArrayList<Integer> list)
	{
			JTextField ans;
			String tempMsg = msg;
			ans = new JTextField("");
			
			Object[] message = new Object[2];
			message[0] = tempMsg;
			message[1] = ans;
			
			int response = JOptionPane.showConfirmDialog(null,message,"MI6 Employee System",JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
			int index = searchById(ans.getText(), list);	
			try
			{
			if(response == JOptionPane.CANCEL_OPTION || response == JOptionPane.CLOSED_OPTION)
			{throw new Exception();}
			return index;
			}catch(Exception e) {return -666;}
			
	}

	/**
	 * @param idNumber
	 * @param tempList
	 * @return
	 * 
	 * This method uses the int values stored in the ArrayList<Integer> to iterate over the
	 * objects stored in the system list at those corresponding index locations. It compares
	 * the ID Number sent in (the user's inputed ID NUmber) with the ID NUmbers of mentioned objects
	 * stored within the system.
	 */
	private int searchById(String idNumber, ArrayList<Integer> tempList)
	{
			for(int i = 0;i < tempList.size();i++)
			{
				if(list.get(tempList.get(i).intValue()).getIdNumber().equalsIgnoreCase(idNumber))
				return tempList.get(i).intValue();
			}
			
			return -999;
	}
}

