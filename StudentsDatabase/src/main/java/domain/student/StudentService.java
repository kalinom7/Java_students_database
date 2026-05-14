package domain.student;

import java.util.UUID;

import language.LanguageManager;

public class StudentService {
	private StudentRepository studentRepository;

	public StudentService(StudentRepository repository) {
		studentRepository = repository;
	}
	
	public Student create(String name, String surname, String albumNumber) {
		Student student = new Student(name, surname, albumNumber);
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
