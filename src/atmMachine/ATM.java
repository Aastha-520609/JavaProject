package atmMachine;
import java.io.*;
import java.util.*;
public class ATM {
	String textFileName;
	String[] userDetails = new String[5];
	int menuOption;
	String FirstName = "";
	String LastName= "";
	String Pin = "";
	String Balance = "";
	public ATM()
	{
		userLogin();
	}
	
	public void userLogin()
	{
		welcomeMessage();
		System.out.println("Enter your user ID number which is from 1 to 5");
		String userIdNumber = userInput();
		System.out.println("Enter your 4 Digit Pin: ");
		String userPin = userInput();
		userDetailsCheck(userIdNumber, userPin);
	}
	
	public void welcomeMessage()
	{
		if(FirstName == "")
		{
			System.out.println("Welcome to PNB ATM!");	
		}
		else
		{
			System.out.println("Welcome,"  +FirstName+ "!");
		}	
	}
	
	//checks whether user input - userIDNumber and pin is correct or not.
	public void userDetailsCheck(String userIdNumber, String userPin)
	{
		textFileName = "D:\\ProjectOfJava\\src\\atmMachine\\" +userIdNumber+".txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(textFileName));
			int i=0;
			String getLine = br.readLine();
			while((getLine != null))
			{
				getLine = br.readLine();
				userDetails[i] = getLine;
				i++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		try {
			if(userPin.equals(userDetails[2]))
			{
				FirstName = userDetails[0];
				LastName = userDetails[1];
				Pin = userDetails[2];
				Balance = userDetails[3];
				menu();
			}
			else
			{
				//Restarting process 
				System.out.println("Information Incorrect" + "\n" + "Restarting Process....");
				//updating variables with empty values
				FirstName = "";
				LastName = "";
				Pin = "";
				Balance = "";
				/*for(int i=0; i<=5; i++)
				{
					userDetails[i] = "";
				}*/
				userLogin();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void menu()
	{
		welcomeMessage();
		System.out.println("1.***Check Current Balance***");
		System.out.println("2.***      Withdraw       ***");
		System.out.println("3.***      Deposit        ***");
		System.out.println("4.***  Change PIN Number  ***");
		System.out.println("5.***        EXIT         ***");
		System.out.println("Choose any one option");
		menuOption = Integer.valueOf(userInput());
		switch (menuOption) {
		case 1:
			checkBalance();
			break;
		case 2:
			withdrawAmount();
			break;
		case 3:
			depositAmount();
			break;
		case 4:
			changePin();
			break;
		case 5:
			exit();
			break;
		default:
			System.out.println("Sorry, Command Invalid.");
			menu();
		}
		userLogin();
	}
	
	public void checkBalance()
	{
		System.out.println("Your current balance is: " + userDetails[3]);
		exit();
		System.out.println("ThankYou!");
	}
	
	public void withdrawAmount()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount: ");
		int amount = sc.nextInt();
		if(amount > Integer.valueOf(userDetails[3]))
			{
				System.out.println("This amount cannot be withdrawn.");
				System.out.println("Try to withdraw less amount according to your limit");
				navigationMenu();
			}
			else if(amount > 10000)
			{
				System.out.println("You can only withdraw amount less than or equal to ten thousand at a time");
				navigationMenu();
			}
			else if((amount % 200) == 0 || (amount % 500) == 0 || (amount % 2000) == 0)
            {
	           System.out.println("Your Withdrawl is proceeding. Please wait!!");
	           //Timer if possible
	           System.out.println("Your Transaction is successfull");
	           System.out.println("Thank You");
	           System.out.println("Visit Us Again");
	           
	           
	           Integer balance = Integer.valueOf(userDetails[3]); //converting string Balance to int balance;
		       userDetails[3] = String.valueOf(balance - amount);
	           try{
	        	   
	        	   BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName));
	        	  // writer.write(userDetails[3]);
	        	   writer.write("\n" + FirstName + "\n" + LastName + "\n" + Pin + "\n" + userDetails[3]);
	        	  // writer.flush();
	        	   writer.close();
	              }
	            catch(Exception e)
	            {
	        	   System.out.println(e);
	            }
	           exit();
            }
			else
			{
				System.out.println("Invalid Amount");
				System.out.println("Try to enter a Valid Amount");
			}
	 }
	
	public void depositAmount()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount: ");
		int amount = sc.nextInt();
		if(amount > Integer.valueOf(userDetails[3]))
			{
				System.out.println("This amount cannot be withdrawn.");
				System.out.println("Try to withdraw less amount according to your limit");
				navigationMenu();
			}
			else if(amount > 10000)
			{
				System.out.println("You can only withdraw amount less than or equal to ten thousand at a time");
				navigationMenu();
			}
			else if((amount % 200) == 0 || (amount % 500) == 0 || (amount % 2000) == 0)
            {
	           System.out.println("Your Withdrawl is proceeding. Please wait!!");
	           //Timer if possible
	           System.out.println("Your Transaction is successfull");
	           System.out.println("Thank You");
	           System.out.println("Visit Us Again");
	           
	           
	           Integer balance = Integer.valueOf(userDetails[3]); //converting string Balance to int balance;
		       userDetails[3] = String.valueOf(balance + amount);
	           try{
	        	   
	        	   BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName));
	        	  // writer.write(userDetails[3]);
	        	   writer.write("\n" + FirstName + "\n" + LastName + "\n" + Pin + "\n" + userDetails[3]);
	        	   //writer.flush();
	        	   writer.close();
	              }
	            catch(Exception e)
	            {
	        	   System.out.println(e);
	            }
	           exit();
            }
			else
			{
				System.out.println("Invalid Amount");
				System.out.println("Try to enter a Valid Amount");
			}
	}
	
	public void changePin()
	{
		String newPin;
		String confirmPin;
		System.out.println("Enter your new 4-Digit PIN");
		newPin = userInput();
		while(newPin.length() > 4)
		{
			System.out.println("Only 4 Digit PIN is accepted");
			System.out.println("Enter only 4 DIGIT");
			newPin = userInput();
		}
		System.out.println("Confirm your new 4 DIGIT PIN");
		confirmPin = userInput();
		System.out.println("Change PIN?");
		yesNo();
		System.out.println("Are You Sure?");
		yesNo();
		if(newPin.equals(confirmPin))
		{
			Pin = newPin;
			FileWriter();
			System.out.println("Your PIN has been updated Succesfully");
		}
		else
		{
			System.out.println("Sorry, PIN doesn't match!");
			System.out.println("Restarting Process....");
			changePin();
		}
		exit();
	}
	
	public void yesNo()
	{
		System.out.println("Y/N?");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input=" ";
		try {
			input = br.readLine();
			while(input.isEmpty() || input.matches("[0-9]"))
			{
				System.out.println("Please re-enter the information correctly");
				input = br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(input.equals("No") || input.equals("N") || input.equals("n") || input.equals("no"))
		{
			exit();
		}
	}
	
	//Rewriting the file using new information
	public void FileWriter()
	{
	  try {
		  BufferedWriter writer = new BufferedWriter(new FileWriter(textFileName));
		  writer.write("\n" + FirstName + "\n" + LastName + "\n" + Pin + "\n" + Balance);
		  //writer.flush();
		  writer.close();
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	}
	
	public void exit()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Press Enter to exit...");
		sc.nextLine();
		System.out.println("Goodbye," +FirstName+ "!");
		textFileName = "";
		FirstName = "";
		LastName = "";
		Pin= "";
		Balance = "";
		System.out.println("ThankYou For using Our Bank ATM");
		userLogin();
	}
	
	//takes userinput, if the input is blank or it is of char then it shows invalid and re-entering of details are done
	public String userInput()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String currentInput = "";
		try {
			currentInput = br.readLine();
			while(currentInput.isEmpty() || currentInput.matches("[a-z]"))
			{
				System.out.println("Invalid PIN");
				System.out.println("Please, re-enter information: ");
				currentInput = br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return currentInput;
	}
	
	public void navigationMenu()
	{
		System.out.println("1. |Return to Menu|");
		System.out.println("2. |    Log-out   |");
		menuOption = Integer.valueOf(userInput());
		while (menuOption > 2) {
			System.out.println("Option not found!" + "\n" + "Please, choose one of the above!");
			menuOption = Integer.valueOf(userInput());
		}
		if (menuOption == 1) {
			menu();
		} else if (menuOption == 2) {
			exit();
		}
	}
	
	public static void main(String[] args)
	{
		new ATM();
	}	
}
