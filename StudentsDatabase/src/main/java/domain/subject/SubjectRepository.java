package domain.subject;

import java.util.UUID;

public interface SubjectRepository {
	public void save(UUID id, Subject subject);
	public Subject get(UUID id);
	public void delete(UUID id);
}
