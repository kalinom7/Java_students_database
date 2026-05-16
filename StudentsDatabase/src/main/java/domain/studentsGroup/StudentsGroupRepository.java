package domain.studentsGroup;

import java.util.UUID;

/*
 * Interface responsible for students group data storage.
 *
 * Defines methods for storing, retrieving
 * and removing students group objects.
 * 
 */ 

public interface StudentsGroupRepository {
	public void save(UUID id, StudentsGroup group);
	public StudentsGroup get(UUID id);
	public void delete(UUID id);
}
