package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public class BecaImpl implements Beca {
	//EXPLICACION EN CLASE
private static final Double CUANTIA_MINIMA = 1500.0;
private String codigo;
private Double cuantiaTotal;
private Integer duracion;
private TipoBeca tipo;

public BecaImpl (String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo){
	checkCodigo(codigo);
	checkDuracion(duracion);
	checkCuantiaTotal(cuantiaTotal);
	this.codigo=codigo;
	this.cuantiaTotal=cuantiaTotal;
	this.duracion=duracion;
	this.tipo=tipo;
}
public BecaImpl (String codigo, TipoBeca tipo){
	checkCodigo(codigo);
	this.codigo=codigo;
	this.tipo=tipo;
	this.cuantiaTotal= CUANTIA_MINIMA;
	this.duracion=1;
}
private void checkCodigo(String codigo){
	Boolean correcto = codigo.length()==7 
	&& Character.isLetter(codigo.charAt(0))
	&& Character.isLetter(codigo.charAt(1))
	&& Character.isLetter(codigo.charAt(2))
	&& Character.isDigit(codigo.charAt(3))
	&& Character.isDigit(codigo.charAt(4))
	&& Character.isDigit(codigo.charAt(5))
	&& Character.isDigit(codigo.charAt(6));
	if(!correcto){
		throw new ExcepcionBecaNoValida(
				"El código debe estar compuesto por 3 letras y 4 números");
	}
}

private void  checkDuracion(Integer duracion){
	if(duracion<1){
		throw new ExcepcionBecaNoValida("La duración mínima de la beca debe ser de 1 mes");
	}
}

private void checkCuantiaTotal(Double cuantiaTotal){
	if(cuantiaTotal<CUANTIA_MINIMA){
		throw new ExcepcionBecaNoValida("La cuantía mínima debe ser"+CUANTIA_MINIMA+"euros");
	}
}
public boolean equals (Object o){
	boolean res = false;
	if(o instanceof Beca){
		Beca b = (Beca) o;
		res= getCodigo().equals(b.getCodigo())&&
				getTipo().equals(b.getTipo());
	}
	return res;
}
public int hashCode(){
	return getCodigo().hashCode() + getTipo().hashCode() * 31;
}
public int compareTo(Beca b){
	int res = getCodigo().compareTo(b.getCodigo());
	if(res == 0){
		res = getTipo().compareTo(b.getTipo());
	}
	return res;
}





@Override
public String getCodigo() {
	return codigo;
}

@Override
public Double getCuantiaTotal() {
	return cuantiaTotal;
}

@Override
public Integer getDuracion() {
	return duracion;
}

@Override
public TipoBeca getTipo() {
	return tipo;
}

@Override
public Double getCuantiaMensual() {
	return getCuantiaTotal() / getDuracion();
	
}

@Override
public void setCuantiaTotal(Double cuantiaTotal) {
	checkCuantiaTotal(cuantiaTotal);
	this.cuantiaTotal=cuantiaTotal;
	
}

@Override
public void setDuracion(Integer duracion) {
	checkDuracion(duracion);
	this.duracion=duracion;
	
}

public String toString() {
return "[" + getCodigo() + "," + getTipo() + "]";
}




//TODO: FALTA UN CONSTRUCTOR






}
