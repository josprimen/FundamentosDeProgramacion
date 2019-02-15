package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;



public class TestNota {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();
		testConstructor1Excepcion2();
		testConstructor2Normal();
		testConstructor2Excepcion();
		
		testIgualdad();
		testOrden();
	}

	private static void testConstructor1Normal(){
		System.out.println("==Probando el primer constructor");
		testConstructor1 (new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, 9.0, true);
		
	}
	
	private static void testConstructor1Excepcion(){
		System.out.println("==Probando el primer constructor con nota negativa");
		testConstructor1 (new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, -9.0, true);
	}
	
	private static void testConstructor1Excepcion2(){
		System.out.println("==Probando el primer constructor con menciónn de honor true y nota menor que 9 (no valida)");
		testConstructor1 (new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, 5.2, true);
	}
	
	private static void testConstructor2Normal(){
		System.out.println("==Probando el segundo constructor");
		testConstructor2 (new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, 9.0);
	}
	
	private static void testConstructor2Excepcion(){
		System.out.println("==Probando el segundo constructor con nota negativa");
		testConstructor2 (new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, -8.0);
	}
	
	//Probano igualdades y demas
	
	private static void testIgualdad(){
		System.out.println("\n ==Probando igualdad");
		//Objetos iguales
		Nota n1 = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, 9.0, true);
		Nota n2 = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, 9.0, true);
		//Objeto con distinto curso académico
		Nota n3 = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2016,Convocatoria.PRIMERA, 9.0, true);
		//Objeto con el mismo curso academico pero distinta asignatura
		Nota n4 = new NotaImpl(new AsignaturaImpl("Circuitos Electrónicos", "2050002", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 1), 2015,Convocatoria.PRIMERA, 9.0, true);
		//Objeto con el mismo curso academico y asignatura pero distinta convocatoria
		Nota n5 = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.SEGUNDA, 9.0, true);
		
		
		
		System.out.println("Codigo hash del objeto 1 ("+n1+"): "+n1.hashCode());
		System.out.println("Codigo hash del objeto 2 ("+n2+"): "+n2.hashCode());
		System.out.println("Codigo hash del objeto 3 ("+n3+"): "+n3.hashCode());
		System.out.println("Codigo hash del objeto 4 ("+n4+"): "+n4.hashCode());
		System.out.println("Codigo hash del objeto 5 ("+n5+"): "+n5.hashCode());

		System.out.println("¿Es el objeto 1 igual al 2? (Debe dar true) "+n1.equals(n2));
		System.out.println("¿Es el objeto 1 distinto del 3? (Debe dar true) "+!n1.equals(n3));
		System.out.println("¿Es el objeto 1 distinto al 4? (Debe dar true) "+!n1.equals(n4));
		System.out.println("¿Es el objeto 1 distinto al 5? (Debe dar true) "+!n1.equals(n5));
	
	}
	
	
	private static void testOrden(){
		System.out.println("\n===============================Probando orden natural");
		// Creamos cuatro objetos tales que menor < igual1 == igual2 < mayor
		Nota menor = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2015,Convocatoria.PRIMERA, 9.0, true);
		Nota igual1 = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2016,Convocatoria.PRIMERA, 9.0, true);
		Nota igual2 = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2016,Convocatoria.PRIMERA, 9.0, true);
		Nota mayor = new NotaImpl(new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0,
				TipoAsignatura.ANUAL, 1), 2017,Convocatoria.PRIMERA, 9.0, true);
		System.out.print("(debe ser ANTES) ");
		compara(menor,igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1,igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2,mayor);
	}
	//********************************************************Métodos auxiliares
	
	
	private static void testConstructor1 (Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor, Boolean mencionHonor) {

		try {
			Nota n = new NotaImpl(asignatura, cursoAcademico, convocatoria, valor, mencionHonor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción nota no válida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}
	

	
	
	
	
	//(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor, Boolean mencionHonor)
	private static void testConstructor2(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor) {

		try {
			Nota n = new NotaImpl(asignatura, cursoAcademico, convocatoria, valor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}	

	private static void mostrarNota(Nota n) {
		System.out.println("Nota --> <" + n + ">");
		System.out.println("\tAsignatura: <" + n.getAsignatura() + ">");
		System.out.println("\tConvocatoria: <" + n.getConvocatoria() + ">");
		System.out.println("\tCurso Académico:  <" + n.getCursoAcademico() + ">");
		System.out.println("\tValor:  <" + n.getValor() + ">");
		System.out.println("\tMención de Honor:  <" + n.getMencionHonor() + ">");
	}

	//***************Métodos auxiliares de igualdad
	
	private static void compara (Nota n1, Nota n2){
		System.out.println("El objeto<"+n1+">");
		if(n1.compareTo(n2)<0){
			System.out.println(" va antes que el objeto ");
		}
		else if (n1.compareTo(n2)>0){
			System.out.println(" va después que el objeto ");
		}
		else{
			System.out.println(" va en la misma posicion que");
		}
		System.out.println("<"+n2+">");
	}
}