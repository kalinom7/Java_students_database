package domain.studentsGroup;

import java.util.UUID;

//TODO: implement addStudent method that adds student to group
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
		StudentsGroup studentGroup = studentsGroupRepository.get(id);
		if(studentGroup == null) {
			throw new Exception("student group not found");
		}
		return studentGroup;
	}
	
	public StudentsGroup edit(UUID id, String specialization, String groupCode, String description) {
		StudentsGroup studentsGroup;
		try {
			studentsGroup = this.get(id);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
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
