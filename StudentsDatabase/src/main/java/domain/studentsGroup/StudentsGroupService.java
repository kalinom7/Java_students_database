package domain.studentsGroup;


import java.util.Set;
import java.util.UUID;
import language.LanguageManager;


public class StudentsGroupService {
	private StudentsGroupRepository studentsGroupRepository;
	
	public StudentsGroupService(StudentsGroupRepository repository) {
		this.studentsGroupRepository = repository;
	}
	
	public StudentsGroup create(String specialization, String groupCode, String description) throws IllegalArgumentException {
		if (specialization.trim().isEmpty() || specialization.isBlank() || groupCode.trim().isEmpty() || groupCode.isBlank()
				|| description.trim().isEmpty() || description.isBlank()) {
			throw new IllegalArgumentException(LanguageManager.get("invalid.group.create.data"));
		}
		StudentsGroup studentsGroup = new StudentsGroup(specialization, groupCode, description);
		UUID id = studentsGroup.getId();
		
		studentsGroupRepository.save(id, studentsGroup);
		return studentsGroup;
	}
	
	public StudentsGroup get(UUID id) throws Exception {
		StudentsGroup studentsGroup = studentsGroupRepository.get(id);
		if(studentsGroup == null) {
			throw new Exception(LanguageManager.get("error.group.notFound"));
		}
		
		return studentsGroup;
	}
	
	public StudentsGroup addStudent(UUID groupId, UUID studentId) throws IllegalArgumentException {
		StudentsGroup studentsGroup = studentsGroupRepository.get(groupId);
		if(studentsGroup == null || studentId == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.group.notFound"));
		}
		
		studentsGroup.getStudentsInGroup().add(studentId);
		studentsGroupRepository.save(groupId, studentsGroup);
		
		return studentsGroup;
	}
	
	public StudentsGroup edit(UUID id, String specialization, String groupCode, String description) throws IllegalArgumentException {
		StudentsGroup studentsGroup = studentsGroupRepository.get(id);
		if(studentsGroup == null || specialization == null || groupCode == null || description == null) {
			throw new IllegalArgumentException(LanguageManager.get("error.group.notFound"));
		}
		if (specialization.trim().isEmpty() || specialization.isBlank() || groupCode.trim().isEmpty() || groupCode.isBlank()
				|| description.trim().isEmpty() || description.isBlank()) {
			throw new IllegalArgumentException(LanguageManager.get("invalid.group.edit.data"));
		}
	
		studentsGroup.setDescription(description);
		studentsGroup.setGroupCode(groupCode);
		studentsGroup.setSpecialization(specialization);
			
		studentsGroupRepository.save(id, studentsGroup);
		return studentsGroup;
	}
	
	public void removeStudent(UUID studentId, UUID groupId) throws Exception {
		StudentsGroup studentsGroup = studentsGroupRepository.get(groupId);
		if(!studentsGroup.getStudentsInGroup().remove(studentId)) {
			throw new Exception(LanguageManager.get("error.studentRemove.in.group.not.found"));
		}
		studentsGroupRepository.save(groupId, studentsGroup);
	}
	
	public void delete(UUID id) {
		studentsGroupRepository.delete(id);
	}
	
}
