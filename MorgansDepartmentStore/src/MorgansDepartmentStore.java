import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MorgansDepartmentStore {
	
	static Scanner myScanner;
	static Scanner datScanner;
	static double [][] bonusamounts = new double [7][5];
	static double [][] referenceamounts = new double [7][5];
	static double ctotal = 0;
	static int ctr;
	static double caverage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		init();
		choice();
	
	}
	
	public static void init() {
		
		//scanner preperation through the catch of the file
		myScanner = new Scanner (System.in);
		datScanner = new Scanner (System.in);
		
		try {
			datScanner = new Scanner(new File("project1data.dat"));
			datScanner.useDelimiter(System.getProperty("line.separator"));
		} catch (FileNotFoundException e1) {
			System.out.println("File error");
			System.exit(1);
			}
		
		//setting the bonus array amounts to zero for the accumulation
		for (int i = 0; i < referenceamounts.length; i++ ) {
			for (int x = 0; x < referenceamounts[i].length; x++) {
				bonusamounts[i][x] = 0.00;
			}
		}

	}
	
	public static void inputdata() {
		//local variable declaration
		String datRecord = "";
    	String datRow[];
		int row = 0;
		int iweeks = 0;
		int ireviews = 0 ;
		boolean invalid = true;
		//this is used to bring out the string varible so i can transfer it to a integer
		String iString;
		
		//record reading
		while(datScanner.hasNextLine()) {
    		try {
    			//read a record
    			datRecord = datScanner.nextLine();
    			//break the record into individual strings
    			datRow = datRecord.split(",");
    			//allocate a row in the pay scale table
    			referenceamounts[row] = new double[datRow.length];
    			//populates a row
    			for(int i = 0; i < datRow.length; i++) {
    				referenceamounts[row][i] = Double.parseDouble(datRow[i]);
    			}
    			//advance to the next row
    			row ++;
    			
    		} catch (Exception e) {
    			System.out.println("Error reading rate file, program transition");
    			System.exit(1);
    			}
    }
		//validation loop for weeks worked till valid input
		while (invalid == true) {
			try {
				System.out.println("How many full weeks have you worked?");
				iString = myScanner.next();
				iweeks = Integer.parseInt(iString);
				invalid = false;
				}
			catch (Exception e) {
				System.out.println("Invalid input. Please enter a whole number.");
					}
		}
			
		//doing an if then statement to set any worked weeks above 6 to 6.
		if (iweeks > 4) {
			iweeks = 6;
		}
		
		//restating the invalid to true so it can hit the loop
		invalid = true;
		
		//loop to validate the positive reviews for input
		while (invalid == true) {
			try {
				System.out.println("How many positive reviews have you had?");
				iString = myScanner.next();
				ireviews = Integer.parseInt(iString);
				invalid = false;
			}
			catch (Exception e) {
				System.out.println("Invalid input. Please enter a whole number.");
			}
		}
		
		//restating the invalid to true so it can hit the loop
		invalid = true;
		
		//doing an if then statement to set any possitive reviews above 4 to 4.
		if (ireviews > 4) {
			ireviews = 4;
		}
		
		//adding to the bonusamounts and the referenceamounts for the correct bonus 
		bonusamounts[iweeks][ireviews] += referenceamounts[iweeks][ireviews];
		
		//see how many sales there are
		ctr++;
		
		//accumulation for the total amount using the variables for bonusamounts array
		ctotal = ctotal + bonusamounts[iweeks][ireviews];
		
		//re asking the choice
		choice();
		
}

	
	public static void inputsummary() {
		
		//average total
		caverage = ctotal / ctr;
		
		//System.out.format("%-5s%5s%10s%5s%10s%5s%10s%5s%10s%5s%10s\n", "0" , " " , bonusamounts[0][0] , " " , bonusamounts[0][1], " " , bonusamounts[0][2], " ", bonusamounts[0][3], " ", bonusamounts[0][4]);
		System.out.format("%-17s%2s%5s%15s%5s%11s%5s%11s%5s%14s%9s\n", "Full Weeks Worked", " ", "0" ," ", "1" ," ", "2" , " ", "3" ," ", "4 or more");
		
		//local variable declaration
		int j = 0;
		int i = 0;
		
		//for loop to print out all array bonus amounts
		for (int x = 0; x < 7; x++) {
			for (i = 0; i < 5; i++) {
				System.out.format("%5s%13s%7s\t", j , " " , bonusamounts[x][i++]);
				System.out.format("%3s%10s\t" , " " , bonusamounts[x][i++]);
				System.out.format("%3s%10s\t" , " " , bonusamounts[x][i++]);
				System.out.format("%3s%10s\t" , " " , bonusamounts[x][i++]);
				System.out.format("%3s%10s\t\n" , " " , bonusamounts[x][i++]);
				j++;
			}
		}
		
		//setting j to zero just in case they want the summary printed out again
		j = 0;
		
		//printing of the total
		System.out.println("Total: " + ctotal);
		
		//printing of the average
		System.out.println("Average: " + caverage);
		
		//re-asking for a choice
		choice();
	}
	
	public static void choice() {
		
		int ichoice = 0;
		boolean invalid = true;
		//this is used to bring out the string varible so i can transfer it to a integer
		String iString;
		
		while (invalid == true) {
			try {
				System.out.println("What option would you like? Data Entry (1), Summary Display (2), or TERMINATE (3)? "); 
				iString = myScanner.next().toUpperCase();
				ichoice = Integer.parseInt(iString);
				} 
			catch (Exception e){
				System.out.println ("Invalid Input. Will only accept 1 (data entry) or 2 (summary display)");
				invalid = true;
					}
	   //case to validate the choice of either data entry, summary entry, and the terminate 
	   switch (ichoice) {
	   	case 1:
	   		inputdata();
	   		invalid = false;
	   		break;
	   	case 2:
	   		inputsummary();
	   		invalid = false;
	   		break;
	   	case 3:
	   		System.out.println("PROGRAM TERMINATED");
	   		System.out.println("Thank You");
	   		System.exit(1);
		   	break;
	   	default:
	   		System.out.println("Invalid input. Must be either 1 (data entry) or 2 (summary display)");
	   		invalid = true;
	   }

		}
	}
}
