package abc;
import java.util.*;

/** CourseDBStructure class
 * 
 * @author Jonas da Silva
 *
 */

import java.io.IOException;

public class CourseDBStructure implements CourseDBStructureInterface{
	
	private int size;
	protected LinkedList<CourseDBElement> hashTable[];
	
	public CourseDBStructure(int size) {
        this.size = size;
        hashTable = new LinkedList[size];
	}
	
	public CourseDBStructure(String s, int size) {
		this.size = size;
		hashTable = new LinkedList[size];
	}
	
	
	

	@Override
	public void add(CourseDBElement element) {
		for(int i = element.hashCode() % size; hashTable[i] == null;) {
			hashTable[i] = new LinkedList<CourseDBElement>();
			break;
		}
		hashTable[element.hashCode() % size].add(element);
	}
	
	

	@Override
	public CourseDBElement get(int crn) throws IOException {
		if(hashTable[Integer.toString(crn).hashCode() % size] == null) 
			throw new IOException();
		for(int i = 0; i < size; i++) {
			CourseDBElement cde = hashTable[Integer.toString(crn).hashCode() % size].get(i);
			if (cde.getCRN() == crn)
				return cde;
		}
		return null;
	}
	

	@Override
	public int getTableSize() {
		return size;
	}
	

}
