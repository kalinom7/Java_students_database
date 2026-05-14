package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.studentsGroup.InMemoStudentsGroupRepository;
import domain.studentsGroup.StudentsGroup;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoStudentsGroupRepositoryTest {

    private InMemoStudentsGroupRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoStudentsGroupRepository();
    }


    @Test
    void get_shouldReturnNullForUnknownId() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        StudentsGroup result = repository.get(id);

        // Assert
        assertNull(result);
    }

    @Test
    void get_shouldReturnNullOnFreshRepository() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        StudentsGroup result = repository.get(id);

        // Assert
        assertNull(result, "Fresh repository should return null for any id");
    }


    @Test
    void save_shouldPersistGroup() {
        // Arrange
        UUID id = UUID.randomUUID();
        StudentsGroup group = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");

        // Act
        repository.save(id, group);

        // Assert
        StudentsGroup result = repository.get(id);
        assertEquals(group, result);
    }

    @Test
    void save_shouldOverwriteExistingEntry() {
        // Arrange
        UUID id = UUID.randomUUID();
        StudentsGroup original = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");
        StudentsGroup replacement = new StudentsGroup("Matematyka", "MAT-2025", "Grupa wieczorowa");
        repository.save(id, original);

        // Act
        repository.save(id, replacement);

        // Assert
        StudentsGroup result = repository.get(id);
        assertEquals(replacement, result);
    }

    @Test
    void save_shouldStoreMultipleGroupsUnderDifferentIds() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        StudentsGroup group1 = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");
        StudentsGroup group2 = new StudentsGroup("Matematyka", "MAT-2025", "Grupa wieczorowa");

        // Act
        repository.save(id1, group1);
        repository.save(id2, group2);

        // Assert
        assertEquals(group1, repository.get(id1));
        assertEquals(group2, repository.get(id2));
    }

    @Test
    void save_shouldNotAffectOtherEntriesWhenOverwriting() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        StudentsGroup group1 = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");
        StudentsGroup group2 = new StudentsGroup("Matematyka", "MAT-2025", "Grupa wieczorowa");
        StudentsGroup group1replacement = new StudentsGroup("Fizyka", "FIZ-2026", "Grupa zaoczna");
        repository.save(id1, group1);
        repository.save(id2, group2);

        // Act
        repository.save(id1, group1replacement);

        // Assert
        assertEquals(group2, repository.get(id2));
    }

    @Test
    //todo: 
    void save_shouldThrowSavingSameGroupObjectUnderDifferentIds() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        StudentsGroup group = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");

        // Act
        repository.save(id1, group);
        repository.save(id2, group);

        // Assert
       assertTrue(false);
    }

    @Test
    void save_shouldPreserveGroupFields() {
        // Arrange
        UUID id = UUID.randomUUID();
        StudentsGroup group = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");

        // Act
        repository.save(id, group);

        // Assert
        StudentsGroup result = repository.get(id);
        assertEquals("Informatyka", result.getSpecialization());
        assertEquals("INF-2024", result.getGroupCode());
        assertEquals("Grupa dzienna", result.getDescription());
    }

    @Test
    void save_shouldPreserveGroupId() {
        // Arrange
        UUID repositoryId = UUID.randomUUID();
        StudentsGroup group = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");
        UUID groupId = group.getId();

        // Act
        repository.save(repositoryId, group);

        // Assert
        StudentsGroup result = repository.get(repositoryId);
        assertEquals(groupId, result.getId());
    }


    @Test
    void delete_shouldRemoveGroup() {
        // Arrange
        UUID id = UUID.randomUUID();
        StudentsGroup group = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");
        repository.save(id, group);

        // Act
        repository.delete(id);

        // Assert
        StudentsGroup result = repository.get(id);
        assertNull(result);
    }

    @Test
    void delete_shouldOnlyRemoveTargetGroup() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        StudentsGroup group1 = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");
        StudentsGroup group2 = new StudentsGroup("Matematyka", "MAT-2025", "Grupa wieczorowa");
        repository.save(id1, group1);
        repository.save(id2, group2);

        // Act
        repository.delete(id1);

        // Assert
        assertNull(repository.get(id1));
        assertEquals(group2, repository.get(id2));
    }
    
    @Test
    void delete_shouldLeaveRepositoryEmptyAfterRemovingAllGroups() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        repository.save(id1, new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna"));
        repository.save(id2, new StudentsGroup("Matematyka", "MAT-2025", "Grupa wieczorowa"));

        // Act
        repository.delete(id1);
        repository.delete(id2);

        // Assert
        assertNull(repository.get(id1));
        assertNull(repository.get(id2));
    }
    
    @Test
    void delete_nonExistentId_shouldNotThrow() {
        // Arrange
        UUID randomId = UUID.randomUUID();

        // Act
        Exception exception = null;
        try {
            repository.delete(randomId);
        } catch (Exception e) {
            exception = e;
        }

        // Assert
        assertNull(exception);
    }

    @Test
    void delete_shouldBeIdempotent() {
        // Arrange
        UUID id = UUID.randomUUID();
        StudentsGroup group = new StudentsGroup("Informatyka", "INF-2024", "Grupa dzienna");
        repository.save(id, group);
        repository.delete(id);

        // Act
        Exception exception = null;
        try {
            repository.delete(id);
        } catch (Exception e) {
            exception = e;
        }

        // Assert
        assertNull(exception);
    }


}