package domain.studentsGroup;


import java.util.Set;
import java.util.UUID;
import language.LanguageManager;


public class StudentsGroupService {
	private StudentsGroupRepository studentsGroupRepository;
	
	public StudentsGroupService(StudentsGroupRepository repository) {
		this.studentsGroupRepository = repository;
	}
	
	public StudentsGroup create(String specialization, String groupCode, String description) {
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
	
	public StudentsGroup addStudent(UUID groupId, UUID studentId) throws Exception {
		StudentsGroup studentsGroup = studentsGroupRepository.get(groupId);
		if(studentsGroup == null) {
			throw new Exception(LanguageManager.get("error.group.notFound"));
		}
		
		studentsGroup.getStudentsInGroup().add(studentId);
		studentsGroupRepository.save(groupId, studentsGroup);
		
		return studentsGroup;
	}
	
	public StudentsGroup edit(UUID id, String specialization, String groupCode, String description) throws Exception {
		StudentsGroup studentsGroup = studentsGroupRepository.get(id);
		if(studentsGroup == null) {
			throw new Exception(LanguageManager.get("error.group.notFound"));
		}
	
		studentsGroup.setDescription(description);
		studentsGroup.setGroupCode(groupCode);
		studentsGroup.setSpecialization(specialization);
			
		studentsGroupRepository.save(id, studentsGroup);
		return studentsGroup;
	}
	
	public void delete(UUID id) {
		studentsGroupRepository.delete(id);
	}
	
}
