package simpleapi;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.BDDMockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ch.qos.logback.core.net.ObjectWriter;
import simpleapi.mongo.ClientRepository;
import simpleapi.mongo.ClientService;
import simpleapi.mongo.Cliente;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ControllerDemoTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClientService clientService;

	private ObjectMapper objectMapper = new ObjectMapper();

	private Cliente ragcrix;
	private Cliente yigit;

	private final String ragcrixClientDni = "aBc123";
	private final String yigitClientDni = "aBc124";

	private final String ragcrixApellido = "Juarez";
	private final String yigitApellido = "Pucheta";
	private final List<Cliente> clients = new ArrayList<>();

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

	}

	@Test
	public void givenStudents_whenGetAllStudents_thenReturnJsonArray() throws Exception {
		given(clientService.findAll()).willReturn(Arrays.asList(ragcrix));

		mvc.perform(get("/getAllClients").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201))
				.andExpect(jsonPath("$[0].nombre", is(ragcrix.getNombre())));
	}

	@Test
	public void givenIdToDelete_ReturnSucces() throws Exception {

		Mockito.doNothing().when(clientService).deleteClient(any(String.class));

		mvc.perform(delete("/deleteClient/?dni=", yigitClientDni).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void givenClientToSave_ReturnSucces() throws Exception {

		Mockito.doNothing().when(clientService).saveOrUpdateClient(yigit);

		mvc.perform(post("/createClient/", yigit).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(yigit))).andExpect(status().isOk());

	}

}
