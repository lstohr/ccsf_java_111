import java.util.*;

public class Course {
	
	private int waitlistMax, rosterMax;
	private String courseName;
	private Student[] rosterArray, waitlistArray;
	
	// Getters
	public Student[] getRosterArray() {
		return rosterArray;
	}
	
	public Student[] getWaitlistArray() {
		return waitlistArray;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public int getWaitlistMax() {
		return waitlistMax;
	}

	public int getRosterMax() {
		return rosterMax;
	}
	
	// Setters
	// We don't want a User to be able to change the Waitlist or Roster 
	// directly - that will be done via the add and drop methods
	
	public void setCourseName(String newCourseName) {
		courseName = newCourseName;
	}
	
	public void setWaitlistMax(int newWaitlistMax) {
		waitlistMax = newWaitlistMax;
	}

	public void setRosterMax(int newRosterMax) {
	   rosterMax = newRosterMax;
	}
	
	// Constructor
	public Course(String title, int rosterCap, int waitlistCap) {
		rosterArray = new Student[rosterCap];
		waitlistArray = new Student[waitlistCap];
		courseName = title;
		waitlistMax = waitlistCap;
		rosterMax = rosterCap;
	}
	
	public String toString() {
		String nameAndEnrollment =  "This course is called: " + courseName + ". There are " + numberOfStudentsEnrolled() + " students enrolled out of " + rosterMax + " total spots.";
		String waitlistInfo = "There are " + numberOfStudentsWaitlisted() + " people on the waitlist out of " + waitlistMax + " total spots.";
		
		String allStudents = "The enrolled students are: \n";
		int i = 1;
		for (Student student : rosterArray) {
			if (student != null) {
			allStudents += (student + "\n");
			}
		}
			
		String waitlistStudents = "The waitlisted students are: \n";
		
		for (Student student : waitlistArray) {
			if (student != null) {
				waitlistStudents += (student + "\n");
			}
		}
		
		return nameAndEnrollment + "\n" + waitlistInfo + "\n" + allStudents + waitlistStudents;
	}
	
	
	
	public boolean addStudent(Student student) {
		
		if (!student.isTuitionPaid()) {
			System.out.println("This student must pay tuition before they can enroll.");
			return false;
			}
		
		if (numberOfStudentsEnrolled() < rosterMax && !isAlreadyEnrolled(student)) {
			for (int i = 0; i < rosterArray.length; i++) {
				if (rosterArray[i] == null) {
					rosterArray[i] = student;
					System.out.println("Added to the course.");
					return true;
				}
			}
		}
	
		else if (numberOfStudentsWaitlisted() < waitlistMax && !isAlreadyOnWaitlist(student)) {
			for (int i = 0; i < waitlistArray.length; i++) {
				if (waitlistArray[i] == null) {
					waitlistArray[i] = student;
					System.out.println("Added to waitlist.");
					return true;
				}
			}
		}
		
		System.out.println("there wasn't enough space");
		return false;
		
	}
	
	public boolean dropStudent(Student student) {
		
		if (!isAlreadyEnrolled(student) && !isAlreadyOnWaitlist(student)) {
			System.out.println("Invalid request - this student is not registered for the course.");
			return false;
			}
		
		if (isAlreadyOnWaitlist(student)) {
			waitlistArray[getIndexInWaitlistArray(student)] = null;
			System.out.println("Dropped.");
			updateWaitlist();
			return true;
		}
		
		if (isAlreadyEnrolled(student)) {
			rosterArray[getIndexInEnrolledArray(student)] = null;
			System.out.println("Dropped.");

			if (numberOfStudentsWaitlisted() > 0) {
				addStudent(waitlistArray[0]);
				waitlistArray[0] = null;
				updateWaitlist();
				}
			
			return true;
		}
		

		
		return true;
		
		}


	private void updateWaitlist() {
		

		
		Student[] reorderedWaitlist = new Student[waitlistMax];

		for (int i = 0; i < waitlistArray.length; i++) {
			if (waitlistArray[i] != null) {
				reorderedWaitlist[indexOfFirstNull(reorderedWaitlist)] = waitlistArray[i];
				}

			}

		waitlistArray = reorderedWaitlist; 
	}
	
	
	private int indexOfFirstNull(Student[] array) {
		
		for (int i = 0; i < array.length; i++) {
			
			if (array[i] == null) {
				return i;
			}
		}
		return 0;
	}
		
	private boolean isAlreadyEnrolled(Student student) {
		
		for (int i = 0; i < rosterArray.length; i++) {
		
			if (rosterArray[i] != null && student.getID() == rosterArray[i].getID()) {
				return true;
			}	
		}
		return false;
	}
	
	private boolean isAlreadyOnWaitlist(Student student) {
		
		for (int i = 0; i < waitlistArray.length; i++) {
		
			if (waitlistArray[i] != null && student.getID() == waitlistArray[i].getID()) {
				return true;
			}	
		}
		return false;
	}
	
	private int getIndexInEnrolledArray(Student student) {
		
		for (int i = 0; i < rosterArray.length; i++) {
		
			if (rosterArray[i] != null && student.getID() == rosterArray[i].getID()) {
				return i;
			}	
		}
		return -1;
	}
	
	private int getIndexInWaitlistArray(Student student) {
		
		for (int i = 0; i < waitlistArray.length; i++) {
		
			if (waitlistArray[i] != null && student.getID() == waitlistArray[i].getID()) {
				return i;
			}	
		}
		return -1;
	}
	

	public int numberOfStudentsEnrolled() {
		int enrolledStudents = 0;
		for (int i = 0; i < rosterArray.length; i++) {
			if (rosterArray[i] != null) {
				enrolledStudents++;
			}	
		}
		return enrolledStudents;
	}
	
	private int numberOfStudentsWaitlisted() {
		int waitlistedStudents = 0;
		for (int i = 0; i < waitlistArray.length; i++) {
			if (waitlistArray[i] != null) {
				waitlistedStudents++;
			}		
		}
		return waitlistedStudents;
	}
	
	public static void main(String[] args) {
		
		Course myFirstCourse = new Course("Bio 101", 10, 5);
		
		Boolean interactWithCourse = true;
		
		System.out.println("Welcome to the loop. You are looking at class: " + myFirstCourse.getCourseName() + ".");
		System.out.println("To see the class enrollment, enter 'view', to add a student enter 'add', \n or to drop a student from the class, enter 'drop'.");
		Scanner scan = new Scanner(System.in);
		String userSelection = scan.nextLine();
		userSelection = userSelection.toLowerCase();
		
	
		System.out.println("To add a student, please enter their first and last names, seprated by a space: ");
		String studentName = scan.nextLine();
		System.out.println("Thanks, now enter their student ID: ");
		String studentID = scan.nextLine();
		System.out.println("And finally, tell us if they've paid their tuition (true/false): ");
		Boolean tuitionPaid = scan.nextBoolean();
		Student newStudentToAdd = new Student(studentName, studentID, tuitionPaid);
		myFirstCourse.addStudent(newStudentToAdd);
		
		System.out.println(myFirstCourse);
		
		System.out.println("To drop a student, please enter their ID: ");
		String idToDrop = scan.nextLine();
		int indexOfStudentToDrop;
/*		if (getIndexInEnrolledArray(idToDrop) >= 0)
		{
			indexOfStudentToDrop = getIndexInEnrolledArray(idToDrop);
		}
		else if (getIndexInWaitlistArray(idToDrop) >= 0)
		{
		
		}*/

	

			
		
		  for (int i = 0; i < myFirstCourse.rosterArray.length; i++) {
		  
		  if (myFirstCourse.rosterArray[i] != null && studentID ==
		  myFirstCourse.rosterArray[i].getID()) {
		  System.out.println(myFirstCourse.rosterArray[i].getName() + " is dropped.");
		  myFirstCourse.dropStudent(myFirstCourse.rosterArray[i]);
		  System.out.println(myFirstCourse);
		  }

		  else {
			  for (int j = 0; j < myFirstCourse.waitlistArray.length; j++) {
				  if (myFirstCourse.waitlistArray[j] != null && studentID ==
						  myFirstCourse.waitlistArray[j].getID()) 
				  
				  {
						  System.out.println(myFirstCourse.waitlistArray[j].getName() + " is dropped.");
						  myFirstCourse.dropStudent(myFirstCourse.waitlistArray[j]);
						  System.out.println(myFirstCourse);
				  }
				  
				  
				  
		  }
			  
			  
		  }
		  }
		  
		  
	/*	  else if }
		  
		  
		  private int getIndexInWaitlistArray(String studentID) {
		  
		  for (int i = 0; i < waitlistArray.length; i++) {
		  
		  if (waitlistArray[i] != null && studentID == waitlistArray[i].getID()) {
		  return i; } } return -1; }*/
		 

	}
	


}
