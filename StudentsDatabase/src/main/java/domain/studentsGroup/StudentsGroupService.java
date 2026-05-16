package domain.studentsGroup;

import java.util.UUID;
import language.LanguageManager;

/*
 * Service responsible for students group management.
 *
 * Handles group creation, validation, editing,
 * retrieving, deleting and assigning students to groups.
 * 
 */

public class StudentsGroupService {
	private StudentsGroupRepository studentsGroupRepository;

	/**
	 * Creates StudentsGroupService object and assigns repository.
	 *
	 * @param repository repository used for storing students group data
	 */
	public StudentsGroupService(StudentsGroupRepository repository) {
		this.studentsGroupRepository = repository;
	}

	/**
	 * Creates a new students group object.
	 *
	 * The method validates input data, creates a group object and saves it in the
	 * repository.
	 *
	 * @param specialization group's specialization
	 * @param groupCode      group's code
	 * @param description    group's description
	 * @return created students group object
	 * @throws IllegalArgumentException when provided data is invalid
	 */
	public StudentsGroup create(String specialization, String groupCode, String description) {

		// Validate empty or blank input values
		if (specialization.trim().isEmpty() || specialization.isBlank() || groupCode.trim().isEmpty()
				|| groupCode.isBlank() || description.trim().isEmpty() || description.isBlank()) {
			throw new IllegalArgumentException(LanguageManager.get("invalid.group.create.data"));
		}

		StudentsGroup studentsGroup = new StudentsGroup(specialization, groupCode, description);
		UUID id = studentsGroup.getId();

		studentsGroupRepository.save(id, studentsGroup);
		return studentsGroup;
	}

	/**
	 * Returns students group object based on provided ID.
	 *
	 * @param id group's unique identifier
	 * @return students group object
	 * @throws Exception when group does not exist
	 */
	public StudentsGroup get(UUID id) {
		StudentsGroup studentsGroup = studentsGroupRepository.get(id);

		// Validate that group exists
		if (studentsGroup == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.group.notFound"));
		}

		return studentsGroup;
	}
	
	/**
	 * Adds student to selected group.
	 *
	 * The method finds group by ID and adds student's ID to the set of students
	 * assigned to this group.
	 *
	 * @param groupId   group's unique identifier
	 * @param studentId student's unique identifier
	 * @return updated students group object
	 * @throws IllegalArgumentException when group does not exist or student ID is
	 *                                  invalid
	 */
	public StudentsGroup addStudent(UUID groupId, UUID studentId) {
		StudentsGroup studentsGroup = studentsGroupRepository.get(groupId);

		// Validate that group exists
		if (studentsGroup == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.group.notFound"));
		}

		// Validate that student ID is provided
		if (studentId == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.student.notFound"));
		}
		// Add student ID to the group collection
		studentsGroup.getStudentsInGroup().add(studentId);
		studentsGroupRepository.save(groupId, studentsGroup);
		return studentsGroup;
	}

	/**
	 * Updates existing students group data.
	 *
	 * @param id             group's unique identifier
	 * @param specialization new group specialization
	 * @param groupCode      new group code
	 * @param description    new group description
	 * @return updated students group object
	 * @throws IllegalArgumentException when group does not exist or provided data
	 *                                  is invalid
	 */
	public StudentsGroup edit(UUID id, String specialization, String groupCode, String description) {
		StudentsGroup studentsGroup = studentsGroupRepository.get(id);

		// Validate that group exists
		if (studentsGroup == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.group.notFound"));
		}

		// Validate that input values are not null or blank
		if (specialization == null || groupCode == null || description == null || specialization.trim().isEmpty()
				|| groupCode.trim().isEmpty() || description.trim().isEmpty()) {
			throw new IllegalArgumentException(LanguageManager.get("invalid.group.edit.data"));
		}

		studentsGroup.setDescription(description);
		studentsGroup.setGroupCode(groupCode);
		studentsGroup.setSpecialization(specialization);
		studentsGroupRepository.save(id, studentsGroup);
		return studentsGroup;
	}

	/**
	 * Removes student from selected group.
	 *
	 * @param studentId student's unique identifier
	 * @param groupId   group's unique identifier
	 * @throws Exception when student is not assigned to the group
	 */
	public void removeStudent(UUID studentId, UUID groupId) {
		StudentsGroup studentsGroup = studentsGroupRepository.get(groupId);

		// Validate that group exists
		if (studentsGroup == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.group.notFound"));
		}

		// Validate that student is assigned to the group
		if (!studentsGroup.getStudentsInGroup().remove(studentId)) {
			throw new IllegalArgumentException(LanguageManager.get("error.studentRemove.in.group.not.found"));
		}
		studentsGroupRepository.save(groupId, studentsGroup);
	}

	/**
	 * Deletes students group object from repository.
	 *
	 * @param id group's unique identifier
	 */
	public void delete(UUID id) {
		studentsGroupRepository.delete(id);
	}

}
