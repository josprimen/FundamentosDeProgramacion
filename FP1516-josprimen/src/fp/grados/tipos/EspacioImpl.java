package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;

public class EspacioImpl implements Espacio{
private TipoEspacio tipo;
private String nombre;
private Integer capacidad;
private Integer planta;

public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta){
	checkCapacidad(capacidad);
	this.tipo=tipo;
	this.nombre=nombre;
	this.capacidad=capacidad;
	this.planta=planta;
}
private void checkCapacidad(Integer capacidad){
	if(capacidad<=0){
		throw new ExcepcionEspacioNoValido("La capacidad debe ser positiva y distinta de cero");
	}
}
public boolean equals (Object o){
	boolean res = false;
	if (o instanceof Espacio){
		Espacio e = (Espacio) o;
		res = getNombre().equals(e.getNombre())&&
				getPlanta().equals(e.getPlanta());
	}
	return res;
}
public int hashCode(){
	return getNombre().hashCode() + getPlanta().hashCode()*31;
	
}
public int compareTo(Espacio e){
	int res = getPlanta().compareTo(e.getPlanta());
	if (res == 0){
		res = getNombre().compareTo(e.getNombre());
	}
	return res;
}

@Override
public TipoEspacio getTipo() {
	return tipo;
}

@Override
public String getNombre() {
	return nombre;
}

@Override
public Integer getCapacidad() {
	return capacidad;
}

@Override
public Integer getPlanta() {
	return planta;
}

@Override
public void setTipo(TipoEspacio tipo) {
	this.tipo=tipo;
	
}

@Override
public void setNombre(String nombre) {
	this.nombre=nombre;
	
}

@Override
public void setCapacidad(Integer capacidad) {
	checkCapacidad(capacidad);
	this.capacidad=capacidad;
	
}
public String toString(){
	return getNombre()+" (Planta "+getPlanta()+")";
}

}
