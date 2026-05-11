package domain.studentsGroup;

import java.util.UUID;

public class StudentsGroup {
	private UUID id;
	private String specialization;
	private String groupCode;
	private String description;
	//TODO: add collection of students in this group
	
	public StudentsGroup(String specialization, String groupCode, String description) {
		this.id = UUID.randomUUID(); 
		this.specialization = specialization;
		this.groupCode = groupCode;
		this.description = description;
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
