package domain.studentsGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoStudentsGroupRepository implements StudentsGroupRepository{
	private Map<UUID, StudentsGroup> studentsGroups = new HashMap<>();
	
	public void save(UUID id, StudentsGroup studentsGroup) {
		studentsGroups.put(id, studentsGroup);
	}
	public StudentsGroup get(UUID id) {
		StudentsGroup studentsGroup = studentsGroups.get(id);
		
		return studentsGroup;
	}
	public void delete(UUID id) {
		studentsGroups.remove(id);
	}
}
