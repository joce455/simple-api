package simpleapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import simpleapi.mongo.ClientRepository;
import simpleapi.mongo.ClientService;
import simpleapi.mongo.Cliente;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	private Cliente ragcrix;
	private Cliente yigit;

	private final String ragcrixClientDni = "aBc123";
	private final String yigitClientDni = "aBc124";

	private final String ragcrixApellido = "Juarez";
	private final String yigitApellido = "Pucheta";
	private final List<Cliente> clients = new ArrayList<>();

	@Autowired
	private ClientService clientService;

	@MockBean
	private ClientRepository clientRepository;

	@Before
	public void setup() {
		ragcrix = new Cliente();
		ragcrix.setDni(ragcrixClientDni);
		ragcrix.setNombre("ragcrix");
		ragcrix.setEdad(28);
		ragcrix.setApellido(ragcrixApellido);

		yigit = new Cliente();
		yigit.setDni(yigitClientDni);
		yigit.setNombre("yigit");
		yigit.setApellido(yigitApellido);
		yigit.setEdad(31);
		clients.add(ragcrix);
		clients.add(yigit);

		Mockito.when(clientRepository.findAll()).thenReturn(clients);
		Mockito.when(clientRepository.findByDni(ragcrixClientDni)).thenReturn(ragcrix);
		Mockito.when(clientRepository.findAllByOrderByEdad()).thenReturn(clients.stream()
				.sorted(Comparator.comparing(Cliente::getEdad).reversed()).collect(Collectors.toList()));
		
		
	}

	@Test
	public void testFindAll_thenStudentListShouldBeReturned() {
		List<Cliente> foundClients = clientService.findAll();

		assertNotNull(foundClients);
		assertEquals(2, foundClients.size());

	}

	@Test
	public void testFindByDni_thenRagcrixShouldBeReturned() {
		Cliente found = clientService.findByDni(ragcrixClientDni);
		assertNotNull(found);
		assertEquals(ragcrix.getNombre(), found.getNombre());
		assertEquals(ragcrix.getDni(), found.getDni());
	}

	@Test
	public void findAllByOrderByEdad() {
		List<Cliente> foundClients = clientService.findAllByOrderByEdad();
		assertNotNull(foundClients);
		assertEquals(2, foundClients.size());
		assertEquals(yigit.getEdad(), foundClients.get(0).getEdad());
		assertEquals(yigit.getDni(), foundClients.get(0).getDni());
	}
	
	@Test
	public void testDeleteStudentById() {
		clientService.deleteClient(ragcrix.getDni());
		 Mockito.verify(clientRepository, Mockito.times(1)).deleteById(ragcrix.getDni());
	   
	}
	

}
