package domain.subject;

import java.util.UUID;

/*
 * Interface responsible for subject data storage.
 *
 * Defines methods for storing, retrieving
 * and removing subject objects.
 */

public interface SubjectRepository {
	public void save(UUID id, Subject subject);
	public Subject get(UUID id);
	public void delete(UUID id);
}
