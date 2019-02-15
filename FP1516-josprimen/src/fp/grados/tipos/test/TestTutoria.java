package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;

public class TestTutoria {
	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();
		testConstructor1Excepcion2();
		testConstructor1Excepcion3();
		testConstructor2Normal();
		testConstructor2Excepcion1();
		testConstructor2Excepcion2();
		testConstructor2Excepcion3();
		
		testIgualdad();
		testOrden();
		
	}

	private static void testConstructor1Normal(){
		System.out.println("==Probando el primer constructor");
		testConstructor1 (DayOfWeek.MONDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		
	}
	
	private static void testConstructor1Excepcion(){
		System.out.println("==Probando el primer constructor con tutoría en sábado");
		testConstructor1 (DayOfWeek.SATURDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		
	}
	
	private static void testConstructor1Excepcion2(){
		System.out.println("==Probando el primer constructor con hora de comienzo antes de las 8:30");
		testConstructor1 (DayOfWeek.MONDAY,LocalTime.of(7, 30), LocalTime.of(9, 30));
		
	}
	
	private static void testConstructor1Excepcion3(){
		System.out.println("==Probando el primer constructor con hora de fin después de las 21:30 ");
		testConstructor1 (DayOfWeek.MONDAY,LocalTime.of(8, 30), LocalTime.of(21, 50));
		
	}
	
	
	private static void testConstructor2Normal(){
		System.out.println("==Probando el segundo constructor");
		testConstructor2 (DayOfWeek.MONDAY, LocalTime.of(12, 10),35);
	}
	
	private static void testConstructor2Excepcion1(){
		System.out.println("==Probando el segundo constructor con tutoría en sábado");
		testConstructor2 (DayOfWeek.SATURDAY, LocalTime.of(12, 10),35);
	}
	
	private static void testConstructor2Excepcion2(){
		System.out.println("==Probando el segundo constructor con hora de comienzo antes de las 8:30");
		testConstructor2 (DayOfWeek.MONDAY, LocalTime.of(6, 10),35);
	}
	
	private static void testConstructor2Excepcion3(){
		System.out.println("==Probando el segundo constructor con duracion de la tutoría de menos de 30 minutos");
		testConstructor2(DayOfWeek.SATURDAY, LocalTime.of(12, 10),15);
	}
	
	//Probando igualdades y orden
	
	private static void testIgualdad(){
		System.out.println("\n ==Probando igualdad");
		//Objetos con el mismo día de la semana y hora de comienzo
		Tutoria t1 = new TutoriaImpl(DayOfWeek.MONDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		Tutoria t2 = new TutoriaImpl(DayOfWeek.MONDAY,LocalTime.of(8, 30), LocalTime.of(9, 00));
		//Objeto con distinto dia de la semana
		Tutoria t3 = new TutoriaImpl(DayOfWeek.TUESDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		//Objeto con mismo dia de la semana pero distinta hora de comienzo
		Tutoria t4 = new TutoriaImpl(DayOfWeek.MONDAY,LocalTime.of(9, 00), LocalTime.of(9, 30));
		
		
		System.out.println("Codigo hash del objeto 1 ("+t1+"): "+t1.hashCode());
		System.out.println("Codigo hash del objeto 2 ("+t2+"): "+t2.hashCode());
		System.out.println("Codigo hash del objeto 3 ("+t3+"): "+t3.hashCode());
		System.out.println("Codigo hash del objeto 4 ("+t4+"): "+t4.hashCode());

		System.out.println("¿Es el objeto 1 igual al 2? (Debe dar true) "+t1.equals(t2));
		System.out.println("¿Es el objeto 1 distinto del 3? (Debe dar true) "+!t1.equals(t3));
		System.out.println("¿Es el objeto 1 distinto al 4? (Debe dar true) "+!t1.equals(t4));
	
	}
	
	private static void testOrden(){
		System.out.println("\n===============================Probando orden natural");
		// Creamos cuatro objetos tales que menor < igual1 == igual2 < mayor
		Tutoria menor = new TutoriaImpl(DayOfWeek.MONDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		Tutoria igual1 = new TutoriaImpl(DayOfWeek.TUESDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		Tutoria igual2 = new TutoriaImpl(DayOfWeek.TUESDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		Tutoria mayor = new TutoriaImpl(DayOfWeek.FRIDAY,LocalTime.of(8, 30), LocalTime.of(9, 30));
		System.out.print("(debe ser ANTES) ");
		compara(menor,igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1,igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2,mayor);
		
		
	}
	
	//********************************************************Métodos auxiliares
	
	
	private static void testConstructor1 (DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {

		try {
			Tutoria t = new TutoriaImpl(diaSemana, horaComienzo, horaFin);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción tutoria no válida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}

	private static void testConstructor2(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion) {

		try {
			Tutoria t = new TutoriaImpl(diaSemana, horaComienzo, duracion);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}	

	private static void mostrarTutoria(Tutoria t){
		System.out.println("Tutoria--> <"+t+">");
		
		System.out.println("Día: <"+t.getDiaSemana()+">");
		System.out.println("Hora de Comienzo: <"+t.getHoraComienzo()+">");
		System.out.println("Hora de Fin: <"+t.getHoraFin()+">");
		System.out.println("Duración: <"+t.getDuracion()+">");
	}
	
	//*******************Métodos auxiliares de igualdad
	
			private static void compara (Tutoria t1, Tutoria t2){
			System.out.println("El objeto <"+t1+">");
			if(t1.compareTo(t2)<0){
				System.out.println(" va antes que el objeto ");
			}
			else if(t1.compareTo(t2)>0){
				System.out.println(" va después que el objeto ");
			}
			else{
		System.out.println(" va en la misma posición que ");
			}
			System.out.println("<"+t2+">");
		}
}
