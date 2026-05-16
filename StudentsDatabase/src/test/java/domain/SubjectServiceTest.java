package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import domain.subject.Criterium;
import domain.subject.CriteriumStudentScore;
import domain.subject.InMemoSubjectRepository;
import domain.subject.Subject;
import domain.subject.SubjectService;

/**
 * Unit tests for SubjectService.
 *
  * Tests follow the AAA pattern:
 * Arrange -> Act -> Assert
 */
class SubjectServiceTest {

	@Test
	void createShouldCreateSubjectWithGivenName() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		// Act
		Subject result = service.create("Java");

		// Assert
		assertNotNull(result);
		assertEquals("Java", result.getName());
	}

	@Test
	void createShouldCreateSubjectWithNotNullId() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		// Act
		Subject result = service.create("Java");

		// Assert
		// Subject should have generated id, otherwise it is saved under null key.
		assertNotNull(result.getId());
	}

	@Test
	void createShouldSaveSubjectInRepositoryUnderItsId() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		// Act
		Subject result = service.create("Java");
		Subject savedSubject = repository.get(result.getId());

		// Assert
		assertSame(result, savedSubject);
	}

	@Test
	void addCriteriumShouldAddCriteriumToExistingSubject() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject subject = new Subject("Java");
		repository.save(subjectId, subject);

		// Act
		Subject result = service.addCriterium(subjectId, "Test 1", 20);

		// Assert
		assertEquals(1, result.getCriteria().size());
		assertEquals("Test 1", result.getCriteria().get(0).getName());
		assertEquals(20, result.getCriteria().get(0).getMaxPoints());
	}

	@Test
	void addCriteriumShouldThrowExceptionWhenSubjectDoesNotExist() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);
		UUID nonExistingId = UUID.randomUUID();

		// Act + Assert
		assertThrows(Exception.class,
				() -> service.addCriterium(nonExistingId, "Test 1", 20));
	}

	@Test
	void removeCriteriumShouldRemoveOnlyMatchingCriterium() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject subject = new Subject("Java");

		subject.getCriteria().add(new Criterium("Test 1", 20));
		subject.getCriteria().add(new Criterium("Project", 30));

		repository.save(subjectId, subject);

		// Act
		Subject result = service.removeCriterium(subjectId, "Test 1");

		// Assert
		assertEquals(1, result.getCriteria().size());
		assertEquals("Project", result.getCriteria().get(0).getName());
	}

	@Test
	void removeCriteriumShouldRemoveAllMatchingCriteria() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject subject = new Subject("Java");

		subject.getCriteria().add(new Criterium("Test", 10));
		subject.getCriteria().add(new Criterium("Test", 20));
		subject.getCriteria().add(new Criterium("Project", 30));

		repository.save(subjectId, subject);

		// Act
		Subject result = service.removeCriterium(subjectId, "Test");

		// Assert
		assertEquals(1, result.getCriteria().size());
		assertEquals("Project", result.getCriteria().get(0).getName());
	}

	@Test
	void removeCriteriumShouldDoNothingWhenCriteriumDoesNotExist() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));

		repository.save(subjectId, subject);

		// Act
		Subject result = service.removeCriterium(subjectId, "Non existing criterium");

		// Assert
		assertEquals(1, result.getCriteria().size());
		assertEquals("Test 1", result.getCriteria().get(0).getName());
	}

	@Test
	void editCriteriumShouldEditMatchingCriterium() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Old test", 10));

		repository.save(subjectId, subject);

		// Act
		Subject result = service.editCriterium(subjectId, "Old test", "New test", 25);

		// Assert
		assertEquals(1, result.getCriteria().size());
		assertEquals("New test", result.getCriteria().get(0).getName());
		assertEquals(25, result.getCriteria().get(0).getMaxPoints());
	}

	@Test
	void editCriteriumShouldDoNothingWhenCriteriumDoesNotExist() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));

		repository.save(subjectId, subject);

		// Act
		Subject result = service.editCriterium(subjectId, "Wrong name", "New name", 100);

		// Assert
		assertEquals(1, result.getCriteria().size());
		assertEquals("Test 1", result.getCriteria().get(0).getName());
		assertEquals(20, result.getCriteria().get(0).getMaxPoints());
	}

	@Test
	void editSubjectNameShouldChangeSubjectName() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject subject = new Subject("Old name");

		repository.save(subjectId, subject);

		// Act
		Subject result = service.editSubjectName(subjectId, "New name");

		// Assert
		assertEquals("New name", result.getName());
	}

	@Test
	void editSubjectNameShouldThrowExceptionWhenSubjectDoesNotExist() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);
		UUID nonExistingId = UUID.randomUUID();

		// Act + Assert
		assertThrows(Exception.class,
				() -> service.editSubjectName(nonExistingId, "New name"));
	}

	@Test
	void editShouldReplaceSubjectInRepository() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject newSubject = new Subject("New subject");

		// Act
		Subject result = service.edit(subjectId, newSubject);

		// Assert
		assertSame(newSubject, result);
		assertSame(newSubject, repository.get(subjectId));
	}

	@Test
	void restoreShouldReplaceSubjectInRepository() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		Subject copyToRestore = new Subject("Restored subject");

		// Act
		Subject result = service.restore(subjectId, copyToRestore);

		// Assert
		assertSame(copyToRestore, result);
		assertSame(copyToRestore, repository.get(subjectId));
	}

	@Test
	void addStudentScoreShouldAddScoreWhenPointsAreValid() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));
		subject.getStudentsScore().put(studentId, new ArrayList<CriteriumStudentScore>());

		repository.save(subjectId, subject);

		// Act
		Subject result = service.addStudentScore(subjectId, studentId, 15, "Test 1");

		// Assert
		assertEquals(1, result.getStudentsScore().get(studentId).size());
		assertEquals("Test 1", result.getStudentsScore().get(studentId).get(0).getName());
		assertEquals(20, result.getStudentsScore().get(studentId).get(0).getMaxPoints());
		assertEquals(15, result.getStudentsScore().get(studentId).get(0).getStudentPoints());
	}

	@Test
	void addStudentScoreShouldAllowZeroPoints() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));
		subject.getStudentsScore().put(studentId, new ArrayList<CriteriumStudentScore>());

		repository.save(subjectId, subject);

		// Act
		Subject result = service.addStudentScore(subjectId, studentId, 0, "Test 1");

		// Assert
		assertEquals(1, result.getStudentsScore().get(studentId).size());
		assertEquals(0, result.getStudentsScore().get(studentId).get(0).getStudentPoints());
	}

	@Test
	void addStudentScoreShouldAllowMaxPoints() throws Exception {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));
		subject.getStudentsScore().put(studentId, new ArrayList<CriteriumStudentScore>());

		repository.save(subjectId, subject);

		// Act
		Subject result = service.addStudentScore(subjectId, studentId, 20, "Test 1");

		// Assert
		assertEquals(1, result.getStudentsScore().get(studentId).size());
		assertEquals(20, result.getStudentsScore().get(studentId).get(0).getStudentPoints());
	}

	@Test
	void addStudentScoreShouldThrowExceptionWhenPointsAreGreaterThanMaxPoints() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));
		subject.getStudentsScore().put(studentId, new ArrayList<CriteriumStudentScore>());

		repository.save(subjectId, subject);

		// Act + Assert
		assertThrows(Exception.class,
				() -> service.addStudentScore(subjectId, studentId, 25, "Test 1"));
	}

	@Test
	void addStudentScoreShouldThrowExceptionWhenPointsAreNegative() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));
		subject.getStudentsScore().put(studentId, new ArrayList<CriteriumStudentScore>());

		repository.save(subjectId, subject);

		// Act + Assert
		// Negative score should not be accepted.
		assertThrows(IllegalArgumentException.class,
				() -> service.addStudentScore(subjectId, studentId, -1, "Test 1"));
	}

	@Test
	void addStudentScoreShouldThrowExceptionWhenCriteriumDoesNotExist() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		Subject subject = new Subject("Java");
		subject.getStudentsScore().put(studentId, new ArrayList<CriteriumStudentScore>());

		repository.save(subjectId, subject);

		// Act + Assert
		// Score should not be added for missing criterium.
		assertThrows(Exception.class,
				() -> service.addStudentScore(subjectId, studentId, 10, "Missing criterium"));
	}

	@Test
	void addStudentScoreShouldThrowExceptionWhenStudentHasNoScoreList() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID subjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		Subject subject = new Subject("Java");
		subject.getCriteria().add(new Criterium("Test 1", 20));

		repository.save(subjectId, subject);

		// Act + Assert
		// Student should have initialized score list before adding scores.
		assertThrows(NullPointerException.class,
				() -> service.addStudentScore(subjectId, studentId, 15, "Test 1"));
	}

	@Test
	void addStudentScoreShouldThrowExceptionWhenSubjectDoesNotExist() {

		// Arrange
		InMemoSubjectRepository repository = new InMemoSubjectRepository();
		SubjectService service = new SubjectService(repository);

		UUID nonExistingSubjectId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		// Act + Assert
		assertThrows(Exception.class,
				() -> service.addStudentScore(nonExistingSubjectId, studentId, 10, "Test 1"));
	}
}