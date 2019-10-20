
public class CourseTester1 {

	public static void main(String[] args) {
		
		Course course = new Course("Media Studies", 5, 5);
		System.out.println("hi leah");

		System.out.println(course);
		
		// array created only for the purposes of this tester file
		Student[] studentsInSchool = new Student[15];
		studentsInSchool[0] = new Student("Adam Ant", "S925", true);
		studentsInSchool[1] = new Student("Bob Barker", "S713", false);
		studentsInSchool[2] = new Student("Chevy Chase", "S512", true);
		studentsInSchool[3] = new Student("Doris Day", "S513", true);
		studentsInSchool[4] = new Student("Emilio Estevez", "S516", true);
		studentsInSchool[5] = new Student("Farrah Fawcet", "S956", true);
		studentsInSchool[6] = new Student("Greta Garbo", "S419", true);
		studentsInSchool[7] = new Student("Helen Hunt", "S281", true);
		studentsInSchool[8] = new Student("Jack Johnson", "S790", true);
		studentsInSchool[9] = new Student("Kim Kardashian", "S336", true);		
		studentsInSchool[10] = new Student("Martina McBride", "S156", true);	
		studentsInSchool[11] = new Student("Renne Russo", "S219", true);	
		studentsInSchool[12] = new Student("Susan Serandon", "S472", true);	
		studentsInSchool[13] = new Student("Vince Vaughn", "S892", true);	
		studentsInSchool[14] = new Student("Walt Whitman", "S901", true);
		
		course.addStudent(studentsInSchool[0]);
		course.addStudent(studentsInSchool[1]);
		course.addStudent(studentsInSchool[2]);
		course.addStudent(studentsInSchool[3]);
		course.addStudent(studentsInSchool[4]);
		course.addStudent(studentsInSchool[5]);
		course.addStudent(studentsInSchool[6]);
		course.addStudent(studentsInSchool[7]);
		course.addStudent(studentsInSchool[8]);
		course.addStudent(studentsInSchool[9]);
		course.addStudent(studentsInSchool[10]);
		course.addStudent(studentsInSchool[11]);
		course.addStudent(studentsInSchool[12]);
		
		course.dropStudent(studentsInSchool[0]);

		
		System.out.println(course.numberOfStudentsEnrolled());
		System.out.println(course.getRosterArray());

		System.out.println(course);

		
	}
}
	
