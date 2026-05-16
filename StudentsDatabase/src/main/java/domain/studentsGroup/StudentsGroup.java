package domain.studentsGroup;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/*
 * Represents a group of students in the system.
 *
 * The class stores basic group information and
 * a set of student identifiers assigned to the group.
 *
 * The identifier is generated automatically when a new StudentsGroup object is created.
 * 
 */

public class StudentsGroup {
	private UUID id;
	private String specialization;
	private String groupCode;
	private String description;
	private Set<UUID> studentsInGroup;
	
	
	/**
	 * Creates a new StudentsGroup object.
	 *
	 * @param specialization group's specialization
	 * @param groupCode group's code
	 * @param description group's description
	 */
	public StudentsGroup(String specialization, String groupCode, String description) {
		this.id = UUID.randomUUID(); 
		this.specialization = specialization;
		this.groupCode = groupCode;
		this.description = description;
		this.studentsInGroup = new HashSet<>();
	}
	
	
	/*
	 * GETTERS
	 */
	public Set<UUID> getStudentsInGroup() {
		return studentsInGroup;
	}
	public UUID getId() {
		return id;
	}
	public String getSpecialization() {
		return specialization;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public String getDescription() {
		return description;
	}
	
	/*
	 * SETTERS
	 */
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
