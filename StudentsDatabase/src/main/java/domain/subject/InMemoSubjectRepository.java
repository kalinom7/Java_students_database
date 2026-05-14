package domain.subject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class InMemoSubjectRepository implements SubjectRepository{
	private Map<UUID, Subject> subjects = new HashMap<>();
	
	@Override
	public void save(UUID id, Subject subject) {
		subjects.put(id, subject);
	}

	@Override
	public Subject get(UUID id) {
		Subject subject = subjects.get(id);
		return subject;
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
