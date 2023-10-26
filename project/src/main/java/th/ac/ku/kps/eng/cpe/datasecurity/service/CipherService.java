package th.ac.ku.kps.eng.cpe.datasecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.ac.ku.kps.eng.cpe.datasecurity.model.Cipher;
import th.ac.ku.kps.eng.cpe.datasecurity.repository.CipherRepository;

@Service
public class CipherService {
	@Autowired
	private CipherRepository cipherRepository;
	
	public Cipher save (Cipher cipher) {
		return cipherRepository.save(cipher);
	}
	
	public List<Cipher> findAll (){
		return (List<Cipher>) cipherRepository.findAll();
	}
	
	public Cipher findById (int cipherId) {
		return cipherRepository.findById(cipherId).orElse(null);
	}
	
	public void deleteById (int id) {
		cipherRepository.deleteById(id);
	}
	
	public List<Cipher> find10RandomQuestion (String level) {
		return cipherRepository.find10RandomQuestion(level);
	}
	
	public List<Cipher> findByLevel (String level) {
		return cipherRepository.findByLevel(level);
	}

}
