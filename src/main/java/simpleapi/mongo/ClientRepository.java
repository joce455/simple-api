package simpleapi.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository <Cliente, String>{
	Cliente findByDni(String dni);

	Cliente findByApellido(String apellido);

	List<Cliente> findAllByOrderByEdad();
}
