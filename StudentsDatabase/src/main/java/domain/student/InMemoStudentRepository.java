package domain.student;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * In-memory implementation of StudentRepository.
 *
 * Stores student objects in a HashMap using UUID
 * as a unique key.
 */
public class InMemoStudentRepository implements StudentRepository {
	private Map<UUID, Student> students = new HashMap<>();
	
	@Override
	public void save(UUID id, Student student) {
		// Store student object using UUID as a unique key
		students.put(id, student);
	}

	@Override
	public Student get(UUID id) {
		// Retrieve student object associated with the provided ID
		Student student = students.get(id);
		
		return student;
	}

	@Override
	public void delete(UUID id) {
		// Remove student from collection if the ID exists
		students.remove(id);
	}
}
