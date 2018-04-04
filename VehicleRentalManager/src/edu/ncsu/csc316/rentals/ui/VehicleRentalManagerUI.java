package edu.ncsu.csc316.rentals.ui;

import java.util.Scanner;

import edu.ncsu.csc316.rentals.manager.VehicleRentalManager;

/**
 * Front-end for the VehicleRentalManager program. Handles all interaction
 * with user, including choosing the file to input, choosing which functionality
 * to run, and outputting the result of the chosen functionality.
 * 
 * @author Noah Benveniste
 */
public class VehicleRentalManagerUI {

	/**
	 * Handles main flow of program; constructs manager object given a
	 * user-specified input file and calls the necessary methods to
	 * generate the output that the user requests.
	 * 
	 * @param args NONE
	 */
	public static void main(String[] args) {
		
		// Stores the file path strings
		String pathToFile = null;
		
		// Scanner for reading input
		Scanner in = new Scanner(System.in);
		
		// Prompt user for the file names
		System.out.print("Enter path to rental cost file: ");
		pathToFile = in.next();
		
		// Initialize the manager
		VehicleRentalManager manager = null;
		boolean filesFound = false;
		
		// Keep re-prompting user until valid files are found
		while (!filesFound) {
			
			try {
				manager = new VehicleRentalManager(pathToFile);
				filesFound = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				
				System.out.print("\nEnter path to rental cost file: ");
				pathToFile = in.next();
			}
			
		}
		
		// Flag to allow the program to run again
		boolean runAgain = true;
		
		// Main program flow control loop
		while (runAgain) {
			
			System.out.println("\nEnter 1 to get the cost-minimized sequence of car rentals between two " +  
							   "days or 2 to list all of the available rentals for a given day: ");
			int selection = in.nextInt();
			
			if (selection == 1) {
				System.out.println("\nEnter the start day: ");
				int start = in.nextInt();
				System.out.println("\nEnter the end day: ");
				int end = in.nextInt();
				System.out.println(manager.getRentals(start, end));
				System.out.println();
			} else if (selection == 2) {
				System.out.println("Enter the day to view available rentals for: ");
				int day = in.nextInt();
				System.out.println(manager.getRentalsForDay(day));
				System.out.println();
			} else {
				System.out.println("\nInvalid input");
			}
			
			System.out.println("\nRun again? (y/n): ");
			runAgain = in.next().toLowerCase().equals("y");
			
		}
		
		// Close input scanner
		in.close();
		
	}

}
