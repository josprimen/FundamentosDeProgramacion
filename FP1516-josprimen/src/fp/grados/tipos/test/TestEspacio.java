package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;



public class TestEspacio {

	public static void main(String[] args){
		testConstructorNormal();
		testConstructorExcepcion();
		testSetCapacidadNormal();
		testSetCapacidadExcepcion();
		testIgualdad();
		testOrden();
	}
	private static void testConstructorNormal(){
	System.out.println("Probando el constructor normal");
	testConstructor(TipoEspacio.LABORATORIO,"Lab_F1.32",60,2);
	}
	private static void testConstructorExcepcion(){
		System.out.println("==Probando el constructor con capacidad negativa");
		testConstructor(TipoEspacio.LABORATORIO,"Lab_F1.32",-60,2);
	}
	
	private static void testSetCapacidadNormal(){
		System.out.println("==Probando setCapacidad normal");
		Espacio esp = new EspacioImpl (TipoEspacio.LABORATORIO,"Lab_F1.32",60,2);
		testSetCapacidad(esp, 36);
	}
	
	private static void testSetCapacidadExcepcion(){
		System.out.println("==Probando setCapacidad normal");
		Espacio esp = new EspacioImpl (TipoEspacio.LABORATORIO,"Lab_F1.32",60,2);
		testSetCapacidad(esp, 0);
	}
	
	//*******************Probando igualdades y demás
	private static void testIgualdad(){
		System.out.println("\n ==Probando igualdad");
		//Objetos con misma planta y nombre
		Espacio e1 = new EspacioImpl(TipoEspacio.LABORATORIO,"Lab_F1.32",60,2);
		Espacio e2 = new EspacioImpl(TipoEspacio.LABORATORIO,"Lab_F1.32",60,2);
		//Objeto con distinta planta
		Espacio e3 = new EspacioImpl(TipoEspacio.LABORATORIO,"Lab_F3.32",60,3);
		//Objeto con misma planta pero distinto nombre
		Espacio e4 = new EspacioImpl(TipoEspacio.LABORATORIO,"LabF1.40",60,2);
		
		
		System.out.println("Codigo hash del objeto 1 ("+e1+"): "+e1.hashCode());
		System.out.println("Codigo hash del objeto 2 ("+e2+"): "+e2.hashCode());
		System.out.println("Codigo hash del objeto 3 ("+e3+"): "+e3.hashCode());
		System.out.println("Codigo hash del objeto 4 ("+e4+"): "+e4.hashCode());

		System.out.println("¿Es el objeto 1 igual al 2? (Debe dar true) "+e1.equals(e2));
		System.out.println("¿Es el objeto 1 distinto del 3? (Debe dar true) "+!e1.equals(e3));
		System.out.println("¿Es el objeto 1 distinto al 4? (Debe dar true) "+!e1.equals(e4));
	
	}
	
	private static void testOrden(){
		System.out.println("\n===============================Probando orden natural");
		// Creamos cuatro objetos tales que menor < igual1 == igual2 < mayor
		Espacio menor = new EspacioImpl(TipoEspacio.LABORATORIO,"Lab_F1.32",60,1);
		Espacio igual1 = new EspacioImpl(TipoEspacio.LABORATORIO,"Lab_F2.36",60,2);
		Espacio igual2 = new EspacioImpl(TipoEspacio.LABORATORIO,"Lab_F2.36",60,2);
		Espacio mayor = new EspacioImpl(TipoEspacio.LABORATORIO,"Lab_F3.18",60,3);
		System.out.print("(debe ser ANTES) ");
		compara(menor,igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1,igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2,mayor);
		
		
	}
	
	
	
	//**********************************Métodos Auxiliares
	
	private static void testConstructor(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		try {
			Espacio esp = new EspacioImpl (tipo, nombre, capacidad, planta);
			mostrarEspacio(esp);
		} catch (ExcepcionEspacioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	private static void testSetCapacidad(Espacio esp, Integer capacidad){
		try {
			System.out.println("La capacidad antes de la operación es: "+ esp.getCapacidad());
			System.out.println("El nuevo valor es: "+  capacidad);
			esp.setCapacidad(capacidad);
			System.out.println("La capacidad después de la operación es: "+ esp.getCapacidad());
		} catch (ExcepcionEspacioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionEspacioNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}
	
	
	private static void mostrarEspacio(Espacio esp){
		System.out.println("Espacio--> <"+esp+">");
		System.out.println("Tipo de Espacio: <"+esp.getTipo()+">");
		System.out.println("Nombre: <"+esp.getNombre()+">");
		System.out.println("Capacidad: <"+esp.getCapacidad()+">");
		System.out.println("Planta: <"+esp.getPlanta()+">");
	}

		//*******************Métodos auxiliares de igualdad
	
		private static void compara (Espacio e1, Espacio e2){
		System.out.println("El objeto<"+e1+">");
		if(e1.compareTo(e2)<0){
			System.out.println(" va antes que el objeto ");
		}
		else if(e1.compareTo(e2)>0){
			System.out.println(" va después que el objeto ");
		}
		else{
	System.out.println(" va en la misma posición que ");
		}
		System.out.println("<"+e2+">");
	}
	}
		

