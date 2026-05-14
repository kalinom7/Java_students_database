package domain.subject;

public class CriteriumStudentScore extends Criterium {
	private int studentPoints;

	public CriteriumStudentScore(String name, int maxPoints, int studentPoints) {
		super(name, maxPoints);
		this.studentPoints = studentPoints;
	}
	public int getStudentPoints() {
		return studentPoints;
	}

	public void setStudentPoints(int studentPoints) {
		this.studentPoints = studentPoints;
	}
}
