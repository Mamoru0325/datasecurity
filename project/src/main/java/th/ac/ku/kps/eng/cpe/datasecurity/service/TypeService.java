package th.ac.ku.kps.eng.cpe.datasecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Type;
import th.ac.ku.kps.eng.cpe.datasecurity.repository.TypeRepository;

@Service
public class TypeService {
	@Autowired
	private TypeRepository typeRepository;
	
	public Type save (Type type) {
		return typeRepository.save(type);
	}
	
	public List<Type> findAll () {
		return (List<Type>) typeRepository.findAll();
	}
	
	public Type findById (int typeId) {
		return typeRepository.findById(typeId).orElse(null);
	}
	
	public void deleteById (int id) {
		typeRepository.deleteById(id);
	}

}
