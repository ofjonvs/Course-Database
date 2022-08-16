package abc;

/** Testing class
 * 
 * @author Jonas da Silva
 *
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBTestJonas {
	
	CourseDBStructure cds, testStructure;

	@Before
	public void setUp() throws Exception {
		cds = new CourseDBStructure(20);
		testStructure = new CourseDBStructure("Testing", 20);
		dataMgr = new CourseDBManager();
	}

	@After
	public void tearDown() throws Exception {
		cds = testStructure = null;
		dataMgr = null;
	}
	
	private CourseDBManagerInterface dataMgr = new CourseDBManager();

	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("CMSC240",24939,4,"ROOM13","Jonas da Silva");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	@Test
	public void testShowAll() {
		dataMgr.add("CMSC240",30504,3,"SC450","Jonas da Silva");
		dataMgr.add("CMSC203",30503,4,"SC450","Prof Thai");
		dataMgr.add("CMSC204",30559,4,"SC450","Prof Thai");
		ArrayList<String> list = dataMgr.showAll();
		
		assertEquals(list.get(0),"\nCourse:CMSC203 CRN:30503 Credits:4 Instructor:Prof Thai Room:SC450");
		assertEquals(list.get(1),"\nCourse:CMSC204 CRN:30559 Credits:4 Instructor:Prof Thai Room:SC450");
		assertEquals(list.get(2),"\nCourse:CMSC240 CRN:30504 Credits:3 Instructor:Jonas da Silva Room:SC450");
			}
	
	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC240 294i5 4 SC450 Jonas da Silva");
			inFile.println("CMSC204 30948 4 SC450 Prof Thai");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			//System.out.println(dataMgr.showAll());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
	
	@Test
	public void testReadInvalid() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC240 294i5 4 SC450 Jonas da Silva");
			inFile.println("CMSC204 30948 4 SC450 Prof Thai");
			inFile.print("203 30504 4 SC540 Dale Higgins");		
			inFile.close();
			dataMgr.readFile(inputFile);
			//System.out.println(dataMgr.showAll());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
	
	

}
