package abc;

/** CourseDBManager class
 * 
 * @author Jonas da Silva
 *
 */

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface{
	
	public CourseDBStructure dbStructure = new CourseDBStructure(30);

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement cde = new CourseDBElement(id, crn, credits, roomNum, instructor);
		dbStructure.add(cde);
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return dbStructure.get(crn);
		}catch(IOException e) {
			e.getMessage();
		}
		return null;
	}

	
	@Override
	public void readFile(File input) throws FileNotFoundException {
		try {
			Scanner sc = new Scanner(input);
			boolean isValid = true;
			while(sc.hasNext()) {
				String id = sc.next();
				if(id.indexOf("#") == 0) {	
					sc.nextLine();
					continue;
				}
				if(id.indexOf("CMSC") != 0) 
					isValid = false;
				int crn = sc.nextInt();
				int credits = sc.nextInt();
				String roomNum = sc.next();
				String instructor = sc.nextLine();
				if(isValid) {		
					add(id, crn, credits, roomNum, instructor);
					}
				else{
					try {
						writeToInvalid(id, crn, credits, roomNum, instructor);
						break;
					}catch(IOException e) {
						e.getMessage();
						}
					}				
				}	
			sc.close();
		}catch(FileNotFoundException e) {
			e.getMessage();
		}catch(InputMismatchException e) {
			e.getMessage();
		}
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> arrayList = new ArrayList<String>();
		LinkedList<CourseDBElement> linkedList = new LinkedList<CourseDBElement>();
		LinkedList<CourseDBElement> linkedListCompare = new LinkedList<CourseDBElement>();
		CourseDBElement cde = new CourseDBElement("", 0, 0, "", "");
		CourseDBElement cdeCompare = new CourseDBElement("", 0, 0, "", "");
		for(int i = 0; i < dbStructure.hashTable.length; i++) {
			linkedList = dbStructure.hashTable[i];
			while(linkedList != null) {
				for(int j = 0; j < linkedList.size(); j++) {
					cde = linkedList.get(j);
					arrayList.add(cde.toString());						
				}
				break;
				}
		}
		Collections.sort(arrayList);
		return arrayList;
	}
	
	
	public void writeToInvalid(String id, int crn, int credits, String roomNum, String instructor) throws IOException {
		File invalidFile = new File("invalid_entries.txt");
		Writer wr = new FileWriter(invalidFile);
		wr.write(id + " " + crn + " " + credits + " " + roomNum + " " + instructor + "\n");	
		wr.close();
	}
	
	
	
	
	
}
