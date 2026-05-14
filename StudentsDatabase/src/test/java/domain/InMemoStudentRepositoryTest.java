package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.student.InMemoStudentRepository;
import domain.student.Student;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoStudentRepositoryTest {

    private InMemoStudentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoStudentRepository();
    }

    @Test
    void get_shouldReturnNullForUnknownId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        Student result = repository.get(id);

        // Assert
        assertNull(result);
    }

    @Test
    void save_shouldOverwriteExistingEntry() {
        // Arrange
        UUID id = UUID.randomUUID();
        Student original = new Student("Jan", "Kowalski", "111111");
        Student replacement = new Student("Anna", "Nowak", "222222");

        repository.save(id, original);

        // Act
        repository.save(id, replacement);
        Student result = repository.get(id);

        // Assert
        assertEquals(replacement, result);
    }

    @Test
    void saveAndGet_shouldReturnSavedStudent() {
        // Arrange
        UUID id = UUID.randomUUID();
        Student student = new Student("Jan", "Kowalski", "123456");

        // Act
        repository.save(id, student);
        Student result = repository.get(id);

        // Assert
        assertEquals(student, result);
    }

    @Test
    void delete_shouldRemoveStudent() {
        // Arrange
        UUID id = UUID.randomUUID();
        Student student = new Student("Jan", "Kowalski", "123456");
        repository.save(id, student);

        // Act
        repository.delete(id);
        Student result = repository.get(id);

        // Assert
        assertNull(result);
    }

    @Test
    void delete_nonExistentId_shouldNotThrow() {
        // Arrange
        UUID randomId = UUID.randomUUID();

        // Act + Assert
        assertDoesNotThrow(() -> repository.delete(randomId));
    }

    @Test
    void delete_shouldOnlyRemoveTargetStudent() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Student s1 = new Student("Jan", "Kowalski", "111111");
        Student s2 = new Student("Anna", "Nowak", "222222");

        repository.save(id1, s1);
        repository.save(id2, s2);

        // Act
        repository.delete(id1);

        // Assert
        assertNull(repository.get(id1));
        assertEquals(s2, repository.get(id2));
    }

    @Test
    void repository_shouldBeEmptyAfterAllDeletes() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        repository.save(id1, new Student("Jan", "Kowalski", "111111"));
        repository.save(id2, new Student("Anna", "Nowak", "222222"));

        // Act
        repository.delete(id1);
        repository.delete(id2);

        // Assert
        assertNull(repository.get(id1));
        assertNull(repository.get(id2));
    }
}