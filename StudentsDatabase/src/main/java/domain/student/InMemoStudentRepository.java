package domain.student;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoStudentRepository implements StudentRepository {
	private Map<UUID, Student> students = new HashMap<>();
	
	@Override
	public void save(UUID id, Student student) {
		students.put(id, student);
	}

	@Override
	public Student get(UUID id) {
		Student student = students.get(id);
		
		return student;
	}

	@Override
	public void delete(UUID id) {
		students.remove(id);
	}
}
