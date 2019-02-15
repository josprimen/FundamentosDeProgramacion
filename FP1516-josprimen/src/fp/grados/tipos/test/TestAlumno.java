package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestAlumno {

	public static void main(String[] args) {

		testConstructorNormal();
		testConstructorExcepcion();

		testSetEmailNormal();
		testSetEmailExcepcion();

		testMatriculaAsignatura();
		testMatriculaAsignaturaExcepcion();

		testEliminaAsignatura();
		testEliminaAsignaturaExcepcion();
		
		testGetCurso1();
		testGetCurso2();
		
		testEvaluaAlumno();
		

	}
	//**
	private static void testEvaluaAlumno() {
		System.out
				.println("\n==================================Probando eliminaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Departamento d = new DepartamentoImpl("LSI");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programaci�n",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, d);
		a.matriculaAsignatura(asig);
		a.evaluaAlumno(asig, 2014, Convocatoria.SEGUNDA, 7.25);
	}
	//**

	private static void testConstructorNormal() {
		System.out
				.println("\n====Probando el primer constructor");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	}

	private static void testConstructorExcepcion() {
		System.out
				.println("\n====Probando el primer constructor, email incorrecto");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testSetEmailNormal() {
		System.out
				.println("\n====Probando setEmail");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@alum.us.es");
	}

	private static void testSetEmailExcepcion() {
		System.out
				.println("\n====Probando setEmail, email incorrecto");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@gmail.com");
	}

	private static void testMatriculaAsignatura() {
		System.out
				.println("\n====Probando matriculaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programaci�n",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1);
		testMatriculaAsignatura(a, asig);
	}

	private static void testMatriculaAsignaturaExcepcion() {
		System.out
				.println("\n====Probando matriculaAsignatura, matricula doble en una asignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programaci�n",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1);
		a.matriculaAsignatura(asig);
		testMatriculaAsignatura(a, asig);
	}

	private static void testEliminaAsignatura() {
		System.out
				.println("\n====Probando eliminaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programaci�n",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1);
		a.matriculaAsignatura(asig);
		testEliminaAsignatura(a, asig);
	}

	private static void testEliminaAsignaturaExcepcion() {
		System.out
				.println("\n====Probando eliminaAsignatura, asignatura no matriculada");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programaci�n",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1);
		testEliminaAsignatura(a, asig);
	}

	private static void testConstructor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		try {
			Alumno a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento,
					email);
			mostrarAlumno(a);
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("****** Se ha capturado la excepci�n ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out
					.println("****** Se ha capturado una excepci�n inesperada.");
		}

	}

	private static void testSetEmail(Alumno a, String nuevoEmail) {

		try {
			System.out.println("El email antes de la operaci�n es: "
					+ a.getEmail());
			System.out.println("El nuevo email es: " + nuevoEmail);
			a.setEmail(nuevoEmail);
			System.out.println("El email despu�s de la operaci�n es: "
					+ a.getEmail());
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("****** Se ha capturado la excepci�n ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out
					.println("****** Se ha capturado una excepci�n inesperada.");
		}
	}

	private static void testMatriculaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operaci�n son: "
					+ a.getAsignaturas());
			System.out.println("Nueva asignatura a matricular: " + asig);
			a.matriculaAsignatura(asig);
			System.out.println("Las asignaturas despu�s de la operaci�n son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("****** Se ha capturado la excepci�n ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("****** Se ha capturado una excepci�n inesperada.");
		}
	}

	private static void testEliminaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operaci�n son: "
					+ a.getAsignaturas());
			System.out.println("Asignatura a eliminar: " + asig);
			a.eliminaAsignatura(asig);
			System.out.println("Las asignaturas despu�s de la operaci�n son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("****** Se ha capturado la excepci�n ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("****** Se ha capturado una excepci�n inesperada.");
		}
	}
	
	//Test T8
	
	private static void testGetCurso1() {
		System.out.println("\n====Probando el m�todo getCurso para un alumno con asignaturas matriculadas");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos", "2050010", 12.0,
				TipoAsignatura.ANUAL, 2);
		a.matriculaAsignatura(fp);
		a.matriculaAsignatura(adda);
		mostrarAlumno(a);
		System.out.println("El curso de este alumno es: " + a.getCurso());
	}

	private static void testGetCurso2() {
		System.out.println("\n====Probando el m�todo getCurso para un alumno sin asignaturas matriculadas");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		mostrarAlumno(a);
		System.out.println("El curso de este alumno es: " + a.getCurso());
	}

	private static void mostrarAlumno(Alumno a) {
		System.out.println("Alumno --> <" + a + ">");
		System.out.println("\tDNI: <" + a.getDNI() + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tApellidos: <" + a.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ a.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + a.getEdad() + ">");
		System.out.println("\tEmail:  <" + a.getEmail() + ">");
		System.out.println("\tAsignaturas:  <" + a.getAsignaturas() + ">");
	}
}
