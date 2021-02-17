package com.cognixia.jump.jdbc.ems_extension.team2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * Object class designed to handle all file interactions-singleton design pattern.
 * @author Alexandre Northouse
 */
public class FileHandler {
	
	//instance variable and file variables
	private static FileHandler instance = null;
	private String directoryFolder = "resources";
	private String objectDataFileName = "objectData";
	private String objectDataFileExtension = "data";
	private String operationHistoryFileName = "log";
	private String operationHistoryFileExtension = "txt";
	
	
	
	//private constructors
	/**
	 * Private constructor that uses the default directory and file names.
	 */
	private FileHandler() {
		super();
	}
	
	
	
	
	//public class methods
	/**
	 * Gets the already created instance, or makes one and returns it.
	 * @return FileHandler Object
	 * @author	Alexandre Northouse
	 */
	public static FileHandler getInstance() {
		if (instance == null) {
			instance = new FileHandler();
		} else {
			System.out.println("Returning existing instance.");
		}
		return instance;
	}
	/**
	 * Writes an object to the specified file directory, name, and type.
	 * @param object Object to be written to the file
	 * @return true on successful write, false otherwise
	 * @author	Alexandre Northouse
	 */
	public boolean writeToFile(Object object) {
		//creates the file objects
		File objectFile = new File(generateFullObjectDataDirectory());
		File logFile = new File(generateFullOperationHistoryDirectory());
		File directory	= new File(generateDirectory());
		
		//checks if the directory exists
		if (!checkDirectory(directory)) {
			return false;
		}
		
		//try-catch block for writing to file
		try(FileOutputStream out = new FileOutputStream(objectFile);
				BufferedOutputStream writer = new BufferedOutputStream(out);
				PrintWriter pw = new PrintWriter(logFile)) {
			
			writer.write(convertToByteArray(object));
			pw.println(getFormattedSystemTime() + 
					"Saved object to data file at: " + generateFullObjectDataDirectory());
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("One of the FileHandler files wasn't found! :<");
			return false;
		} catch (IOException e) {
			System.out.println("There was an IO Error! :<");
			return false;
		}
	}
	/**
	 * Reads an object from the specified file directory, name, and type.
	 * @return generic object if successful, null otherwise
	 * @author	Alexandre Northouse
	 */
	public Object readFromFile() {
		//creates the file objects
		File objectFile	= new File(generateFullObjectDataDirectory());
		File logFile	= new File(generateFullOperationHistoryDirectory());
		File directory	= new File(generateDirectory());
		
		if (!checkDirectory(directory)) {
			return false;
		}
		
		//try-catch block for reading from file
		try(FileInputStream in = new FileInputStream(objectFile);
				BufferedInputStream reader = new BufferedInputStream(in);
				PrintWriter pw = new PrintWriter(logFile)) {
			
			byte[] bites = reader.readAllBytes();
			
			pw.println(getFormattedSystemTime() + 
					"Object successfully loaded from file: " + generateFullObjectDataDirectory());
			return convertToObject(bites);
		} catch (FileNotFoundException e) {
			System.out.println("One of the FileHandler files wasn't found! :<");
			return null;
		} catch (IOException e) {
			System.out.println("There was an IO Error! :<");
			return null;
		}
	}
	/**
	 * Attempts to generate the directory and files inside it.
	 * @return	true if the files were successfully created, false otherwise.
	 * @author	Alexandre Northouse
	 */
	public boolean createFiles() {
		boolean condition1 = checkDirectory(new File(generateDirectory()));
		boolean condition2 = checkFile(new File(generateFullObjectDataDirectory()));
		boolean condition3 = checkFile(new File(generateFullOperationHistoryDirectory()));
		
		if (condition1 && condition2 && condition3) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Returns the full, relative path of the object's data file.
	 * @return	full path of the object file, relative.
	 * @author	Alexandre Northouse
	 */
	public String generateFullObjectDataDirectory() {
		return generateDirectory() + this.objectDataFileName
				+ "." + this.objectDataFileExtension;
	}
	/**
	 * Returns the full, relative path of the log's file.
	 * @return	full path of the log file, relative.
	 * @author	Alexandre Northouse
	 */
	public String generateFullOperationHistoryDirectory() {
		return generateDirectory() + this.operationHistoryFileName
				+ "." + this.operationHistoryFileExtension;
	}
	/**
	 * Returns the full, relative directory path.
	 * @return	full path of the log file, relative.
	 * @author	Alexandre Northouse
	 */
	public String generateDirectory() {
		return this.directoryFolder + "\\";
	}
	
	
	
	
	//private class methods
	/**
	 * Returns a formated system time by adding brackets around the current time in Milliseconds.
	 * @return	Formatted String of the currentTimeMillis().
	 * @author	Alexandre Northouse
	 */
	private String getFormattedSystemTime() {
		return ("[" + System.currentTimeMillis() + "]- ");
	}
	/**
	 * Converts a passed generic object into a byte[] Array.
	 * @param	object	Object to be converted to a byte[].
	 * @return	byte[] conversion of the passed generic object.
	 * @author	Alexandre Northouse
	 */
	private byte[] convertToByteArray(Object object) {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream out = new ObjectOutputStream(bos)){
		  out.writeObject(object);
		  out.flush();
		  return bos.toByteArray();
		  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Converts a passed byte[] Array into a generic object.
	 * @param	biteArary byte[] to be converted to an object.
	 * @return	generic object conversion of the passed byte[].
	 * @author	Alexandre Northouse
	 */
	private Object convertToObject(byte[] biteArray) {
		
		try (ByteArrayInputStream bis = new ByteArrayInputStream(biteArray);
		ObjectInput in = new ObjectInputStream(bis)){
			return in.readObject(); 
		} catch (EOFException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * Checks if the sent file object is a directory, and said directory actually exists.
	 * @param	directory	The directory to be tested.
	 * @return	true if it is a valid directory, or if it can create one; false otherwise.
	 * @author	Alexandre Northouse
	 */
	private boolean checkDirectory(File directory) {
		//checks if the directory exists and is a directory
		if (directory.isDirectory()) {
			return true;
		} else {
			//attempts to create one otherwise
			System.out.println("Ran into a problem opening the directory: " + directory.getAbsolutePath() + "\\");
			System.out.println("Creating the directory now...");
			
			//try-catch block around making the directory
			try {
				directory.mkdirs();
				System.out.println("Directory folder successfully created, returning to task...");
				return true;
			} catch (SecurityException e) {
				System.out.println("Failed to create the directory, please try restarting the program. :<");
				return false;
			}
		}
	}
	/**
	 * Checks if the sent file object actually exists.
	 * @param	file	The file object to be tested.
	 * @return	true if it can find the file, or create it; false otherwise.
	 * @author	Alexandre Northouse
	 */
	private boolean checkFile(File file) {
		//checks if the file exists
		if (file.isDirectory()) {
			return true;
		} else {
			//attempts to create it otherwise
			System.out.println("Ran into a problem finding the file: " + file.getAbsoluteFile() + "\\");
			System.out.println("Creating the file now...");
			
			//try-catch block around making the directory
			try {
				file.createNewFile();
				System.out.println("File successfully created, returning to task...");
				return true;
			} catch (SecurityException | IOException e) {
				System.out.println("Failed to create the file, please try restarting the program. :<");
				return false;
			}
		}
	}
	
	
	
	
	//getters
	/**
	 * @return the directoryFolder
	 */
	public String getDirectoryFolder() {
		return directoryFolder;
	}
	/**
	 * @return the objectDataFileName
	 */
	public String getObjectDataFileName() {
		return objectDataFileName;
	}
	/**
	 * @return the objectDataFileExtension
	 */
	public String getObjectDataFileExtension() {
		return objectDataFileExtension;
	}
	/**
	 * @return the operationHistoryFileName
	 */
	public String getOperationHistoryFileName() {
		return operationHistoryFileName;
	}
	/**
	 * @return the operationHistoryFileExtension
	 */
	public String getOperationHistoryFileExtension() {
		return operationHistoryFileExtension;
	}
	
	//setters
	/**
	 * Attempts to set the directory folder path.
	 * <p>
	 * The method will test the newly given directory path by checking if it exists 
	 * OR if it can create the directory. It will return true if it can do either of
	 * those things and keep the new value, otherwise it will keep the original value
	 * and return false if it can't.
	 * @param	directoryFolder	the directoryFolder to set.
	 * @return	true if successfully changed, false if it can't find or create the directory.
	 * @author	Alexandre Northouse
	 */
	public boolean setDirectoryFolder(String directoryFolder) {
		String originalValue = this.directoryFolder;
		this.directoryFolder = directoryFolder;
		if (checkDirectory(new File(getDirectoryFolder() + "/"))) {
			return true;
		} else {
			this.directoryFolder = originalValue;
			return false;
		}
	}
	/**
	 * Attempts to set the object's data file name.
	 * <p>
	 * The method will test the newly given file name by checking if it already exists 
	 * OR if the object can create a new file with that name. It will keep the new value 
	 * and return true if can do both of those things, otherwise it will keep the original 
	 * value and return false.
	 * @param	objectDataFileName	the new extension for the object's data file.
	 * @return	true if successfully changed, false if it can't find or create the file.
	 * @author	Alexandre Northouse
	 */
	public boolean setObjectDataFileName(String objectDataFileName) {
		String originalValue = this.objectDataFileName;
		this.objectDataFileName = objectDataFileName;
		if (checkFile(new File(generateFullObjectDataDirectory()))) {
			return true;
		} else {
			this.objectDataFileName = originalValue;
			return false;
		}
	}
	/**
	 * Attempts to set the object's data file extension.
	 * <p>
	 * The method will test the newly given file extension by checking if it already exists 
	 * OR if the object can create a new file with that extension. It will keep the new value 
	 * and return true if can do both of those things, otherwise it will keep the original 
	 * value and return false.
	 * @param	objectDataFileExtension	the new extension for the object's data file.
	 * @return	true if successfully changed, false if it can't find or create the file.
	 * @author	Alexandre Northouse
	 */
	public boolean setObjectDataFileExtension(String objectDataFileExtension) {
		String originalValue = this.objectDataFileExtension;
		this.objectDataFileExtension = objectDataFileExtension;
		if (checkFile(new File(generateFullObjectDataDirectory()))) {
			return true;
		} else {
			this.objectDataFileExtension = originalValue;
			return false;
		}
	}
	/**
	 * Attempts to set the log's file name.
	 * <p>
	 * The method will test the newly given file name by checking if it already exists 
	 * OR if the object can create a new file with that name. It will keep the new value 
	 * and return true if can do both of those things, otherwise it will keep the original 
	 * value and return false.
	 * @param	operationHistoryFileName	the new name for the object's data file.
	 * @return	true if successfully changed, false if it can't find or create the file.
	 * @author	Alexandre Northouse
	 */
	public boolean setOperationHistoryFileName(String operationHistoryFileName) {
		String originalValue = this.operationHistoryFileName;
		this.operationHistoryFileName = operationHistoryFileName;
		if (checkFile(new File(generateFullObjectDataDirectory()))) {
			return true;
		} else {
			this.operationHistoryFileName = originalValue;
			return false;
		}
	}
	/**
	 * Attempts to set the log's file extension.
	 * <p>
	 * The method will test the newly given file extension by checking if it already exists 
	 * OR if the object can create a new file with that extension. It will keep the new value 
	 * and return true if can do both of those things, otherwise it will keep the original 
	 * value and return false.
	 * @param	operationHistoryFileExtension	the new extension for the object's data file.
	 * @return	true if successfully changed, false if it can't find or create the file.
	 * @author	Alexandre Northouse
	 */
	public boolean setOperationHistoryFileExtension(String operationHistoryFileExtension) {
		String originalValue = this.operationHistoryFileExtension;
		this.operationHistoryFileExtension = operationHistoryFileExtension;
		if (checkFile(new File(generateFullObjectDataDirectory()))) {
			return true;
		} else {
			this.operationHistoryFileExtension = originalValue;
			return false;
		}
	}
	
	
	
	
	/**
	 * Custom implementation of toString() that returns all values as a concatenated String.
	 * @return	String of all the values concatenated together.
	 * @author	Alexandre Northouse
	 * @see	java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileHandler [directoryFolder=" + directoryFolder + ", objectDataFile=" + objectDataFileName
				+ "." + objectDataFileExtension + ", operationHistoryFile=" + operationHistoryFileName
				+ "." + operationHistoryFileExtension + "]";
	}
}