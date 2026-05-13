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

    @BeforeEach
    void setUp() {
        studentService = new StudentService(new InMemoStudentRepository());
    }

    @Test
    void create_nameShouldNotContainDigits() {

        // Arrange
        String name = "Jan123";

        // Act
        Student student = studentService.create(name, "Kowalski", "123456");

        // Assert
        assertFalse(student.getName().matches(".*\\d.*"),
                "Name must not contain digits");
    }

    @Test
    void create_surnameShouldNotContainDigits() {

        // Arrange
        String surname = "Kowalski123";

        // Act
        Student student = studentService.create("Jan", surname, "123456");

        // Assert
        assertFalse(student.getSurname().matches(".*\\d.*"),
                "Surname must not contain digits");
    }

    @Test
    void create_albumNumberShouldContainOnlyDigits() {

        // Arrange
        String albumNumber = "ABC123";

        // Act
        Student student = studentService.create("Jan", "Kowalski", albumNumber);

        // Assert
        assertTrue(student.getAlbumNumber().matches("\\d+"),
                "Album number must contain only digits");
    }

    @Test
    void create_shouldRejectEmptyStrings() {

        // Act
        Student student = studentService.create("", "", "");

        // Assert
        assertFalse(student.getName().isBlank(), "Name should not be empty");
        assertFalse(student.getSurname().isBlank(), "Surname should not be empty");
        assertFalse(student.getAlbumNumber().isBlank(), "Album number should not be empty");
    }

    @Test
    void create_shouldRejectWhitespaceOnlyStrings() {

        // Act
        Student student = studentService.create("   ", "   ", "   ");

        // Assert
        assertFalse(student.getName().isBlank(), "Name should not be whitespace only");
        assertFalse(student.getSurname().isBlank(), "Surname should not be whitespace only");
        assertFalse(student.getAlbumNumber().isBlank(), "Album number should not be whitespace only");
    }

    @Test
    void create_shouldRejectNullValues() {

        // Act
        Student student = studentService.create(null, null, null);

        // Assert
        assertNotNull(student.getName(), "Name should not be null");
        assertNotNull(student.getSurname(), "Surname should not be null");
        assertNotNull(student.getAlbumNumber(), "Album number should not be null");
    }

    @Test
    void create_shouldReturnStudentWithCorrectData() {

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
    void create_shouldAssignUniqueId() {

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
        Exception exception = assertThrows(Exception.class,
                () -> studentService.get(randomId));

        assertEquals("student not found", exception.getMessage());
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
    void edit_shouldReturnNullForUnknownId() {

        // Arrange
        UUID randomId = UUID.randomUUID();

        // Act
        Student result = studentService.edit(randomId, "Jan", "Kowalski", "111111");

        // Assert
        assertNull(result);
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
    void delete_shouldRemoveStudentFromRepository() {

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