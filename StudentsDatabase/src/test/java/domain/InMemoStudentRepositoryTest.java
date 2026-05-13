package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.student.InMemoStudentRepository;
import domain.student.Student;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoStudentRepositoryTest {

    private InMemoStudentRepository repository;

    // setup
    @BeforeEach
    void setUp() {
        repository = new InMemoStudentRepository();
    }

    // get() - should return null for unknown ID
    @Test
    void get_shouldReturnNullForUnknownId() {
        UUID id = UUID.randomUUID();

        assertNull(repository.get(id));
    }

    // save() - saving under the same ID should overwrite the previous student
    @Test
    void save_shouldOverwriteExistingEntry() {
        UUID id = UUID.randomUUID();
        Student original = new Student("Jan", "Kowalski", "111111");
        Student replacement = new Student("Anna", "Nowak", "222222");

        repository.save(id, original);
        repository.save(id, replacement);

        assertEquals(replacement, repository.get(id));
    }

    // save() + get() - should save and return the same student
    @Test
    void saveAndGet_shouldReturnSavedStudent() {
        UUID id = UUID.randomUUID();
        Student student = new Student("Jan", "Kowalski", "123456");

        repository.save(id, student);

        assertEquals(student, repository.get(id));
    }

    // delete() - should remove student from repository
    @Test
    void delete_shouldRemoveStudent() {
        UUID id = UUID.randomUUID();
        Student student = new Student("Jan", "Kowalski", "123456");
        repository.save(id, student);

        repository.delete(id);

        assertNull(repository.get(id));
    }

    // delete() - calling delete for a non-existent ID should not throw an exception
    @Test
    void delete_nonExistentId_shouldNotThrow() {
        UUID randomId = UUID.randomUUID();

        assertDoesNotThrow(() -> repository.delete(randomId));
    }

    // delete() - should remove only the student with the given ID
    @Test
    void delete_shouldOnlyRemoveTargetStudent() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Student s1 = new Student("Jan", "Kowalski", "111111");
        Student s2 = new Student("Anna", "Nowak", "222222");

        repository.save(id1, s1);
        repository.save(id2, s2);

        repository.delete(id1);

        assertNull(repository.get(id1));
        assertEquals(s2, repository.get(id2));
    }
    
    // delete() - Repository should be empty after all deletes
    @Test
    void repository_shouldBeEmptyAfterAllDeletes() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        repository.save(id1, new Student("Jan", "Kowalski", "111111"));
        repository.save(id2, new Student("Anna", "Nowak", "222222"));

        repository.delete(id1);
        repository.delete(id2);

        assertNull(repository.get(id1));
        assertNull(repository.get(id2));
    }
}