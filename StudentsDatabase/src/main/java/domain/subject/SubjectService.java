package domain.subject;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.javatuples.Pair;

import language.LanguageManager;

public class SubjectService {
	private SubjectRepository subjectRepository;

	public SubjectService(SubjectRepository repository) {
		this.subjectRepository = repository;
	}

	public Subject create(String name) {
		Subject subject = new Subject(name);
		subjectRepository.save(subject.getId(), subject);

		return subject;
	}

	public Subject addCriterium(UUID subjectId, String criteriumName, int maxPoints) throws Exception {
		Subject subject = subjectRepository.get(subjectId);
		if(subject == null) {
			throw new Exception(LanguageManager.get("error.subject.notFound"));
		}
		
		ArrayList<Criterium> criteriaList = subject.getCriteria();
		Criterium criterium = new Criterium(criteriumName, maxPoints);

		criteriaList.add(criterium);
		subjectRepository.save(subjectId, subject);

		return subject;
	}
	
	public Subject removeCriterium(UUID subjectId, String criteriumName) throws Exception {
		Subject subject = subjectRepository.get(subjectId);
		if(subject == null) {
			throw new Exception(LanguageManager.get("error.subject.notFound"));
		}
		
		ArrayList<Criterium> criteriaList = subject.getCriteria();
		
		criteriaList.removeIf(criteria -> criteria.getName().equals(criteriumName));
		
		subjectRepository.save(subjectId, subject);
		
		return subject;
	}
	 
	public Subject editCriterium(UUID subjectId,String previousName, String criteriumName, int maxPoints) throws Exception {
		Subject subject = subjectRepository.get(subjectId);
		if(subject == null) {
			throw new Exception(LanguageManager.get("error.subject.notFound"));
		}
		
		ArrayList<Criterium> criteriaList = subject.getCriteria();

		criteriaList.forEach((criteria) -> {
			if(criteria.getName().equals(previousName)) {
				criteria.setName(criteriumName);
				criteria.setMaxPoints(maxPoints);
			}
		});
		
		subjectRepository.save(subjectId, subject);

		return subject;
	}
	
	public Subject editSubjectName(UUID subjectId, String newName) throws Exception {
		Subject subject = subjectRepository.get(subjectId);
		if(subject == null) {
			throw new Exception(LanguageManager.get("error.subject.notFound"));
		}
		
		subject.setName(newName);
		subjectRepository.save(subjectId, subject);
		return subject;
	}
	
	public Subject edit(UUID subjectId, Subject newSubject) {
		subjectRepository.save(subjectId, newSubject);
		return newSubject;
	}
	
	public Subject restore(UUID subjectId, Subject copyToRestore) {
		subjectRepository.save(subjectId, copyToRestore);
		
		return copyToRestore;
	}
	public Subject addStudentScore(UUID subjectId, UUID studentId, int points, String criteriumName) throws Exception {
		Subject subject = subjectRepository.get(subjectId);
		if(subject == null) {
			throw new Exception(LanguageManager.get("error.subject.notFound"));
		}
		if(points < 0) {
			throw new Exception(LanguageManager.get("error.score.points.negative"));
		}
		
		Map<UUID, ArrayList<CriteriumStudentScore>> subjectStudentsScore = subject.getStudentsScore();
		
		int maxPointsForCriterium = 0;
		for (Criterium criterium : subject.getCriteria()) {
		    if (criterium.getName().equals(criteriumName)) {
		        maxPointsForCriterium = criterium.getMaxPoints();
		        break;
		    }
		}
		
		if(points > maxPointsForCriterium) {
			throw new Exception(LanguageManager.get("error.studentScore.greater.than.maxPoints"));
			
		}
		
		CriteriumStudentScore studentScore = new CriteriumStudentScore(criteriumName, maxPointsForCriterium, points);
		ArrayList<CriteriumStudentScore> arrayStudentScore = subjectStudentsScore.get(studentId);
		arrayStudentScore.add(studentScore);
		subjectStudentsScore.put(studentId, arrayStudentScore);
		
		subjectRepository.save(subjectId, subject);
		return subject;
	}
	
	public Subject editStudentScore(UUID subjectId, UUID studentId, int points, String criteriumName) throws Exception {
		Subject subject = subjectRepository.get(subjectId);
		if(subject == null) {
			throw new Exception(LanguageManager.get("error.subject.notFound"));
		}
		if(points < 0) {
			throw new Exception(LanguageManager.get("error.score.points.negative"));
		}
		int maxPointsForCriterium = 0;
		for (Criterium criterium : subject.getCriteria()) {
		    if (criterium.getName().equals(criteriumName)) {
		        maxPointsForCriterium = criterium.getMaxPoints();
		        break;
		    }
		}
		if(points > maxPointsForCriterium) {
			throw new Exception(LanguageManager.get("error.studentScore.greater.than.maxPoints"));
			
		}
		Map<UUID, ArrayList<CriteriumStudentScore>> subjectStudentsScore = subject.getStudentsScore();
		for(CriteriumStudentScore studentScore : subjectStudentsScore.get(studentId)) {
			if(studentScore.getName().equals(criteriumName)) {
				studentScore.setStudentPoints(points);
			}
		}
		subjectRepository.save(subjectId, subject);
		return subject;
	}
	public Subject removeStudentScore(UUID subjectId, UUID studentId, String criteriumName) {
		Subject subject = subjectRepository.get(subjectId);
		Map<UUID, ArrayList<CriteriumStudentScore>> subjectStudentsScore = subject.getStudentsScore();
		ArrayList<CriteriumStudentScore> arrayStudentScore = subjectStudentsScore.get(studentId);
		
		arrayStudentScore.removeIf( (criterium) -> criterium.getName().equals(criteriumName));
	
		subjectRepository.save(subjectId, subject);
		return subject;
	}
}
