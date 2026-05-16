package domain.studentsGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * In-memory implementation of StudentsGroupRepository.
 *
 * Stores students group objects in a HashMap using UUID
 * as a unique key.
 */

public class InMemoStudentsGroupRepository implements StudentsGroupRepository{
	private Map<UUID, StudentsGroup> studentsGroups = new HashMap<>();
	
	/**
	 * Saves students group object in memory.
	 *
	 * @param id group's unique identifier
	 * @param studentsGroup students group object to save
	 */
	public void save(UUID id, StudentsGroup studentsGroup) {
		studentsGroups.put(id, studentsGroup);
	}
	
	/**
	 * Returns students group object from memory.
	 *
	 * @param id group's unique identifier
	 * @return students group object
	 */
	public StudentsGroup get(UUID id) {
		StudentsGroup studentsGroup = studentsGroups.get(id);
		
		return studentsGroup;
	}
	
	/**
	 * Deletes students group object from memory.
	 *
	 * @param id group's unique identifier
	 */
	public void delete(UUID id) {
		studentsGroups.remove(id);
	}
}
