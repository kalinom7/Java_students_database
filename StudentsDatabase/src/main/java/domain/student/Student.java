package domain.student;

import java.util.UUID;

/*
 * Represents a student in the system.
 *
 * The class stores basic student data.
 *
 * The identifier is generated automatically when a new Student object is created.
 */

public class Student {
	private UUID id;
	private String name;
	private String surname;
	private String albumNumber;
	
	/*
	 * Creates a new Student object.
	 *
	 * Parameters:
	 * name - student's first name,
	 * surname - student's surname,
	 * albumNumber - student's album number.
	 *
	 * The constructor generates a random UUID and assigns all provided data
	 * to the new student object.
	 */
	public Student(String name, String surname, String albumNumber) {
		
		this.id = UUID.randomUUID();
		this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
	}
	
	/*
	 * SETTERS
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
		
	public void setAlbumNumber(String albumNumber) {
		this.albumNumber = albumNumber;
	}
	
	/*
	 * GETTERS
	 */
	
	public UUID getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAlbumNumber() {
		return albumNumber;
	}
		
}
