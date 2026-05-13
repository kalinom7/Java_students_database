package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.studentsGroup.InMemoStudentsGroupRepository;
import domain.studentsGroup.StudentsGroup;
import domain.studentsGroup.StudentsGroupRepository;
import domain.studentsGroup.StudentsGroupService;

/**
 * Test class for StudentsGroupService.
 * Tests verify creating, retrieving, editing,
 * deleting groups and adding students to groups.
 */
class StudentsGroupServiceTest {

	// service object used in tests
	private StudentsGroupService service;

	@BeforeEach
	void setUp() {

		// create in-memory repository
		StudentsGroupRepository repository = new InMemoStudentsGroupRepository();

		// create service object with repository
		service = new StudentsGroupService(repository);
	}

	@Test
	void createShouldReturnGroupWithId() {

		// Arrange
		// service already exists because of @BeforeEach

		// Act
		// create new students group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// Assert
		// verify that group exists
		assertNotNull(group);

		// verify that generated id exists
		assertNotNull(group.getId());
	}

	@Test
	void getShouldReturnExistingGroup() throws Exception {

		// Arrange
		// create and save group in repository
		StudentsGroup createdGroup = service.create("Cloud", "C1", "Test group");

		// Act
		// get group by id
		StudentsGroup result = service.get(createdGroup.getId());

		// Assert
		// verify that correct group was returned
		assertEquals(createdGroup.getId(), result.getId());
	}

	@Test
	void getShouldThrowExceptionWhenGroupDoesNotExist() {

		// Arrange
		// create random non-existing id
		UUID fakeId = UUID.randomUUID();

		// Act + Assert
		// verify that exception is thrown
		assertThrows(Exception.class, () -> {
			service.get(fakeId);
		});
	}

	@Test
	void addStudentShouldAddStudentToExistingGroup() {

		// Arrange
		// create group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// create sample student id
		UUID studentId = UUID.randomUUID();

		// Act
		// add student to group
		StudentsGroup result = service.addStudent(group.getId(), studentId);

		// Assert
		// verify that student id exists in group
		assertTrue(result.getStudentsInGroup().contains(studentId));
	}

	@Test
	void addStudentShouldNotAddSameStudentTwice() {

		// Arrange
		// create group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// create sample student id
		UUID studentId = UUID.randomUUID();

		// Act
		// add the same student twice
		service.addStudent(group.getId(), studentId);
		service.addStudent(group.getId(), studentId);

		// Assert
		// verify that set contains only one student
		assertEquals(1, group.getStudentsInGroup().size());
	}

	@Test
	void addStudentShouldThrowExceptionWhenGroupDoesNotExist() {

		// Arrange
		// create random ids
		UUID fakeGroupId = UUID.randomUUID();
		UUID studentId = UUID.randomUUID();

		// Act + Assert
		// verify that exception is thrown
		assertThrows(NullPointerException.class, () -> {
			service.addStudent(fakeGroupId, studentId);
		});
	}

	@Test
	void editShouldChangeGroupData() {

		// Arrange
		// create initial group with original data
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// Act
		// edit existing group data
		StudentsGroup result = service.edit(
				group.getId(),
				"Cybersecurity",
				"C2",
				"Edited group");

		// Assert
		// verify that all fields were updated correctly
		assertEquals("Cybersecurity", result.getSpecialization());
		assertEquals("C2", result.getGroupCode());
		assertEquals("Edited group", result.getDescription());
	}

	@Test
	void editShouldReturnNullWhenGroupDoesNotExist() {

		// Arrange
		// create random non-existing id
		UUID fakeId = UUID.randomUUID();

		// Act
		// try to edit non-existing group
		StudentsGroup result = service.edit(
				fakeId,
				"Cloud",
				"C1",
				"Test group");

		// Assert
		// verify that null was returned
		assertNull(result);
	}

	@Test
	void deleteShouldRemoveExistingGroup() {

		// Arrange
		// create group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// Act
		// delete group
		service.delete(group.getId());

		// Assert
		// verify that deleted group no longer exists
		assertThrows(Exception.class, () -> {
			service.get(group.getId());
		});
	}

	@Test
	void deleteShouldNotThrowExceptionWhenGroupDoesNotExist() {

		// Arrange
		// create random non-existing id
		UUID fakeId = UUID.randomUUID();

		// Act + Assert
		// verify that deleting non-existing group does not throw exception
		assertDoesNotThrow(() -> {
			service.delete(fakeId);
		});
	}
	@Test
	void createShouldNotAllowNullSpecialization() {

		// Arrange
		// create null specialization
		String specialization = null;

		// Act + Assert
		// verify that exception is thrown for null specialization
		assertThrows(IllegalArgumentException.class, () -> {
			service.create(specialization, "C1", "Test group");
		});
	}

	@Test
	void createShouldNotAllowEmptyGroupCode() {

		// Arrange
		// create empty group code
		String groupCode = "";

		// Act + Assert
		// verify that exception is thrown for empty group code
		assertThrows(IllegalArgumentException.class, () -> {
			service.create("Cloud", groupCode, "Test group");
		});
	}

	@Test
	void createShouldNotAllowBlankDescription() {

		// Arrange
		// create blank description
		String description = "   ";

		// Act + Assert
		// verify that exception is thrown for blank description
		assertThrows(IllegalArgumentException.class, () -> {
			service.create("Cloud", "C1", description);
		});
	}

	@Test
	void addStudentShouldNotAllowNullStudentId() {

		// Arrange
		// create valid group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// Act + Assert
		// verify that exception is thrown for null student id
		assertThrows(IllegalArgumentException.class, () -> {
			service.addStudent(group.getId(), null);
		});
	}

	@Test
	void addStudentShouldNotAllowNullGroupId() {

		// Arrange
		// create sample student id
		UUID studentId = UUID.randomUUID();

		// Act + Assert
		// verify that exception is thrown for null group id
		assertThrows(IllegalArgumentException.class, () -> {
			service.addStudent(null, studentId);
		});
	}

	@Test
	void editShouldNotAllowNullSpecialization() {

		// Arrange
		// create initial group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// Act + Assert
		// verify that exception is thrown for null specialization
		assertThrows(IllegalArgumentException.class, () -> {
			service.edit(group.getId(), null, "C2", "Edited group");
		});
	}

	@Test
	void editShouldNotAllowEmptyGroupCode() {

		// Arrange
		// create initial group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// Act + Assert
		// verify that exception is thrown for empty group code
		assertThrows(IllegalArgumentException.class, () -> {
			service.edit(group.getId(), "Cybersecurity", "", "Edited group");
		});
	}

	@Test
	void editShouldNotAllowBlankDescription() {

		// Arrange
		// create initial group
		StudentsGroup group = service.create("Cloud", "C1", "Test group");

		// Act + Assert
		// verify that exception is thrown for blank description
		assertThrows(IllegalArgumentException.class, () -> {
			service.edit(group.getId(), "Cybersecurity", "C2", "   ");
		});
	}

	@Test
	void studentsInGroupShouldNotBeModifiableFromOutside() {

		// Arrange
		// create group and add student
		StudentsGroup group = service.create("Cloud", "C1", "Test group");
		UUID studentId = UUID.randomUUID();

		service.addStudent(group.getId(), studentId);

		// Act
		// modify internal set from outside class
		group.getStudentsInGroup().clear();

		// Assert
		// verify that external modification should not be possible
		assertTrue(group.getStudentsInGroup().contains(studentId));
	}
}