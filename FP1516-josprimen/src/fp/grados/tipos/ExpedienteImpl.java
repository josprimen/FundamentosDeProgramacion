package fp.grados.tipos;

import java.util.ArrayList;
import java.util.List;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;

public class ExpedienteImpl implements Expediente {

	private List<Nota> notas;
	
	
	public ExpedienteImpl(){
		this.notas = new ArrayList<Nota>();
	}

	public void checkConvocatorias(Nota n){
		Integer cont = 0;
			for(Nota t: getNotas()){
			if(t.getAsignatura().equals(n.getAsignatura()) && t.getCursoAcademico().equals(n.getCursoAcademico())){
				cont ++;
			}
		}
			if(cont>=2){
				throw new ExcepcionExpedienteOperacionNoPermitida ("Un expediente no puede contener notas para más de dos convocatorias de una misma asignatura y curso");
			}
	}
	
	public Double getNotaMedia(){
		Double suma = 0.0;
		Integer numElem = 0;
		for (Nota n: getNotas()){
			if(n.getValor()>=5.0){
			suma += n.getValor();
			numElem ++;
			}
		}
		if (numElem == 0){
		return 0.0;
	}
		else{ return suma/numElem;}
		}
	
	
	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}

	
	public void nuevaNota(Nota n) {
		checkConvocatorias(n);
		if(notas.isEmpty()){
			notas.add(n);
		}
		else{
			for (Nota n1 : getNotas()){
				if(n1.getAsignatura().equals(n.getAsignatura()) && n1.getConvocatoria().equals(n.getConvocatoria()) && n1.getCursoAcademico().equals(n.getCursoAcademico())){
					//METER CHECKCONVOCATORIAS AQUÍ SERÍA NECESARIO?
					this.notas.remove(n1);
					
				}
			}
			
			this.notas.add(n);
		}
	}

	
	public boolean equals (Object o){
		boolean res = false;
		if (o instanceof Expediente){
			Expediente e = (Expediente) o;
			res = getNotas().equals(e.getNotas());
		}
		return res;
	}

	public int hashCode(){
		return getNotas().hashCode();
	}
	
	public String toString(){
		return getNotas().toString();
	}


	
	
}
