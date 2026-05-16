package domain.subject;

/*
 * Represents a grading criterion in the system.
 *
 * The class stores criterion information such as:S
 * name and maximum available points.
 */

public class Criterium {
	private String name;
	private int maxPoints;
	
	
	/**
	 * Creates a new Criterium object.
	 *
	 * @param name criterion name
	 * @param maxPoints maximum number of points
	 */
	public Criterium(String name, int maxPoints) {
		this.maxPoints = maxPoints;
		this.name = name;
	}
	
	/*
	 * GETTERS
	 */
	
	public String getName() {
		return name;
	}
	
	public int getMaxPoints() {
		return maxPoints;
	}
	
	/*
	 * SETTERS
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}  
}
