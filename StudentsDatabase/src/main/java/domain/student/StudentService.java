package domain.student;

import java.util.UUID;

import language.LanguageManager;

public class StudentService {
	private StudentRepository studentRepository;

	public StudentService(StudentRepository repository) {
		studentRepository = repository;
	}

	public Student create(String name, String surname, String albumNumber) throws Exception {
		// validate for empty strings
		if (name.trim().isEmpty() || name.isBlank() || surname.trim().isEmpty() || surname.isBlank()
				|| albumNumber.trim().isEmpty() || albumNumber.isBlank()) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}
		
		
		// validate albumNumber
		if (!albumNumber.matches("\\d+")) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}
		// validate name
		if (name.matches(".*\\d.*")) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}
		// validate surname
		if (surname.matches(".*\\d.*")) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}

		Student student = new Student(name.trim(), surname.trim(), albumNumber.trim());
		UUID id = student.getId();

		studentRepository.save(id, student);
		return student;
	}

	public Student get(UUID id) throws Exception {
		Student student = studentRepository.get(id);
		if (student == null) {
			throw new Exception(LanguageManager.get("error.student.notFound"));
		}

		return student;
	}

	public Student edit(UUID id, String name, String surname, String albumNumber) throws Exception {
		Student student = studentRepository.get(id);
		if (student == null) {
			throw new Exception(LanguageManager.get("error.student.notFound"));
		}

		student.setAlbumNumber(albumNumber);
		student.setName(name);
		student.setSurname(surname);

		studentRepository.save(id, student);

		return student;
	}

	public StudentRepository getStudentRepository() {
		return studentRepository;
	}

	public void delete(UUID id) {
		studentRepository.delete(id);
	}
}
