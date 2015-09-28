package ar.edu.uai.paradigms.dao;

import ar.edu.uai.model.Venta;

import java.util.Collection;

public interface VentaDAO extends GenericDAO<Venta> {

	public Collection<Venta> listarComprasDeEspectador(String username);

	public Float calcularMontoNeto(long id_sector, long id_espectaculo);

	Collection<Venta> listarCantidadVentasPorMes();
}
