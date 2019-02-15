package fp.grados.tipos.test;

//Emocionata

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionDepartamentoNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestDepartamento {
	
	public static void main(String[] args) {

		testExisteProfesorAsignado();
		testConstructor1Normal();
		testAsignaturaNormal();
		testProfesorNormal();
		
		testConstructorNormal();
		testRelacionBidireccionalAsignaturas();
		testIgualdad();
		testOrden();
		
		testBorraTutoriasCategoria();
		testExisteProfesorAsignado();


	}
	
	
	private static void testConstructor1Normal() {
	
		System.out
				.println("\n==================================Probando el primer constructor");
		testConstructor1("LSI");
	}
	
	private static void testAsignaturaNormal() {
		
		System.out
				.println("\n==================================Probando a�adir asignatura");
		testAsignatura();
	}
	
	private static void testProfesorNormal() {
		
		System.out
				.println("\n==================================Probando a�adir profesor");
		testProfesor();
	}
	
	private static void testConstructor1(String nombre) {

		try {
			Departamento d = new DepartamentoImpl(nombre);
			mostrarDepartamento(d);
		} catch (ExcepcionDepartamentoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionDepartamentoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepci�n inesperada.");
		}

	}
	
	private static void testAsignatura() {

		try {
			Departamento d = new DepartamentoImpl("LSI");
			Asignatura asig = new AsignaturaImpl("FP", "2415687", 12., TipoAsignatura.ANUAL, 1, d);
			d.nuevaAsignatura(asig);
			mostrarDepartamento(d);
		} catch (ExcepcionDepartamentoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionDepartamentoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepci�n inesperada.");
		}

	}
	
	private static void testProfesor() {

		try {
			Departamento d = new DepartamentoImpl("LSI");
			Profesor p = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, d);
			d.nuevoProfesor(p);
			mostrarDepartamento(d);
		} catch (ExcepcionDepartamentoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepci�n ExcepcionDepartamentoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepci�n inesperada.");
		}

	}
	
	private static void mostrarDepartamento(Departamento d) {
		System.out.println("Departamento --> <" + d + ">");
		System.out.println("\tNombre: <" + d.getNombre() + ">");
		System.out.println("\tAsignaturas: <" + d.getAsignaturas() + ">");
		System.out.println("\tProfesores: <" + d.getProfesores() + ">");
	}
//*******
	private static void testConstructorNormal() {
		System.out
				.println("==================================Probando el primer constructor");
		Departamento d = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		mostrarDepartamento(d);
	}
	
	private static void testRelacionBidireccionalAsignaturas() {
		System.out.println("\n==================================Probando la "
				+ "relaci�n bidireccional entre Departamento y Asignatura");
		Departamento lsi = new DepartamentoImpl(
				"Lenguajes y Sistemas Inform�ticos");
		Departamento ccia = new DepartamentoImpl(
				"Ciencias de la Computaci�n e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programaci�n",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, lsi);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		
		System.out.println("\n****A�adimos la asignatura FP al departamento CCIA, "
			+ "mediante la operaci�n nuevaAsignatura del tipo Departamento:");
		ccia.nuevaAsignatura(fp);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);

		System.out.println("\n****Cambiamos al departamento LSI la asignatura FP, "
			+ "mediante la operaci�n setDepartamento del tipo Asignatura:");
		fp.setDepartamento(lsi);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		
		System.out.println("\n****Eliminamos del departamento LSI la asignatura FP, "
			+ "mediante la operaci�n eliminaAsignatura del tipo Departamento:");

		lsi.eliminaAsignatura(fp);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		
	}
	
	private static void testIgualdad() {
		System.out.println("\n===============================Probando igualdad con dos objetos iguales");		
		Departamento d1 = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Departamento d2 = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");		
		Departamento d3 = new DepartamentoImpl("Arquitectura y Tecnolog�a de Computadores");

		System.out.println("C�digo hash del objeto d1 (" + d1 + "): " + d1.hashCode());
		System.out.println("C�digo hash del objeto d2 (" + d2 + "): " + d2.hashCode());
		System.out.println("C�digo hash del objeto d3 (" + d3 + "): " + d3.hashCode());
		
		System.out.println("�Es d1 igual a d2? (debe ser true): " + d1.equals(d2));
		System.out.println("�Es d1 distinto de d3? (debe ser true): " + !d1.equals(d3));
	}

	// Nuevo en T4:
	private static void testOrden() {
		System.out.println("\n===============================Probando orden natural");
		// Creamos cuatro objetos tales que menor < igual1 == igual2 < mayor
		Departamento menor = new DepartamentoImpl("Arquitectura y Tecnolog�a de Computadores");
		Departamento igual1 = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Departamento igual2 = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");
		Departamento mayor = new DepartamentoImpl("Tecnolog�a Electr�nica");

		System.out.print("(debe ser ANTES) ");
		compara(menor,igual1);
		System.out.print("(debe ser MISMA POSICI�N) ");
		compara(igual1,igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2,mayor);
	}
	
	// M�todos auxiliares
	// Nuevo en T4:
	private static void compara(Departamento d1, Departamento d2) {
		System.out.print("El objeto <" + d1 + ">");
		if (d1.compareTo(d2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (d1.compareTo(d2) > 0) {
			System.out.print(" va DESPU�S que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICI�N que el objeto ");
		}
		System.out.println("<" + d2 + ">");
	}

	
	private static void mostrarAsignatura(Asignatura a) {
		System.out.println("Asignatura --> <" + a + ">");
		System.out.println("\tDepartamento: <" + a.getDepartamento() + ">");
	}

	//******
	private static void testExisteProfesorAsignado() {
		System.out.println("==================================Probando existeProfesorAsignado");
		Departamento d = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");

		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor p2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor p3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);
		d.nuevoProfesor(p1);
		d.nuevoProfesor(p2);
		d.nuevoProfesor(p3);

		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Dise�o de Datos y Algoritmos", "2050010", 12.0,
				TipoAsignatura.ANUAL, 2);
		Asignatura ir = new AsignaturaImpl("Ingenier�a de Requisitos", "2050020", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 3);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 6.0, TipoAsignatura.PRIMER_CUATRIMESTRE,
				2);

		d.nuevaAsignatura(fp);
		d.nuevaAsignatura(adda);
		d.nuevaAsignatura(ir);
		d.nuevaAsignatura(so);

		p1.imparteAsignatura(ir, 1.0);
		p1.imparteAsignatura(so, 3.5);
		p2.imparteAsignatura(so, 3.0);
		p3.imparteAsignatura(ir, 2.0);
		p3.imparteAsignatura(fp, 3.0);

		muestraProfesoresDepartamento(d);
		System.out.println(
				"�Existe alg�n profesor asignado a la asignatura " + fp + "?: " + d.existeProfesorAsignado(fp));
		System.out.println(
				"�Existe alg�n profesor asignado a la asignatura " + adda + "?: " + d.existeProfesorAsignado(adda));

	}

	private static void muestraProfesoresDepartamento(Departamento d) {
		System.out.println("Asignaturas impartidas por los profesores del departamento " + d);
		for (Profesor p : d.getProfesores()) {
			System.out.println("Profesor " + p + ": " + p.getAsignaturas());
		}
	}

	private static void testBorraTutoriasCategoria() {
		System.out.println("==================================Probando borraTutorias(Categoria)");
		Departamento d = new DepartamentoImpl("Lenguajes y Sistemas Inform�ticos");

		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor p2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor p3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);

		p1.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		p2.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		p3.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);

		d.nuevoProfesor(p1);
		d.nuevoProfesor(p2);
		d.nuevoProfesor(p3);

		muestraTutoriasDepartamento(d);

		System.out.println("Invocamos a borraTutorias para categor�a AYUDANTE...");
		d.borraTutorias(Categoria.AYUDANTE);

		muestraTutoriasDepartamento(d);
	}

	private static void muestraTutoriasDepartamento(Departamento d) {
		System.out.println("Tutor�as de los profesores del departamento " + d);
		for (Profesor p : d.getProfesores()) {
			System.out.println("Profesor " + p + ": " + p.getTutorias());
		}
	}

	//*****************************************+
}