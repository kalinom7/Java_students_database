package domain.student;

import java.util.UUID;

/*
 * Interface responsible for student data storage.
 *
 * Defines methods for storing, retrieving
 * and removing student objects.
 */
public interface StudentRepository {
	public void save(UUID id,Student student);
	public Student get(UUID id);
	public void delete(UUID id);
}
