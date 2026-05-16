package domain.student;

import java.util.UUID;

import language.LanguageManager;

/*
 * Service responsible for student management.
 *
 * Handles student creation, validation,
 * editing, retrieving and deleting operations.
 */

public class StudentService {
	private StudentRepository studentRepository;

	/**
	 * Creates StudentService object and assigns repository.
	 *
	 * @param repository repository used for storing student data
	 */
	public StudentService(StudentRepository repository) {
		studentRepository = repository;
	}

	/**
	 * Creates a new student object.
	 *
	 * The method validates input data, creates a student
	 * object and saves it in the repository.
	 *
	 * @param name student's first name
	 * @param surname student's surname
	 * @param albumNumber student's album number
	 * @return created student object
	 * @throws Exception when provided data is invalid
	 */
	public Student create(String name, String surname, String albumNumber) throws Exception {
		// Validate empty or blank input values
		if (name.trim().isEmpty() || name.isBlank() || surname.trim().isEmpty() || surname.isBlank()
				|| albumNumber.trim().isEmpty() || albumNumber.isBlank()) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}
	
		// Validate that album number contains digits only
		if (!albumNumber.matches("\\d+")) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}
		
		// Validate that name does not contain digits
		if (name.matches(".*\\d.*")) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}
		
		// Validate that surname does not contain digits
		if (surname.matches(".*\\d.*")) {
			throw new Exception(LanguageManager.get("invalid.student.create.data"));
		}

		Student student = new Student(name.trim(), surname.trim(), albumNumber.trim());
		UUID id = student.getId();

		studentRepository.save(id, student);
		return student;
	}

	/**
	 * Returns student object based on provided ID.
	 *
	 * @param id student's unique identifier
	 * @return student object
	 * @throws Exception when student does not exist
	 */
	public Student get(UUID id) throws Exception {
		Student student = studentRepository.get(id);
		if (student == null) {
			throw new Exception(LanguageManager.get("error.student.notFound"));
		}

		return student;
	}

	/**
	 * Updates existing student data.
	 *
	 * @param id student's unique identifier
	 * @param name new student's first name
	 * @param surname new student's surname
	 * @param albumNumber new student's album number
	 * @return updated student object
	 * @throws Exception when student does not exist
	 */
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

	/**
	 * Deletes student object from repository.
	 *
	 * @param id student's unique identifier
	 */
	public void delete(UUID id) {
		studentRepository.delete(id);
	}

	/*
	 * GETTERS
	 */
	public StudentRepository getStudentRepository() {
		return studentRepository;
	}
}
