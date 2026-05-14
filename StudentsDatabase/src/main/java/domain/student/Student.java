package domain.student;

import java.util.UUID;

public class Student {
	private UUID id;
	private String name;
	private String surname;
	private String albumNumber;
	
	public Student(String name, String surname, String albumNumber) {
		this.id = UUID.randomUUID();
		this.name = name;
        this.surname = surname;
        this.albumNumber = albumNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAlbumNumber(String albumNumber) {
		this.albumNumber = albumNumber;
	}
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
