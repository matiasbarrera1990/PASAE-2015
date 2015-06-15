package ar.edu.uai.paradigms.translator;

import ar.edu.uai.model.Sector;
import ar.edu.uai.paradigms.dto.SectorDTO;
import ar.edu.uai.paradigms.service.EspectaculoService;

/**
 * Created by EzequielPanoff on 9/6/15.
 */
public class SectorTranslator {

    private EspectaculoService espectaculoService;

    public void setEspectaculoService(EspectaculoService espectaculoService) {
        this.espectaculoService = espectaculoService;
    }

    public Sector translate(SectorDTO sectorDTO) {

        return new Sector(sectorDTO.getNombre(), sectorDTO.getMonto(), espectaculoService.retrieveEspectaculo(sectorDTO.getEspectaculo()), sectorDTO.getAsientosLibres());
    }

    public SectorDTO translateToDTO(Sector sector) {
        return new SectorDTO(sector.getNombre(),sector.getMonto(),sector.getEspectaculo().getId(), sector.getAsientosLibres());
    }
}