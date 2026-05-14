package domain.subject;

public class Criterium {
	private String name;
	private int maxPoints;
	
	public Criterium(String name, int maxPoints) {
		this.maxPoints = maxPoints;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}  
}
