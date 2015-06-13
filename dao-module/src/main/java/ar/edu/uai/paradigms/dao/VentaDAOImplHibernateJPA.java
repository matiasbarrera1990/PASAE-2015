package ar.edu.uai.paradigms.dao;

import ar.edu.uai.model.Venta;

import javax.persistence.Query;
import java.util.Collection;

public class VentaDAOImplHibernateJPA  extends GenericDaoHibernateJPA<Venta> implements VentaDAO {

	@Override
	public Collection<Venta> listarComprasDeEspectador(String espectador) {
		
		Query consulta=this.entityManager.createQuery("from Venta as v where v.comprador=?");
	    consulta.setParameter(1,espectador);
	    return consulta.getResultList();
		
	}

	public Float calcularMontoNeto(long id_sector, long id_espectaculo) {

		Query consulta=this.entityManager.createQuery("select s.monto from Sector where s.id=? and s.espectaculo.id=?");
		consulta.setParameter(1, id_sector);
		consulta.setParameter(2, id_espectaculo);
		return (Float) consulta.getSingleResult();
	}

	//AGREGO COMENTARIO

}
