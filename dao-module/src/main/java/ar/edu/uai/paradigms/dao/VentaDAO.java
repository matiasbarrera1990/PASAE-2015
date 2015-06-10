package ar.edu.uai.paradigms.dao;

import java.util.Collection;

import ar.edu.uai.model.Venta;

public interface VentaDAO extends GenericDAO<Venta> {

	public Collection<Venta> listarComprasDeEspectador(String espectador);
	  
}
