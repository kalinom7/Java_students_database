package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.student.InMemoStudentRepository;
import domain.student.Student;
import domain.student.StudentService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

	private StudentService studentService;

	// setup

	@BeforeEach
	void setUp() {
		studentService = new StudentService(new InMemoStudentRepository());
	}

	// Does the system allow digits in the name
	@Test
	void create_shouldAllowDigitsInName() {
		Student student = studentService.create("Jan123", "Kowalski", "123456");

		assertEquals("Jan123", student.getName());
	}

	// Name should not contain digits
	@Test
	void create_nameShouldNotContainDigits() {

		Student student = studentService.create("Jan123", "Kowalski", "123456");

		assertFalse(student.getName().matches(".*\\d.*"));
	}

	// Does the system allow digits in surname
	@Test
	void create_shouldAllowDigitsInSurname() {

		Student student = studentService.create("Jan", "Kowalski123", "123456");

		assertEquals("Kowalski123", student.getSurname());
	}

	// Surname should not contain digits
	@Test
	void create_surnameShouldNotContainDigits() {

		Student student = studentService.create("Jan", "Kowalski123", "123456");

		assertFalse(student.getSurname().matches(".*\\d.*"));
	}

	// Does the system allow letters in album number
	@Test
	void create_shouldAllowLettersInAlbumNumber() {

		Student student = studentService.create("Jan", "Kowalski", "ABC123");

		assertEquals("ABC123", student.getAlbumNumber());
	}

	// Album number should contain only digits
	@Test
	void create_albumNumberShouldContainOnlyDigits() {

		Student student = studentService.create("Jan", "Kowalski", "ABC123");

		assertTrue(student.getAlbumNumber().matches("\\d+"));
	}

	// Does the system allow empty strings
	@Test
	void create_shouldAllowEmptyStrings() {

		Student student = studentService.create("", "", "");

		assertEquals("", student.getName());
		assertEquals("", student.getSurname());
		assertEquals("", student.getAlbumNumber());
	}

	// Does the system allow whitespace-only strings
	@Test
	void create_shouldAllowWhitespaceStrings() {

		Student student = studentService.create("   ", "   ", "   ");

		assertEquals("   ", student.getName());
	}

	// Does the system allow null values
	@Test
	void create_shouldAllowNullValues() {

		Student student = studentService.create(null, null, null);

		assertNull(student.getName());
		assertNull(student.getSurname());
		assertNull(student.getAlbumNumber());
	}

	// create() - should return a student with correct data
	@Test
	void create_shouldReturnStudentWithCorrectData() {
		Student student = studentService.create("Jan", "Kowalski", "123456");

		assertNotNull(student);
		assertEquals("Jan", student.getName());
		assertEquals("Kowalski", student.getSurname());
		assertEquals("123456", student.getAlbumNumber());
	}

	// create() - should assign a unique UUID to each student
	@Test
	void create_shouldAssignUniqueId() {
		Student s1 = studentService.create("Jan", "Kowalski", "111111");
		Student s2 = studentService.create("Anna", "Nowak", "222222");

		assertNotNull(s1.getId());
		assertNotNull(s2.getId());
		assertNotEquals(s1.getId(), s2.getId());
	}

	// create() - should persist student in repository and allow retrieval via get()
	@Test
	void create_shouldPersistStudentInRepository() throws Exception {
		Student created = studentService.create("Jan", "Kowalski", "123456");
		Student fetched = studentService.get(created.getId());

		assertNotNull(fetched);
		assertEquals(created.getId(), fetched.getId());
	}

	// get() - should throw an exception for unknown ID
	@Test
	void get_shouldThrowExceptionForUnknownId() {
		UUID randomId = UUID.randomUUID();

		Exception exception = assertThrows(Exception.class, () -> studentService.get(randomId));

		assertEquals("student not found", exception.getMessage());
	}

	// get() - should return correct student by ID
	@Test
	void get_shouldReturnCorrectStudent() throws Exception {
		Student created = studentService.create("Anna", "Nowak", "999999");

		Student fetched = studentService.get(created.getId());

		assertEquals("Anna", fetched.getName());
		assertEquals("Nowak", fetched.getSurname());
		assertEquals("999999", fetched.getAlbumNumber());
	}

	// edit() - should update all student fields
	@Test
	void edit_shouldUpdateAllFields() throws Exception {
		Student created = studentService.create("Jan", "Kowalski", "111111");

		studentService.edit(created.getId(), "Piotr", "Wiśniewski", "999999");

		Student updated = studentService.get(created.getId());
		assertEquals("Piotr", updated.getName());
		assertEquals("Wiśniewski", updated.getSurname());
		assertEquals("999999", updated.getAlbumNumber());
	}

	// edit() - should return null for unknown ID
	@Test
	void edit_shouldReturnNullForUnknownId() {
		UUID randomId = UUID.randomUUID();

		Student result = studentService.edit(randomId, "Jan", "Kowalski", "111111");

		assertNull(result);
	}

	// edit() - student ID should not change after update
	@Test
	void edit_shouldNotChangeStudentId() throws Exception {
		Student created = studentService.create("Jan", "Kowalski", "111111");
		UUID originalId = created.getId();

		studentService.edit(originalId, "Piotr", "Wiśniewski", "999999");

		Student updated = studentService.get(originalId);
		assertEquals(originalId, updated.getId());
	}

	// delete() - should remove student from repository
	@Test
	void delete_shouldRemoveStudentFromRepository() {
		Student created = studentService.create("Jan", "Kowalski", "123456");
		UUID id = created.getId();

		studentService.delete(id);

		assertThrows(Exception.class, () -> studentService.get(id));
	}

	// delete() - deleting non-existent ID should not throw an exception
	@Test
	void delete_nonExistentId_shouldNotThrow() {
		UUID randomId = UUID.randomUUID();

		assertDoesNotThrow(() -> studentService.delete(randomId));
	}

	// delete() - should remove only the targeted student
	@Test
	void delete_shouldRemoveOnlyTargetStudent() throws Exception {
		Student s1 = studentService.create("Jan", "Kowalski", "111111");
		Student s2 = studentService.create("Anna", "Nowak", "222222");

		studentService.delete(s1.getId());

		assertThrows(Exception.class, () -> studentService.get(s1.getId()));
		assertDoesNotThrow(() -> studentService.get(s2.getId()));
	}
}
