package fp.grados.tipos.test;

//La emocionata

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoEspacio;

public class TestCentro {
	
	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();
		testIncluirEspacio();
		
		testConteosEspacios();
		testGetDespachos();
		testGetDespachosDepartamento();
		
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1("ETSII", "Reina Mercedes", 3, 1);
	}
	
	private static void testConstructor1Excepcion() {
		System.out
		.println("==================================Probando el primer constructor, plantas menor de 1");
		testConstructor1("ETSII", "Reina Mercedes", 0, 1);
	}
	
	private static void testIncluirEspacio() {
		System.out
		.println("==================================Probando a añadir espacios");
		Centro c = new CentroImpl("ETSII", "Reina Mercedes", 3, 1);
		Espacio e1 = new EspacioImpl(TipoEspacio.OTRO, "Grupo 1", 200, 1);
		Espacio e2 = new EspacioImpl(TipoEspacio.OTRO, "Grupo 2", 200, 1);
		Espacio e3 = new EspacioImpl(TipoEspacio.OTRO, "Grupo 3", 200, 1);
		c.nuevoEspacio(e1);
		c.nuevoEspacio(e2);
		c.nuevoEspacio(e3);
		
		System.out.println(c.getEspacios());
	}
	
	private static void testConstructor1(String nombre, String direccion, Integer plantas, Integer sotanos) {

		try {
			Centro c = new CentroImpl(nombre, direccion, plantas, sotanos);
			mostrarCentro(c);
		} catch (ExcepcionCentroNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionCentroNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}
	
	private static void mostrarCentro(Centro c) {
		System.out.println("Centro --> <" + c + ">");
		System.out.println("\tNombre: <" + c.getNombre() + ">");
		System.out.println("\tDireccion: <" + c.getDireccion() + ">");
		System.out.println("\tPlantas: <" + c.getNumeroPlantas() + ">");
		System.out.println("\tSótanos:  <" + c.getNumeroSotanos() + ">");
	}
	
	//T8
	private static void testConteosEspacios() {
		System.out.println("==================================Probando conteosEspacios");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4,
				1);

		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.TEORIA, "A0.10", 50, 0));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.TEORIA, "A1.10", 50, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 50, 2));

		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.EXAMEN, "A3.10", 150, 3));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.EXAMEN, "A3.11", 150, 3));

		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.30", 30, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.31", 30, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.32", 30, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.33", 30, 1));

		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.SEMINARIO, "Salón de Grados", 60, 1));

		etsii.nuevoEspacio(new DespachoImpl("F0.20", 1, 0));
		etsii.nuevoEspacio(new DespachoImpl("F0.21", 3, 0));
		etsii.nuevoEspacio(new DespachoImpl("F0.22", 2, 0));

		muestraEspaciosCentro(etsii);
		muestraConteosEspaciosCentro(etsii);
	}

	private static void muestraConteosEspaciosCentro(Centro c) {
		System.out.println("Conteo de tipos de espacios:");
		Integer[] conteos = c.getConteosEspacios();
		for (int i = 0; i < conteos.length; i++) {
			System.out.println("Espacios de tipo " + TipoEspacio.values()[i] + ": " + conteos[i]);
		}
	}

	private static void muestraEspaciosCentro(Centro c) {
		System.out.println("Espacios del centro " + c);
		for (Espacio e : c.getEspacios()) {
			System.out.println(e.getTipo() + ": " + e);
		}
	}

	private static void testGetDespachosDepartamento() {
		System.out.println("==================================Probando getDespachos(Departamento)");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4,
				1);

		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor p2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor p3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);

		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		lsi.nuevoProfesor(p1);
		lsi.nuevoProfesor(p2);
		ccia.nuevoProfesor(p3);

		Despacho d1 = new DespachoImpl("F0.20", 1, 0, p1);
		Despacho d2 = new DespachoImpl("F0.21", 2, 0, p2);
		Despacho d3 = new DespachoImpl("F0.22", 1, 0, p3);

		etsii.nuevoEspacio(d1);
		etsii.nuevoEspacio(d2);
		etsii.nuevoEspacio(d3);

		muestraDespachosDepartamentoCentro(etsii, lsi);
	}

	private static void testGetDespachos() {
		System.out.println("==================================Probando getDespachos");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4,
				1);

		Despacho d1 = new DespachoImpl("F0.20", 1, 0);
		Despacho d2 = new DespachoImpl("F0.21", 1, 0);
		Despacho d3 = new DespachoImpl("F0.22", 1, 0);

		etsii.nuevoEspacio(d1);
		etsii.nuevoEspacio(d2);
		etsii.nuevoEspacio(d3);

		muestraDespachosCentro(etsii);
	}

	private static void muestraDespachosCentro(Centro c) {
		System.out.println("Despachos del centro " + c);
		for (Despacho d : c.getDespachos()) {
			System.out.println(d);
		}
	}

	private static void muestraDespachosDepartamentoCentro(Centro c, Departamento dep) {
		System.out.println("Despachos del centro " + c + " con profesores del departamento " + dep);
		for (Despacho d : c.getDespachos(dep)) {
			System.out.println(d);
		}
	}

}

