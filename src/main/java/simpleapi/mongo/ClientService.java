package simpleapi.mongo;

import java.util.List;

public interface  ClientService {
	 List<Cliente> findAll();

	    Cliente findByDni(String dni);

	    Cliente findByApellido(String email);

	    List<Cliente> findAllByOrderByEdad();

	    void saveOrUpdateClient(Cliente client);

	    void deleteClient(String id);
}
