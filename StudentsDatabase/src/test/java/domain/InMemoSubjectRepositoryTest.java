package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import domain.subject.InMemoSubjectRepository;
import domain.subject.Subject;

/**
 * Unit tests for InMemoSubjectRepository.
 * 
 * Tests follow the AAA pattern:
 * Arrange -> Act -> Assert
 */

class InMemoSubjectRepositoryTest {

	@Test
	void saveShouldStoreSubjectUnderGivenId() {

		// Arrange
		// Create repository and sample subject.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		UUID id = UUID.randomUUID();
		Subject subject = new Subject("Java");

		// Act
		// Save subject and retrieve it using the same id.
		repository.save(id, subject);
		Subject result = repository.get(id);

		// Assert
		// Retrieved object should be exactly the same instance.
		assertSame(subject, result);
	}

	@Test
	void getShouldReturnNullWhenSubjectDoesNotExist() {

		// Arrange
		// Create repository without storing any subjects.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		UUID nonExistingId = UUID.randomUUID();

		// Act
		// Try to retrieve subject using non-existing id.
		Subject result = repository.get(nonExistingId);

		// Assert
		// Repository should return null for missing subject.
		assertNull(result);
	}

	@Test
	void saveShouldOverwriteSubjectWhenIdAlreadyExists() {

		// Arrange
		// Create repository and two subjects with the same id.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		UUID id = UUID.randomUUID();

		Subject oldSubject = new Subject("Old name");
		Subject newSubject = new Subject("New name");

		// Act
		// Save first subject and overwrite it with another one.
		repository.save(id, oldSubject);
		repository.save(id, newSubject);

		Subject result = repository.get(id);

		// Assert
		// Repository should store the newest object for duplicated id.
		assertSame(newSubject, result);
		assertEquals("New name", result.getName());
	}

	@Test
	void deleteShouldRemoveExistingSubject() {

		// Arrange
		// Create repository and save sample subject.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		UUID id = UUID.randomUUID();
		Subject subject = new Subject("Java");

		repository.save(id, subject);

		// Act
		// Delete subject and try to retrieve it again.
		repository.delete(id);
		Subject result = repository.get(id);

		// Assert
		// Deleted subject should no longer exist in repository.
		assertNull(result);
	}

	@Test
	void deleteShouldNotThrowExceptionWhenSubjectDoesNotExist() {

		// Arrange
		// Create repository and random non-existing id.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		UUID nonExistingId = UUID.randomUUID();

		// Act + Assert
		// Deleting non-existing object should be safe.
		assertDoesNotThrow(() -> repository.delete(nonExistingId));
	}

	@Test
	void saveShouldThrowExceptionWhenIdIsNull() {

		// Arrange
		// Create repository and valid subject.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		Subject subject = new Subject("Subject with null id");

		// Act + Assert
		// Repository should reject null ids.
		assertThrows(IllegalArgumentException.class,
				() -> repository.save(null, subject));
	}

	@Test
	void saveShouldThrowExceptionWhenSubjectIsNull() {

		// Arrange
		// Create repository and valid id.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		UUID id = UUID.randomUUID();

		// Act + Assert
		// Repository should reject null subject objects.
		assertThrows(IllegalArgumentException.class,
				() -> repository.save(id, null));
	}

	@Test
	void getShouldThrowExceptionWhenIdIsNull() {

		// Arrange
		// Create empty repository.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();

		// Act + Assert
		// Repository should reject null ids during retrieval.
		assertThrows(IllegalArgumentException.class,
				() -> repository.get(null));
	}

	@Test
	void deleteShouldThrowExceptionWhenIdIsNull() {

		// Arrange
		// Create empty repository.
		InMemoSubjectRepository repository = new InMemoSubjectRepository();

		// Act + Assert
		// Repository should reject null ids during deletion.
		assertThrows(IllegalArgumentException.class,
				() -> repository.delete(null));
	}
}