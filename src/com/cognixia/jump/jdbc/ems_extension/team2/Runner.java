package com.cognixia.jump.jdbc.ems_extension.team2;

import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.jdbc.ems_extension.team2.concrete.CompanyDAOClass;
import com.cognixia.jump.jdbc.ems_extension.team2.model.Company;

public class Runner {
	
	//runner main
	public static void main(String[] args) {
		//this creates the needed files for everything to work
		Scanner scanner = new Scanner(System.in);
		//boolean keepProgramRunning = true;
		int companyId;
		
		//first method that polls the database for any existing companies.
		companyId = startUp(scanner);
		
		//holds the main logic
		//mainLoop(scanner, fileHandler, company, keepProgramRunning);
		
		//final set of logic before the program terminates
		//shutDown(scanner, fileHandler, company);
		
		//closing the scanner for security and safety.
		System.out.println("... Program Terminated");
		System.out.println(companyId);
		scanner.close();
	}
	
	private static int startUp(Scanner scanner) {
		System.out.println("...  Loading database connection");
		CompanyDAOClass companyDAO = new CompanyDAOClass();
		List<Company> companies = companyDAO.getAllCompanies();
		
		//check database for any existing companies
		if (companies.isEmpty()) {
			//if no companies, creates one with user
			System.out.println("... No created companies were detected");
			System.out.println("... Creating company from user input:");
			
			////reuses method for creating company
			//Company tempCompany = createCompany(scanner);
			//companyDAO.createCompany(tempCompany);
			//return tempCompany.getId();
			System.out.println("Nice");
			return 0;
		} else {
			//if one exists, ask user what company they'd like to work with
			System.out.println("... Found " + companies.size() + " Companies: ");
			for (Company company : companies ) {
				System.out.printf("ID: %-2d - Name: %s%n", company.getId(), company.getName());
			}
			System.out.print("ID of company you'd like to use: ");
			return scanner.nextInt();
		}
	}
}
