package domain.studentsGroup;

import java.util.UUID;

public interface StudentsGroupRepository {
	public void save(UUID id, StudentsGroup group);
	public StudentsGroup get(UUID id);
	public void delete(UUID id);
}
