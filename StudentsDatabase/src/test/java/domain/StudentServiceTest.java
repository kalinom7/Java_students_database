package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.student.InMemoStudentRepository;
import domain.student.Student;
import domain.student.StudentService;
import language.LanguageManager;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

	private StudentService studentService;

	@BeforeEach
	void setUp() {
		LanguageManager.setLanguage("en", "US");
		studentService = new StudentService(new InMemoStudentRepository());
	}

	@Test
	void create_nameShouldNotContainDigits() throws Exception {

		// Arrange
		String name = "Jan123";

		// Act && Assert
		assertThrows(Exception.class, () -> studentService.create(name, "Kowalski", "123456"));

	}

	@Test
	void create_surnameShouldNotContainDigits() throws Exception {

		// Arrange
		String surname = "Kowalski123";

		// Act && Assert
		assertThrows(Exception.class, () -> studentService.create("Jan", surname, "123456"));
	}

	@Test
	void create_shouldThrowIfAlbumNuberContainsLetters() throws Exception {

		// Arrange
		String albumNumber = "ABC123";

		// Act && Assert
		assertThrows(Exception.class, () -> studentService.create("Jan", "Kowalski", albumNumber));
	}

	@Test
	void create_shouldRejectEmptyStrings() throws Exception {

		// Act && Assert
		assertThrows(Exception.class, () -> studentService.create("", "", ""));
		assertThrows(Exception.class, () -> studentService.create("Jan", "", ""));
		assertThrows(Exception.class, () -> studentService.create("Jan", "Kowalski", ""));
		assertThrows(Exception.class, () -> studentService.create("Jan", "", "2241"));
		assertThrows(Exception.class, () -> studentService.create("", "Kowalski", "2241"));
		assertThrows(Exception.class, () -> studentService.create("", "Kowalski", ""));
		assertThrows(Exception.class, () -> studentService.create("", "", "2241"));
	}

	@Test
	void create_shouldRejectWhitespaceOnlyStrings() throws Exception {

		// Act && Assert
		assertThrows(Exception.class, () -> studentService.create("	", "  ", " "));
		assertThrows(Exception.class, () -> studentService.create("Jan", " ", "  "));
		assertThrows(Exception.class, () -> studentService.create("Jan", "Kowalski", "  "));
		assertThrows(Exception.class, () -> studentService.create("Jan", "  ", "2241"));
		assertThrows(Exception.class, () -> studentService.create(" ", "Kowalski", "2241"));
		assertThrows(Exception.class, () -> studentService.create(" ", "Kowalski", "  "));
		assertThrows(Exception.class, () -> studentService.create("  ", " ", "2241"));
	}

	@Test
	void create_shouldThrowException_whenAnyFieldIsNull() {
		// Arrange
		String name = "Jan";
		String surname = "Kowalski";
		String album = "123456";

		// Act && Assert
		assertThrows(Exception.class, () -> studentService.create(null, null, null));
		assertThrows(Exception.class, () -> studentService.create(name, null, null));
		assertThrows(Exception.class, () -> studentService.create(null, surname, null));
		assertThrows(Exception.class, () -> studentService.create(null, null, album));
		assertThrows(Exception.class, () -> studentService.create(name, surname, null));
		assertThrows(Exception.class, () -> studentService.create(name, null, album));
		assertThrows(Exception.class, () -> studentService.create(null, surname, album));
	}

	@Test
	void create_shouldReturnStudentWithCorrectData() throws Exception {

		// Arrange
		String name = "Jan";
		String surname = "Kowalski";
		String album = "123456";

		// Act
		Student student = studentService.create(name, surname, album);

		// Assert
		assertNotNull(student);
		assertEquals(name, student.getName());
		assertEquals(surname, student.getSurname());
		assertEquals(album, student.getAlbumNumber());
	}

	@Test
	void create_shouldAssignUniqueId() throws Exception {

		// Act
		Student s1 = studentService.create("Jan", "Kowalski", "111111");
		Student s2 = studentService.create("Anna", "Nowak", "222222");

		// Assert
		assertNotNull(s1.getId());
		assertNotNull(s2.getId());
		assertNotEquals(s1.getId(), s2.getId());
	}

	@Test
	void create_shouldPersistStudentInRepository() throws Exception {

		// Act
		Student created = studentService.create("Jan", "Kowalski", "123456");
		Student fetched = studentService.get(created.getId());

		// Assert
		assertNotNull(fetched);
		assertEquals(created.getId(), fetched.getId());
	}

	@Test
	void get_shouldThrowExceptionForUnknownId() {

		// Arrange
		UUID randomId = UUID.randomUUID();

		// Act + Assert
		Exception exception = assertThrows(Exception.class, () -> studentService.get(randomId));

		assertEquals("Student not found", exception.getMessage());
	}

	@Test
	void get_shouldReturnCorrectStudent() throws Exception {

		// Act
		Student created = studentService.create("Anna", "Nowak", "999999");
		Student fetched = studentService.get(created.getId());

		// Assert
		assertEquals("Anna", fetched.getName());
		assertEquals("Nowak", fetched.getSurname());
		assertEquals("999999", fetched.getAlbumNumber());
	}

	@Test
	void edit_shouldUpdateAllFields() throws Exception {

		// Act
		Student created = studentService.create("Jan", "Kowalski", "111111");

		studentService.edit(created.getId(), "Piotr", "Wiśniewski", "999999");

		Student updated = studentService.get(created.getId());

		// Assert
		assertEquals("Piotr", updated.getName());
		assertEquals("Wiśniewski", updated.getSurname());
		assertEquals("999999", updated.getAlbumNumber());
	}

	@Test
	void edit_shouldThrowForUnknownId() throws Exception {

		// Arrange
		UUID randomId = UUID.randomUUID();

		// Assert
		assertThrows(Exception.class, () -> {
			studentService.edit(randomId, "Jan", "Kowalski", "111111");
		});
	}

	@Test
	void edit_shouldNotChangeStudentId() throws Exception {

		// Act
		Student created = studentService.create("Jan", "Kowalski", "111111");
		UUID originalId = created.getId();

		studentService.edit(originalId, "Piotr", "Wiśniewski", "999999");

		Student updated = studentService.get(originalId);

		// Assert
		assertEquals(originalId, updated.getId());
	}

	@Test
	void delete_shouldRemoveStudentFromRepository() throws Exception {

		// Act
		Student created = studentService.create("Jan", "Kowalski", "123456");
		UUID id = created.getId();

		studentService.delete(id);

		// Assert
		assertThrows(Exception.class, () -> studentService.get(id));
	}

	@Test
	void delete_nonExistentId_shouldNotThrow() {

		// Arrange
		UUID randomId = UUID.randomUUID();

		// Act + Assert
		assertDoesNotThrow(() -> studentService.delete(randomId));
	}

	@Test
	void delete_shouldRemoveOnlyTargetStudent() throws Exception {

		// Act
		Student s1 = studentService.create("Jan", "Kowalski", "111111");
		Student s2 = studentService.create("Anna", "Nowak", "222222");

		studentService.delete(s1.getId());

		// Assert
		assertThrows(Exception.class, () -> studentService.get(s1.getId()));
		assertDoesNotThrow(() -> studentService.get(s2.getId()));
	}
}