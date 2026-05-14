package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import domain.studentsGroup.StudentsGroup;

/**
 * Test class for StudentsGroup.
 * Tests verify object creation, generated id
 * and students collection behavior.
 */
class StudentsGroupTest {

	@Test
	void constructorShouldCreateGroupWithId() {

		// Arrange + Act
		// create new students group
		StudentsGroup group = new StudentsGroup("Cloud", "C1", "Test group");

		// Assert
		// verify that group was created
		assertNotNull(group);

		// verify that id was generated
		assertNotNull(group.getId());
	}

	@Test
	void constructorShouldCreateEmptyStudentsSet() {

		// Arrange + Act
		// create new students group
		StudentsGroup group = new StudentsGroup("Cloud", "C1", "Test group");

		// Assert
		// verify that students set exists
		assertNotNull(group.getStudentsInGroup());

		// verify that new group has no students
		assertTrue(group.getStudentsInGroup().isEmpty());
	}

	@Test
	void studentsSetShouldAllowAddingStudentId() {

		// Arrange
		// create new group and sample student id
		StudentsGroup group = new StudentsGroup("Cloud", "C1", "Test group");
		UUID studentId = UUID.randomUUID();

		// Act
		// add student id directly to students set
		group.getStudentsInGroup().add(studentId);

		// Assert
		// verify that student id was added
		assertTrue(group.getStudentsInGroup().contains(studentId));
	}

	@Test
	void studentsSetShouldNotStoreDuplicatedStudentIds() {

		// Arrange
		// create new group and sample student id
		StudentsGroup group = new StudentsGroup("Cloud", "C1", "Test group");
		UUID studentId = UUID.randomUUID();

		// Act
		// add the same student twice
		group.getStudentsInGroup().add(studentId);
		group.getStudentsInGroup().add(studentId);

		// Assert
		// verify that set stores only one occurrence
		assertEquals(1, group.getStudentsInGroup().size());
	}

	@Test
	void studentsSetShouldNotAllowNullStudentId() {

		// Arrange
		// create new group
		StudentsGroup group = new StudentsGroup(
				"Cloud",
				"C1",
				"Test group");

		// Act + Assert
		// verify that adding null student id throws exception
		assertThrows(IllegalArgumentException.class, () -> {

			group.getStudentsInGroup().add(null);
		});
	}
	@Test
	void constructorShouldSetGroupData() {

		// Arrange + Act
		// create new students group
		StudentsGroup group = new StudentsGroup(
				"Cloud",
				"C1",
				"Test group");

		// Assert
		// verify that constructor correctly sets all fields
		assertEquals("Cloud", group.getSpecialization());
		assertEquals("C1", group.getGroupCode());
		assertEquals("Test group", group.getDescription());
	}
	@Test
	void settersShouldChangeGroupData() {

		// Arrange
		// create initial group
		StudentsGroup group = new StudentsGroup(
				"Cloud",
				"C1",
				"Test group");

		// Act
		// change group data using setters
		group.setSpecialization("Cybersecurity");
		group.setGroupCode("C2");
		group.setDescription("Edited group");

		// Assert
		// verify that setters updated fields correctly
		assertEquals("Cybersecurity", group.getSpecialization());
		assertEquals("C2", group.getGroupCode());
		assertEquals("Edited group", group.getDescription());
	}
	@Test
	void constructorShouldNotAllowInvalidData() {

		// Arrange
		// create invalid input data
		String specialization = null;
		String groupCode = "";
		String description = "   ";

		// Act + Assert
		// verify that constructor throws exception
		// for invalid input data
		assertThrows(IllegalArgumentException.class, () -> {

			// try to create group with invalid data
			new StudentsGroup(
					specialization,
					groupCode,
					description);
		});
	}
}