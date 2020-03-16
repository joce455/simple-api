package simpleapi.mongo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Cliente")
public class Cliente {
	@Id
	private String dni;
	private int edad;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;

	public Cliente() {
		this.dni="";
		this.edad = 0;
		this.nombre = null;
		this.apellido = null;
		this.fechaNacimiento = null;

	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Cliente(String dni,int edad, String nombre, String apellido, LocalDate fechaNacimiento) {
		this.dni=dni;
		this.edad = edad;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;

	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}