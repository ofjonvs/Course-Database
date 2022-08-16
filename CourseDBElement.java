package abc;

/** CourseDB class
 * 
 * @author Jonas da Silva
 *
 */

public class CourseDBElement implements Comparable{
	
	private String courseID;
	private int crn;
	private int numOfCredits;
	private String roomNum;
	private String instructor;
	
	public CourseDBElement() {
		courseID = "";
		crn = 0;
		numOfCredits = 0;
		roomNum = "";
		instructor = "";
	}
	
	public CourseDBElement(String courseID, int crn, int numOfCredits, String roomNum, String instructor) {
		this.courseID = courseID;
		this.crn = crn;
		this.numOfCredits = numOfCredits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	public int getCRN() {
		return crn;
	}

	@Override
	public int compareTo(Object o) {
		return crn - (Integer)o;
	}

	public void setCRN(int parseInt) {
		crn = parseInt;
	}
	
	 public int hashCode() {
         String str = crn + "";
         return str.hashCode();
 }
	 

	 public String toString() {
		 String str = "\nCourse:" + courseID + " CRN:" + crn + " Credits:" + numOfCredits + " Instructor:" + instructor + " Room:" + roomNum;
	     return str;
	    }
	 


	
	
}
