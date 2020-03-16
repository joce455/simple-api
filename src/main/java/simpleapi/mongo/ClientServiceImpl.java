package simpleapi.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{

	
	@Autowired
	private ClientRepository clientRepository;
	 
	@Override
	public List<Cliente> findAll() {
		 return clientRepository.findAll();
	}

	@Override
	public Cliente findByDni(int dni) {
		return clientRepository.findByDni(dni);
	}

	@Override
	public Cliente findByApellido(String apellido) {
		return clientRepository.findByApellido(apellido);
	}

	@Override
	public List<Cliente> findAllByOrderByEdad() {
		return clientRepository.findAllByOrderByEdad();
		
	}

	@Override
	public void saveOrUpdateClient(Cliente client) {
		clientRepository.save(client);
	}

	@Override
	public void deleteClient(int id) {
		
		clientRepository.deleteById(String.valueOf(id));
		
	}

}
