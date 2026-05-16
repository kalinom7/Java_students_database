package domain.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * Represents a subject in the system.
 *
 * The class stores subject information such as:
 * name, unique identifier, grading criteria
 * and student scores assigned to criteria.
 *
 * The identifier is generated automatically when a new Subject object is created.
 */

public class Subject {
	private String name;
	private UUID id;
	
	/*
	 * Criteria list stores all grading criteria assigned to the subject.
	 *
	 * Each criterium contains its name and maximum number of points (integer).
	 */
	private ArrayList<Criterium> criteria;
	
	/*
	 * Stores students' scores.
	 *
	 * UUID represents student identifier.
	 * ArrayList stores scores assigned to criteria for this student.
	 */
	private Map<UUID, ArrayList<CriteriumStudentScore>> studentsScore;

	/**
	 * Creates a new Subject object.
	 *
	 * @param name subject name
	 */
	public Subject(String name) {
		this.name = name;
		this.id =  UUID.randomUUID();
		this.criteria = new  ArrayList<>();
		this.studentsScore = new HashMap<>();
	}

	/*
	 * GETTERS
	 */
	
	public String getName() {
		return name;
	}
	public UUID getId() {
		return this.id;
	}

	public ArrayList<Criterium> getCriteria() {
		return criteria;
	}

	public Map<UUID, ArrayList<CriteriumStudentScore>> getStudentsScore() {
		return studentsScore;
	}

	/*
	 * SETTERS
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
}
