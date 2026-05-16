package domain.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.javatuples.Pair;

import domain.student.StudentRepository;


public class Subject {
	private String name;
	private UUID id;
	/**
	 * criteria is an array of criterium Pair<String,Integer> represents single
	 * criterium where string is the name of the criterium for example test1 and
	 * Integer is max points for the criterium
	 */
	private ArrayList<Criterium> criteria;
	
	private Map<UUID, ArrayList<CriteriumStudentScore>> studentsScore;

	public Subject(String name) {
		this.name = name;
		this.id =  UUID.randomUUID();
		this.criteria = new  ArrayList<>();
		this.studentsScore = new HashMap<>();
	}

	public String getName() {
		return name;
	}
	public UUID getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Criterium> getCriteria() {
		return criteria;
	}

	
	public Map<UUID, ArrayList<CriteriumStudentScore>> getStudentsScore() {
		return studentsScore;
	}

	
}
