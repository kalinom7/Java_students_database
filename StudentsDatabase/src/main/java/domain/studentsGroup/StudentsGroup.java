package domain.studentsGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import domain.student.Student;

public class StudentsGroup {
	private UUID id;
	private String specialization;
	private String groupCode;
	private String description;
	private Set<UUID> studentsInGroup;
	
	
	public Set<UUID> getStudentsInGroup() {
		return studentsInGroup;
	}

	public StudentsGroup(String specialization, String groupCode, String description) {
		this.id = UUID.randomUUID(); 
		this.specialization = specialization;
		this.groupCode = groupCode;
		this.description = description;
		this.studentsInGroup = new HashSet<>();
	}
	
	public UUID getId() {
		return id;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
