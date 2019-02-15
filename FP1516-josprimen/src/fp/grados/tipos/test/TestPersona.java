package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionPersonaNoValida;
import fp.grados.tipos.Persona;
import fp.grados.tipos.PersonaImpl;

public class TestPersona {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();
		testConstructor1Excepcional4();
		testConstructor1Excepcional5();

		testSetDNINormal();
		testSetDNIExcepcional1();
		testSetDNIExcepcional2();
		testSetDNIExcepcional3();
		
		testSetEmailNormal();
		testSetEmailExcepcional();
		
		testIgualdad();
		testOrden();
	

		testConstructor2Normal();
		testConstructor2Excepcional1();
		testConstructor2Excepcional2();
		testConstructor2Excepcional3();		
		
		testSetFechaNacimientoNormal();
		testSetFechaNacimientoExcepcional();
	}

	// Casos de prueba
	
	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1("12345678Z","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional1() {
		System.out
				.println("==================================Probando el primer constructor con dni sin letra");
		testConstructor1("123456789","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional2() {
		System.out
				.println("==================================Probando el primer constructor con dni de longitud menor de la esperada");
		testConstructor1("1234567X","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional3() {
		System.out
				.println("==================================Probando el primer constructor con letra en dni que no se corresponde a los dígitos");
		testConstructor1("12345678X","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional4() {
		System.out
				.println("==================================Probando el primer constructor con email sin arroba");
		testConstructor1("12345678Z","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadiegmail.com");
	}
	
	private static void testConstructor1Excepcional5() {
		System.out
				.println("==================================Probando el primer constructor con fecha de nacimiento posterior a la actual");
		testConstructor1("12345678Z","Juan", "Nadie Nadie", 
				LocalDate.of(2030, 3, 15), "juan.nadie@gmail.com");
	}
	
	private static void testSetDNINormal(){
		System.out
		.println("==================================Probando setDNI");
			Persona p = new PersonaImpl("12345678Z","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
			testSetDNI(p, "12345677J");	
	}
	
	private static void testSetDNIExcepcional1(){
		System.out
		.println("==================================Probando setDNI con dni sin letra");
		
		Persona p = new PersonaImpl("12345678Z","Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "123456779");	
	}

	private static void testSetDNIExcepcional2(){
		System.out
		.println("==================================Probando setDNI con dni de longitud menor de la esperada");
		
		Persona p = new PersonaImpl("12345678Z","Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677");	
	}
	

	private static void testSetDNIExcepcional3(){
		System.out
		.println("==================================Probando setDNI con letra en dni que no se corresponde a los dígitos");

		Persona p = new PersonaImpl( "12345678Z","Juan", "Nadie Nadie",
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677X");	
	}
	
	private static void testSetEmailNormal(){
		System.out
		.println("==================================Probando setEmail");

		Persona p = new PersonaImpl("12345678Z","Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "juan@us.es");	
	}

	private static void testSetEmailExcepcional(){
		System.out
		.println("==================================Probando setEmail con email sin arroba");

		Persona p = new PersonaImpl("12345678Z","Juan", "Nadie Nadie", 
			LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetEmail(p, "juan.us.es");	
	}
	
	private static void testConstructor2Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor2("12345678Z","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	
	}
	
	private static void testConstructor2Excepcional1() {
		System.out
				.println("==================================Probando el primer constructor con dni sin letra");
		testConstructor2("123456789","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional2() {
		System.out
				.println("==================================Probando el primer constructor con dni de longitud menor de la esperada");
		testConstructor2("1234567X","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}

	private static void testConstructor2Excepcional3() {
		System.out
				.println("==================================Probando el primer constructor con letra en dni que no se corresponde a los dígitos");
		testConstructor2("12345678X","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
	}
	
	private static void testSetFechaNacimientoNormal() {
	
		System.out.println("---//Probando el setFechaNacimiento()\\---");
		
		Persona p = new PersonaImpl("12345678Z","Juan", "Nadie Nadie", 
				LocalDate.of(1950, 3, 15));
		
		testFechaNacimiento(p, LocalDate.of(1980, 5, 12) );
		
		
	}
	
	
	private static void testSetFechaNacimientoExcepcional() {
		
		System.out.println("---//Probando el setFechaNacimiento()\\---");
		
		Persona p = new PersonaImpl("12345678Z","Juan", "Nadie Nadie", 
				LocalDate.of(1980, 5, 12));
		
		testFechaNacimiento(p, LocalDate.of(2030, 5, 12) );
	}
	
	//Probando igualdad y orden
	
	private static void testIgualdad() {
		System.out.println("\n===========Probando igualdad con dos objetos iguales");
		// Dos objetos iguales
		Persona p1 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
		LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		Persona p2 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
		LocalDate.of(1980, 6, 25), "juan.nadie2@gmail.com");
		// Creamos objetos distintos (distinto dni, distinto nombre, distintos
		// apellidos)
		Persona p3 = new PersonaImpl("12345677J", "Juan", "Nadie Nadie",
		LocalDate.of(1952, 1, 10), "nadie@gmail.com");
		Persona p4 = new PersonaImpl("12345678Z", "Antonio", "Nadie Nadie",
		LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		Persona p5 = new PersonaImpl("12345678Z", "Juan", "Otros Apellidos",
		LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		
		System.out.println("Código hash del objeto p1 (" + p1 + "): " + p1.hashCode());
		System.out.println("Código hash del objeto p2 (" + p2 + "): " + p2.hashCode());
		System.out.println("Código hash del objeto p3 (" + p3 + "): " + p3.hashCode());
		System.out.println("Código hash del objeto p4 (" + p4 + "): " + p4.hashCode());
		System.out.println("Código hash del objeto p5 (" + p5 + "): " + p5.hashCode());
		System.out.println("¿Es p1 igual a p2? (debe ser true): " + p1.equals(p2));
		System.out.println("¿Es p1 distinto de p3? (debe ser true): " + !p1.equals(p3));
		System.out.println("¿Es p1 distinto de p4? (debe ser true): " + !p1.equals(p4));
		System.out.println("¿Es p1 distinto de p5? (debe ser true): " + !p1.equals(p5));
		}
	
	private static void testOrden() {
		System.out.println("\n============Probando orden natural");
		
		Persona menor = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
		LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		Persona igual1 = new PersonaImpl("12345677J", "Juana", "Nadie Nadie",
		LocalDate.of(1980, 7, 13));
		Persona igual2 = new PersonaImpl("12345677J", "Juana", "Nadie Nadie",
		LocalDate.of(1980, 7, 13));
		Persona mayor = new PersonaImpl("12345679S", "Juana", "Nadie Nadie",
		LocalDate.of(1980, 7, 13));
		System.out.print("(debe ser ANTES) ");
		compara(menor,igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1,igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2,mayor);
	}
	
	// Métodos auxiliares
	
	private static void testConstructor1(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {

		try {
			Persona p = new PersonaImpl(dni,nombre, apellidos, 
					fechaNacimiento, email);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}	

	private static void testSetDNI(Persona p, String nuevoDNI) {

		try {
			System.out.println("El dni antes de la operación es: "+  p.getDNI());
			System.out.println("El nuevo dni es: "+  nuevoDNI);
			p.setDNI(nuevoDNI);
			System.out.println("El dni después de la operación es: "+  p.getDNI());
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}

	private static void testSetEmail(Persona p, String nuevoEmail) {

		try {
			System.out.println("El email antes de la operación es: "+  p.getEmail());
			System.out.println("El nuevo email es: "+  nuevoEmail);
			p.setEmail(nuevoEmail);
			System.out.println("El email después de la operación es: "+  p.getEmail());
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}
	
private static void testConstructor2(String dni, String nombre, String apellidos, LocalDate fechaNacimiento) {
		
		
		try {
			Persona p = new PersonaImpl(dni,nombre, apellidos, 
					fechaNacimiento);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
		
		
	}



private static void testFechaNacimiento(Persona p, LocalDate nuevaFechaNacimiento) {
		
		try {
			System.out.println("La nueva fechade nacimiento antes de la operación es: "+  p.getFechaNacimiento());
			System.out.println("La nueva fechade nacimiento es: "+  nuevaFechaNacimiento);
			p.setFechaNacimiento(nuevaFechaNacimiento);
			System.out.println("El email después de la operación es: "+  p.getFechaNacimiento());
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}
	
	private static void mostrarPersona(Persona p) {
		System.out.println("Persona --> <" + p + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ p.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
	}
	
	// Métodos auxiliares de igualdad
	
	private static void compara(Persona p1, Persona p2) {
	System.out.print("El objeto <" + p1 + ">");
	if (p1.compareTo(p2) < 0) {
	System.out.print(" va ANTES que el objeto ");
	} else if (p1.compareTo(p2) > 0) {
	System.out.print(" va DESPUÉS que el objeto ");
	} else {
	System.out.print(" va en la MISMA POSICIÓN que el objeto ");
	}
	System.out.println("<" + p2 + ">");
	}
	
}
