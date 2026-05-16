package domain.subject;

/*
 * Represents student's score assigned to a criterion.
 *
 * Extends Criterium class and additionally stores
 * student's obtained points.
 */

public class CriteriumStudentScore extends Criterium {
	private int studentPoints;

	/**
	 * Creates a new CriteriumStudentScore object.
	 *
	 * @param name criterion name
	 * @param maxPoints maximum available points
	 * @param studentPoints student's obtained points
	 */
	public CriteriumStudentScore(String name, int maxPoints, int studentPoints) {
		super(name, maxPoints);
		this.studentPoints = studentPoints;
	}
	
	/*
	 * GETTERS
	 */
	
	public int getStudentPoints() {
		return studentPoints;
	}

	/*
	 * SETTERS
	 */
	public void setStudentPoints(int studentPoints) {
		this.studentPoints = studentPoints;
	}
}
