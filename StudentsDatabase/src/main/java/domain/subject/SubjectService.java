package domain.subject;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import language.LanguageManager;

/*
 * Service responsible for subject management.
 *
 * Handles subject creation, editing, restoring,
 * criteria management and student score management.
 */

public class SubjectService {
	private SubjectRepository subjectRepository;

	/**
	 * Creates SubjectService object and assigns repository.
	 *
	 * @param repository repository used for storing subject data
	 */
	public SubjectService(SubjectRepository repository) {
		this.subjectRepository = repository;
	}

	/**
	 * Creates a new subject object.
	 *
	 * The method creates a subject and saves it in the repository.
	 *
	 * @param name subject name
	 * @return created subject object
	 */
	public Subject create(String name) {
		Subject subject = new Subject(name);
		subjectRepository.save(subject.getId(), subject);

		return subject;
	}

	/**
	 * Adds a new criterion to selected subject.
	 *
	 * @param subjectId subject unique identifier
	 * @param criteriumName criterion name
	 * @param maxPoints maximum number of points for the criterion
	 * @return updated subject object
	 * @throws IllegalArgumentException when subject does not exist
	 */
	public Subject addCriterium(UUID subjectId, String criteriumName, int maxPoints) {
		Subject subject = subjectRepository.get(subjectId);
		
		// Check if subject exists
		if(subject == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.subject.notFound"));
		}
		
		ArrayList<Criterium> criteriaList = subject.getCriteria();
		Criterium criterium = new Criterium(criteriumName, maxPoints);

		// Add new criterion to subject criteria list
		criteriaList.add(criterium);
		subjectRepository.save(subjectId, subject);

		return subject;
	}
	
	/**
	 * Removes criterion from selected subject.
	 *
	 * @param subjectId subject unique identifier
	 * @param criteriumName name of criterion to remove
	 * @return updated subject object
	 * @throws IllegalArgumentException when subject does not exist
	 */
	public Subject removeCriterium(UUID subjectId, String criteriumName) {
		Subject subject = subjectRepository.get(subjectId);
		
		// Check if subject exists
		if(subject == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.subject.notFound"));
		}
		
		ArrayList<Criterium> criteriaList = subject.getCriteria();
		
		// Remove criterion with matching name
		criteriaList.removeIf(criteria -> criteria.getName().equals(criteriumName));
		
		subjectRepository.save(subjectId, subject);
		
		return subject;
	}
	 
	/**
	 * Updates existing criterion data.
	 *
	 * @param subjectId subject unique identifier
	 * @param previousName current criterion name
	 * @param criteriumName new criterion name
	 * @param maxPoints new maximum number of points
	 * @return updated subject object
	 * @throws IllegalArgumentException when subject does not exist
	 */
	public Subject editCriterium(UUID subjectId,String previousName, String criteriumName, int maxPoints) {
		Subject subject = subjectRepository.get(subjectId);
		
		// Check if subject exists
		if(subject == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.subject.notFound"));
		}
		
		ArrayList<Criterium> criteriaList = subject.getCriteria();

		// Find criterion by previous name and update its data
		criteriaList.forEach((criteria) -> {
			if(criteria.getName().equals(previousName)) {
				criteria.setName(criteriumName);
				criteria.setMaxPoints(maxPoints);
			}
		});
		
		subjectRepository.save(subjectId, subject);

		return subject;
	}
	
	/**
	 * Updates subject name.
	 *
	 * @param subjectId subject unique identifier
	 * @param newName new subject name
	 * @return updated subject object
	 * @throws IllegalArgumentException when subject does not exist
	 */
	public Subject editSubjectName(UUID subjectId, String newName) {
		Subject subject = subjectRepository.get(subjectId);
		
		// Check if subject exists
		if(subject == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.subject.notFound"));
		}
		
		// Update subject name
		subject.setName(newName);
		subjectRepository.save(subjectId, subject);
		return subject;
	}
	
	/**
	 * Replaces subject object with a new subject object.
	 *
	 * @param subjectId subject unique identifier
	 * @param newSubject new subject object
	 * @return saved subject object
	 */
	public Subject edit(UUID subjectId, Subject newSubject) {
		subjectRepository.save(subjectId, newSubject);
		return newSubject;
	}
	
	/**
	 * Restores subject object from provided copy.
	 *
	 * @param subjectId subject unique identifier
	 * @param copyToRestore subject copy to restore
	 * @return restored subject object
	 */
	public Subject restore(UUID subjectId, Subject copyToRestore) {
		subjectRepository.save(subjectId, copyToRestore);
		
		return copyToRestore;
	}
	
	/**
	 * Adds student score for selected subject criterion.
	 *
	 * The method checks if subject exists, validates points,
	 * finds maximum points for selected criterion
	 * and adds student score to the score map.
	 *
	 * @param subjectId subject unique identifier
	 * @param studentId student unique identifier
	 * @param points student's obtained points
	 * @param criteriumName criterion name
	 * @return updated subject object
	 * @throws IllegalArgumentException when subject does not exist or points are invalid
	 */
	public Subject addStudentScore(UUID subjectId, UUID studentId, int points, String criteriumName) {
		Subject subject = subjectRepository.get(subjectId);
		
		// Check if subject exists
		if(subject == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.subject.notFound"));
		}
		
		// Validate that points are not negative
		if(points < 0) {
			throw new IllegalArgumentException(LanguageManager.get("error.score.points.negative"));
		}
		
		Map<UUID, ArrayList<CriteriumStudentScore>> subjectStudentsScore = subject.getStudentsScore();
		
		int maxPointsForCriterium = 0;
		
		// Find maximum points for selected criterion
		for (Criterium criterium : subject.getCriteria()) {
		    if (criterium.getName().equals(criteriumName)) {
		        maxPointsForCriterium = criterium.getMaxPoints();
		        break;
		    }
		}
		
		// Validate that points do not exceed criterion maximum
		if(points > maxPointsForCriterium) {
			throw new IllegalArgumentException(LanguageManager.get("error.score.points.exceedsMax"));
			
		}
		
		CriteriumStudentScore studentScore = new CriteriumStudentScore(criteriumName, maxPointsForCriterium, points);
		ArrayList<CriteriumStudentScore> arrayStudentScore = subjectStudentsScore.get(studentId);
		
		// Add new score to student's score list
		arrayStudentScore.add(studentScore);
		subjectStudentsScore.put(studentId, arrayStudentScore);
		
		subjectRepository.save(subjectId, subject);
		return subject;
	}
	
	/**
	 * Updates student score for selected subject criterion.
	 *
	 * @param subjectId subject unique identifier
	 * @param studentId student unique identifier
	 * @param points new student's points
	 * @param criteriumName criterion name
	 * @return updated subject object
	 * @throws IllegalArgumentException when subject does not exist or points are invalid
	 */
	public Subject editStudentScore(UUID subjectId, UUID studentId, int points, String criteriumName) {
		Subject subject = subjectRepository.get(subjectId);
		
		// Check if subject exists
		if(subject == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.subject.notFound"));
		}
		
		// Validate that points are not negative
		if(points < 0) {
			throw new IllegalArgumentException(LanguageManager.get("error.score.points.negative"));
		}
		
		int maxPointsForCriterium = 0;
		
		// Find maximum points for selected criterion
		for (Criterium criterium : subject.getCriteria()) {
		    if (criterium.getName().equals(criteriumName)) {
		        maxPointsForCriterium = criterium.getMaxPoints();
		        break;
		    }
		}
		
		// Validate that points do not exceed criterion maximum
		if(points > maxPointsForCriterium) {
			throw new IllegalArgumentException(LanguageManager.get("error.score.points.exceedsMax"));
			
		}
		
		Map<UUID, ArrayList<CriteriumStudentScore>> subjectStudentsScore = subject.getStudentsScore();
		
		// Find student's score for selected criterion and update points
		for(CriteriumStudentScore studentScore : subjectStudentsScore.get(studentId)) {
			if(studentScore.getName().equals(criteriumName)) {
				studentScore.setStudentPoints(points);
			}
		}
		subjectRepository.save(subjectId, subject);
		return subject;
	}
	
	/**
	 * Removes student score for selected criterion.
	 *
	 * @param subjectId subject unique identifier
	 * @param studentId student unique identifier
	 * @param criteriumName criterion name
	 * @return updated subject object
	 */
	public Subject removeStudentScore(UUID subjectId, UUID studentId, String criteriumName) {
		Subject subject = subjectRepository.get(subjectId);
		Map<UUID, ArrayList<CriteriumStudentScore>> subjectStudentsScore = subject.getStudentsScore();
		ArrayList<CriteriumStudentScore> arrayStudentScore = subjectStudentsScore.get(studentId);
		
		// Remove student's score assigned to selected criterion
		arrayStudentScore.removeIf( (criterium) -> criterium.getName().equals(criteriumName));
	
		subjectRepository.save(subjectId, subject);
		return subject;
	}
}
