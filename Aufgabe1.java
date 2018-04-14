import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aufgabe1 {

	public static void printSeperationLine() {
		System.out.println("=======================================");
	}

	public static Student createStudent(Course course) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Vorname: ");
		String firstName = sc.nextLine();
		System.out.println("Nachname: ");
		String lastName = sc.nextLine();
		System.out.println("Matrikelnummer: ");
		int matrikelNummer = sc.nextInt();
		sc.nextLine();
		System.out.println("E-Mail: ");
		String eMail = sc.nextLine();
		Student student = new Student(firstName, lastName, matrikelNummer, eMail);
		student.setCourse(course);
		return student;
	}

	public static void main(String[] args) {
		// Initialisierung Anfang
		College oth = new College();
		Course winfo = new Course("Wirtschaftsinformatik");
		Course info = new Course("Informatik");
		Course bwl = new Course("Betriebswirtschaftslehre");
		Class pg1 = new Class("Programmieren 1", 8, 2);
		Class pg2 = new Class("Programmieren 2", 8, 2);
		Class bwl1 = new Class("BWL 1", 5, 1);
		Class bwl2 = new Class("BWL 2", 7, 1);
		Class mathe = new Class("Mathematik", 7, 2);
		winfo.addClass(pg1);
		winfo.addClass(pg2);
		winfo.addClass(bwl1);
		winfo.addClass(bwl2);
		winfo.addClass(mathe);
		info.addClass(pg1);
		info.addClass(pg2);
		info.addClass(mathe);
		bwl.addClass(bwl1);
		bwl.addClass(bwl2);
		bwl.addClass(mathe);
		oth.addCourse(winfo);
		oth.addCourse(info);
		oth.addCourse(bwl);
		// Initialisierung Ende

		Scanner mainScanner = new Scanner(System.in);
		boolean endInput = false;
		int input;
		do {
			printSeperationLine();
			System.out.println("Auswahlbildschirm:");
			System.out.println("1.Student anlegen.");
			System.out.println("2.Student bearbeiten.");
			System.out.println("3.Infos eines Studenten ausgeben.");
			System.out.println("4.Infos der Hochschule ausgeben.");
			System.out.println("5.Noten eintragen.");
			System.out.println("0.Programm beenden.");
			input = mainScanner.nextInt();
			endInput = input == 0;
			switch (input) {
			case 1:
				System.out.println("Studiengang auswählen: ");
				int i = 1;
				for (Course current : oth.getCourses()) {
					System.out.println(i++ + " " + current.getName());
				}
				i = mainScanner.nextInt();
				mainScanner.nextLine();
				Student student = createStudent(oth.getCourses().get(i - 1));
				oth.addStudent(student);
				break;
			case 2:
				System.out.println("Student auswählen: ");
				int j = 1;
				for (Student current : oth.getStudents()) {
					System.out.println(j++ + " " + current.getMatrikelNummer());
				}
				j = mainScanner.nextInt();
				mainScanner.nextLine();
				oth.getStudents().get(j - 1).editStudent();
				break;
			case 3:
				System.out.println("Student auswählen: ");
				int k = 1;
				for (Student current : oth.getStudents()) {
					System.out.println(k++ + " " + current.getMatrikelNummer());
				}
				k = mainScanner.nextInt();
				oth.getStudents().get(k - 1).printStudent();
				oth.getStudents().get(k - 1).printAverage();
				break;
			case 4:
				oth.printCollege();
				break;
			case 5:
				System.out.println("Student auswählen: ");
				int m = 1;
				for (Student current : oth.getStudents()) {
					System.out.println(m++ + " " + current.getMatrikelNummer());
				}
				m = mainScanner.nextInt();
				mainScanner.nextLine();
				oth.getStudents().get(m - 1).setGrades();
				break;
			}

		} while (!endInput);

	}

}

class Student {
	private String firstName;
	private String lastName;
	private Course course;
	private int matrikelNummer;
	private String eMail;

	public Student(String firstName, String lastName, int matrikelNummer, String eMail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.matrikelNummer = matrikelNummer;
		this.eMail = eMail;
	}

	public float averageGrade() {
		float average = 0;
		float sum = 0;
		float creditsWithFactor = 0;
		for (Class current : course.getClasses()) {
			sum += (current.getNote() * current.getCredits() * current.getMultiplikator());
			creditsWithFactor += (current.getCredits() * current.getMultiplikator());
		}
		average = sum / creditsWithFactor;
		return average;
	}

	public void printAverage() {
		System.out.println("Durchschnittsnote: " + averageGrade());
	}

	public void editStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Neuer Vorname: ");
		setFirstName(sc.nextLine());
		System.out.println("Neuer Nachname: ");
		setLastName(sc.nextLine());
		System.out.println("Neue Matrikelnummer: ");
		setMatrikelNummer(sc.nextInt());
		sc.nextLine();
		System.out.println("Neue E-Mail: ");
		seteMail(sc.nextLine());

	}

	public void printStudent() {
		printSeperationLine();
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Matrikelnummer: " + matrikelNummer);
		System.out.println("Studiengang: " + course.getName());
		System.out.println("E-Mail: " + eMail);
		printSeperationLine();
	}

	public void setGrades() {
		Scanner sc = new Scanner(System.in);
		for (Class classe : course.getClasses()) {
			System.out.println("Note für " + classe.getName() + " eintragen: ");
			classe.setNote(sc.nextFloat());
		}

	}

	public void printSeperationLine() {
		System.out.println("=======================================");
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMatrikelNummer() {
		return matrikelNummer;
	}

	public void setMatrikelNummer(int matrikelNummer) {
		this.matrikelNummer = matrikelNummer;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}

class College {
	private List<Student> students = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();
	private String name = "OTH Regensburg";

	public void printCollege() {
		System.out.println("Studiengänge der Hochschule: ");
		for (Course currentCourse : courses) {
			System.out.println(currentCourse.getName());
		}
		printSeperationLine();
		for (Student currentStudent : students) {
			currentStudent.printStudent();
			for (Class currentClass : currentStudent.getCourse().getClasses()) {
				currentClass.printClass();
				printSeperationLine();
			}
			currentStudent.printAverage();
			printSeperationLine();
		}
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printSeperationLine() {
		System.out.println("=======================================");
	}

}

class Course {
	private List<Class> classes = new ArrayList<>();
	private String name;

	public Course(String name) {
		setName(name);
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void addClass(Class classe) {
		classes.add(classe);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Class {
	private String name;
	private int credits;
	private float note = 0;
	private int multiplikator;

	public Class(String name, int credits, int multiplikator) {
		this.name = name;
		this.credits = credits;
		this.multiplikator = multiplikator;

	}

	public void printClass() {
		System.out.println("Kursname: " + name);
		System.out.println("Credits: " + credits);
		System.out.println("Note: " + note);
		System.out.println("Multiplikator: " + multiplikator);
	}

	public void printSeperationLine() {
		System.out.println("=======================================");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public int getMultiplikator() {
		return multiplikator;
	}

	public void setMultiplikator(int multiplikator) {
		this.multiplikator = multiplikator;
	}

}