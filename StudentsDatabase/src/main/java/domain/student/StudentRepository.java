package domain.student;

import java.util.UUID;

public interface StudentRepository {
	public void save(UUID id,Student student);
	public Student get(UUID id);
	public void delete(UUID id);
}
