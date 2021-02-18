package com.cognixia.jump.jdbc.ems_extension.team2;

/**
 * Runner class that handles starting the project up.
 * @author Alexandre Northouse
 * @author Alex Donaldson
 * @author Mae Saguil
 * @author Raymund Palafox
 */
import java.util.Scanner;

import com.cognixia.jump.jdbc.ems_extension.team2.concrete.CompanyDAOClass;
import com.cognixia.jump.jdbc.ems_extension.team2.concrete.CompanyManager;
import com.cognixia.jump.jdbc.ems_extension.team2.concrete.DepartmentDAOClass;
import com.cognixia.jump.jdbc.ems_extension.team2.concrete.EmployeeDAOClass;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Department;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Employee;

import java.util.List;

public class EMSRunner {
	
	//runner main
	public static void main(String[] args) {
		//this creates the needed files for everything to work
		Scanner scanner = new Scanner(System.in);
		boolean keepProgramRunning = true;
		int companyId;
		
		//first method that polls the database for any existing companies.
		companyId = startUp(scanner);
		
		//holds the main logic
		//mainLoop(scanner, fileHandler, company, keepProgramRunning);
		
		//final set of logic before the program terminates
		//shutDown(scanner, fileHandler, company);
		
		//closing the scanner for security and safety.
		System.out.println("Program Terminated.");
		scanner.close();
	}
	
	
	//main methods
	/**
	 * This forces the user to either create a company or load one from a file.
	 * @param scanner
	 * @param fileHandler
	 * @param company
	 */
	private static int startUp(Scanner scanner) {
		System.out.println("...  Loading database connection");
		CompanyDAOClass companyDAO = new CompanyDAOClass();
		List<Company> companies = companyDAO.getAllCompanies();
		
		//check database for any existing companies
		if (companies.isEmpty()) {
			//if no companies, creates one with user
			System.out.println("... No created companies were detected");
			System.out.println("... Creating company from user input:");
			
//			reuses method for creating company
//			Company tempCompany = createCompany(scanner);
//			companyDAO.createCompany(tempCompany);
//			return tempCompany.getId();
			System.out.println("Nice");
			return 0;
		} else {
			//if one exists, ask user what company they'd like to work with
			System.out.println("... Found " + companies.size() + " Companies: ");
			for (Company company : companies ) {
				System.out.printf("ID: %-2d - Name: %s%n");
			}
			System.out.print("ID of company you'd like to use: ");
			return scanner.nextInt();
		}
	}
	
	
	/**
	 * This runs the main logic of the system, in a do-while loop.
	 * @param scanner
	 * @param fileHandler
	 * @param company
	 * @param keepProgramRunning
	 */
	private static void mainLoop(Scanner scanner, FileHandler fileHandler, CompanyManager company, boolean keepProgramRunning) {
		//while loop that keeps running till the user tells it to stop.
		do {
			System.out.println("\n= = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
			System.out.println("Which option would you like to choose?");
			System.out.println("1: Employee\n" + "2: Department\n" + "3: Company\n"
								+ "4: Open Saved Info\n" + "5: Save Current Info\n" + "6: Terminate Program");
			int userInput = Integer.parseInt(scanner.nextLine());
			System.out.println();
			
			switch (userInput) {
				//===Employee Options:
				case 1:
					System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
					employeeOptions(scanner, company, fileHandler);
					break;
				
				//===Department Options:
				case 2:
					System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
					departmentOptions(scanner, company, fileHandler);
					break;
					
				//===Company Options:
				case 3:
					System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
					companyOptions(scanner, company, fileHandler);
					break;
					
				//===File Options:
				case 4:
					System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
					company = openSavedInfo(scanner, company, fileHandler);
					break;
				case 5:
					System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
					saveCurrentInfo(scanner, company, fileHandler);
					break;
					
				//===Quitting:
				case 6:
					System.out.println("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
					System.out.println("Terminating Program...\n");
					keepProgramRunning = false;
					break;
					
				//===Default Option:
				default:
					System.out.println("Unrecognized input, restarting options...\n");
					break;
			}
		} while (keepProgramRunning);
	}
	
	
	/**
	 * This does a final ask if the user wants to save their work work before ending the program.
	 * @param scanner
	 * @param fileHandler
	 * @param company
	 */
	private static void shutDown(Scanner scanner, FileHandler fileHandler, CompanyManager company) {
		System.out.println("Would you like to save your current data and "
				+ "overwrite any existing data before closing the program? [y/n]");
		String userInput = scanner.nextLine().toLowerCase();
		
		if (userInput.equals("y")) {
			System.out.println("Saving current data...");
			fileHandler.writeToFile(company);
			System.out.println("Successfully saved current data!");
		} else if (userInput.equals("n")) {
			System.out.println("Chose to NOT save data.");
		} else {
			System.out.println("Unrecognized input, retrying operation..");
			shutDown(scanner, fileHandler, company);
		}
	}
	
	
	
	//secondary methods
	/***
	 * Handles the options for interacting with the Employee class.
	 * @param scanner
	 * @param company
	 * @param fileHandler
	 */
	private static void employeeOptions(Scanner scanner, CompanyManager company, FileHandler fileHandler) {
		//temp variables:
		Employee tempEmployee = null;
		
		
		System.out.println("Select from the following Employee options:");
		System.out.println("1: List a Current Employee's Info\n" + "2: Update a Current Employee's Info\n" + 
							"3: Create a New Employee\n" + "4: Remove a Current Employee");
		int userInput = Integer.parseInt(scanner.nextLine());
		int employeeId, departmentId;
		System.out.println();
		
		switch(userInput) {
		
		
			//gets info on an employee
			case 1:
				//asks for employee ID and department ID
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("What is employee's ID?");
				employeeId = Integer.parseInt(scanner.nextLine());
				System.out.println("What their department's ID?");
				departmentId = Integer.parseInt(scanner.nextLine());
				
				//prints out found info
				tempEmployee = company.getEmployee(employeeId, departmentId);
				
				if (tempEmployee != null) {
					System.out.println("Employee Found, printing info:\n"
							+ tempEmployee.toString());
				} else {
					System.out.println("Employee not found.");
				}
				break;
			
			
			//updates an employee's info
			case 2:
				//asks for employee ID and department ID
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("What is the employee's ID?");
				employeeId = Integer.parseInt(scanner.nextLine());
				System.out.println("What is their department's ID?");
				departmentId = Integer.parseInt(scanner.nextLine());
				tempEmployee = company.getEmployee(employeeId, departmentId);
				
				//prints out found info then asks for needed info
				if (tempEmployee != null) {
					//prints current
					System.out.println("Employee Found, printing current info:\n"
							+ tempEmployee.toString());
					
					//collects replacement info
					System.out.println("Please fill out the following fields to update the Employee:");
					System.out.println("--What is the employee's first name?");
					String firstName = scanner.nextLine();
					System.out.println("--What is the employee's last name?");
					String lastName = scanner.nextLine();
					System.out.println("--What is the employee's salary?");
					int salary = Integer.parseInt(scanner.nextLine());
					System.out.println("--What is the employee's title?");
					String title = scanner.nextLine();
					System.out.println("--What is the employee's phone number?");
					String phoneNumber = scanner.nextLine();
					System.out.println("--What is the department ID that the employee belongs to?");
					departmentId = Integer.parseInt(scanner.nextLine());
					
					//attempts update
					company.editEmployee(employeeId, departmentId, firstName, lastName, salary, title, phoneNumber);
					System.out.println("Employee updated successfully!");
				} else {
					System.out.println("Employee not found.");
				}
				break;
			
			
			//adds an employee
			case 3:
				//ask for inputs on everything.
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("Creating an Employee...");
				System.out.println("--What is the employee's first name?");
				String firstName = scanner.nextLine();
				System.out.println("--What is the employee's last name?");
				String lastName = scanner.nextLine();
				System.out.println("--What is the employee's salary?");
				int salary = Integer.parseInt(scanner.nextLine());
				System.out.println("--What is the employee's title?");
				String title = scanner.nextLine();
				System.out.println("--What is the employee's phone number?");
				String phoneNumber = scanner.nextLine();
				System.out.println("--What is the department ID that the employee belongs to?");
				departmentId = Integer.parseInt(scanner.nextLine());
				
				//gets newly generated ID if the employee was successfully added
				int newID = company.addEmployee(departmentId, firstName, lastName, salary, title, phoneNumber);
				
				//if-else statement on the received int.
				if (newID == 0) {
					System.out.println("Unable to create an employee in that department.");
				} else {
					System.out.println("Employee created, their ID is: " + newID);
				}
				break;
			
			
			//removing an employee
			case 4:
				//asks for employee ID and department ID
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("What is the employee's ID?");
				employeeId = Integer.parseInt(scanner.nextLine());
				System.out.println("What is their department's ID?");
				departmentId = Integer.parseInt(scanner.nextLine());

				//prints out the results of removal process
				if (company.removeEmployee(employeeId, departmentId)) {
					System.out.println("Employee removed.");
				} else {
					System.out.println("There was an issue removing the employee.");
				}
				break;
			
			
			//handling a bad input
			default:
				System.out.println("The input wasn't recognized.");
				break;
		}
	}
	
	/**
	 * Handles the options for interacting with the Department class.
	 * @param scanner
	 * @param company
	 * @param fileHandler
	 */
	private static void departmentOptions(Scanner scanner, CompanyManager company, FileHandler fileHandler) {
		//temp variables:
		Department tempDepartment = null;
		
		
		System.out.println("Select from the following Department options:");
		System.out.println("1: List a Department's Info & Assigned Employees\n"
							+ "2: Update a Department\n" +  "3: Create a Department\n"
							+ "4: Remove a Department");
		int userInput = Integer.parseInt(scanner.nextLine());
		int departmentId;
		System.out.println();
		
		switch(userInput) {
		
		
			//gets info on a department, and lists its currently assigned employees
			case 1:
				//asks for employee ID and department ID
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("What the Department's ID?");
				departmentId = Integer.parseInt(scanner.nextLine());
				
				//prints out found info
				tempDepartment = company.getDepartment(departmentId);
				
				if (tempDepartment != null) {
					System.out.println("Department Found, printing info:\n"
							+ tempDepartment.listInfo());
					System.out.println(tempDepartment.listEmployees());
				} else {
					System.out.println("Department not found.");
				}
				break;
			
			
			//updates an Department's info
			case 2:
				//asks for department ID
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("What is the Department's ID?");
				departmentId = Integer.parseInt(scanner.nextLine());
				tempDepartment = company.getDepartment(departmentId);
				
				//prints out found info, then asks for needed info
				if (tempDepartment != null) {
					System.out.println("Department Found, printing current info:\n"
							+ tempDepartment.listInfo());
					
					//collecting update info
					System.out.println("Please fill out the following fields to update the Employee:");
					System.out.println("--What is the Department's name?");
					String name = scanner.nextLine();
					System.out.println("--What is the Department's phone number?");
					String phoneNumber = scanner.nextLine();
					System.out.println("--What is the Department's budget?");
					int budget = Integer.parseInt(scanner.nextLine());
					
					//attempts update
					company.editDepartment(departmentId, name, phoneNumber, budget);
					System.out.println("Department info updated successfully!");
				} else {
					System.out.println("Department not found.");
				}
				break;
			
			
			//creates a Department
			case 3:
				//ask for inputs on everything.
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("Creating a Department...");
				System.out.println("--What is the Department's name?");
				String name = scanner.nextLine();
				System.out.println("--What is the Department's phone number?");
				String phoneNumber = scanner.nextLine();
				System.out.println("--What is the Department's budget?");
				int budget = Integer.parseInt(scanner.nextLine());
				
				//gets newly generated ID if the employee was successfully added
				int newID = company.addDepartment(name, phoneNumber, budget);
				
				//if-else statement on the received int.
				if (newID == 0) {
					System.out.println("Unable to create that Department.");
				} else {
					System.out.println("Department created, its ID is: " + newID);
				}
				break;
			
			
			//removes a Department
			case 4:
				//asks for employee ID and department ID
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("What is the Department's ID?");
				departmentId = Integer.parseInt(scanner.nextLine());

				//prints out the results of removal process
				if (company.removeDepartment(departmentId)) {
					System.out.println("Department removed.");
				} else {
					System.out.println("There was an issue removing the Department.");
				}
				break;
			
			
			//handling a bad input
			default:
				System.out.println("The input wasn't recognized.");
				break;
		}
	}
	
	/**
	 * Handles the options for interacting with the Company class.
	 * @param scanner
	 * @param company
	 * @param fileHandler
	 */
	private static void companyOptions(Scanner scanner, CompanyManager company, FileHandler fileHandler) {
		//temp variables:
		Company tempCompany = null;
		
		
		System.out.println("Select from the following Company options:");
		System.out.println("1: List the Company's Info & Active Departments\n"
							+ "2: Update the Company's Info\n" + "3: Reset All Data");
		int userInput = Integer.parseInt(scanner.nextLine());
		System.out.println();
		
		switch(userInput) {
			//gets info on the company, and lists its currently active departments
			case 1:
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				//prints out found info
				tempCompany = company.getCompany();
				System.out.println("Department Found, printing info:\n"
						+ tempCompany.listInfo());
				System.out.println(tempCompany.listDepartments());
				break;
			
			
			//updates an Company's info
			case 2:
				//asks for fields to be updated
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("What is the Department's ID?");
				tempCompany = company.getCompany();
				
				//prints out found info
				if (tempCompany != null) {
					//prints out current info
					System.out.println("Department Found, printing current info:\n"
							+ tempCompany.listInfo());
					
					//collects update info
					System.out.println("Please fill out the following fields to update the Employee:");
					System.out.println("--What is the Company's name?");
					String name = scanner.nextLine();
					System.out.println("--What is the Company's budget?");
					int budget = Integer.parseInt(scanner.nextLine());
					System.out.println("--What is the Company's location?");
					String location = scanner.nextLine();
					
					//attempts update
					company.editCompany(name, budget, location);
					System.out.println("Company info updated successfully!");
				} else {
					System.out.println("Company not found.");
				}
				break;
			
			
			//resets the company data
			case 3:
				//asks confirmation before doing it.
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println("Are you sure you want to reset all the entered data? [y/n]");
				String answer = scanner.next().toLowerCase();

				//prints out the results of removal process
				if (answer.equals("y")) {
					System.out.println("Company reset.");
					company.resetCompany();
				} else {
					System.out.println("There was an issue resetting the company.");
				}
				break;
			
			
			//handling a bad input
			default:
				System.out.println("The input wasn't recognized.");
				break;
		}
	}
	
	/**
	 * Reads data from a file and overwrites the current CompanyManager object
	 * @param scanner
	 * @param company
	 * @param fileHandler
	 * @return loaded CompanyManager data
	 */
	private static CompanyManager openSavedInfo(Scanner scanner, CompanyManager company, FileHandler fileHandler) {
		System.out.println("Wouldy you load saved data and overwrite any unsaved data? [y/n]");
		String userInput = scanner.next().toLowerCase();
		
		if (userInput.equals("y")) {
			System.out.println("Loading data from file...");
			company = (CompanyManager) fileHandler.readFromFile();
			System.out.println("Successfully saved current data!");
			scanner.nextLine();
			return company;
		} else if (userInput.equals("n")) {
			System.out.println("Returning to main menu..");
			scanner.nextLine();
			return company;
		} else {
			System.out.println("Unrecognized input, returning to main menu..");
			scanner.nextLine();
			return company;
		}
	}
	
	/**
	 * Handles saving the current CompanyManager object to a file.
	 * @param scanner
	 * @param company
	 * @param fileHandler
	 */
	private static void saveCurrentInfo(Scanner scanner, CompanyManager company, FileHandler fileHandler) {
		System.out.println("Wouldy you like to save your current data and overwrite any existing data? [y/n]");
		String userInput = scanner.next().toLowerCase();
		
		if (userInput.equals("y")) {
			System.out.println("Saving current data...");
			fileHandler.writeToFile(company);
			System.out.println("Successfully saved current data!");
			scanner.nextLine();
		} else if (userInput.equals("n")) {
			System.out.println("Returning to main menu..");
			scanner.nextLine();
		} else {
			System.out.println("Unrecognized input, returning to main menu..");
			scanner.nextLine();
		}
	}

}
