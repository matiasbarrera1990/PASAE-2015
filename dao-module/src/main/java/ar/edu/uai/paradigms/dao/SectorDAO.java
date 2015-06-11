package ar.edu.uai.paradigms.dao;

import ar.edu.uai.model.Asiento;
import ar.edu.uai.model.Sector;

import java.util.Collection;

public interface SectorDAO extends GenericDAO<Sector> {
	
	public Integer chequearDisponibilidad (long id_sector);

	public Collection<Asiento> asientosDisponiblesDeSector (long id_sector);

	public Collection<Asiento> asientosOcupadosDeSector (long id_sector);

}
