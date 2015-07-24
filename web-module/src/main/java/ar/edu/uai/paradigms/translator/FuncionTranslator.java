package ar.edu.uai.paradigms.translator;

import ar.edu.uai.model.Funcion;
import ar.edu.uai.paradigms.dto.FuncionDTO;

public class FuncionTranslator {

    public Funcion translate(FuncionDTO funcionDTO) {
        return new Funcion(funcionDTO.getFecha(), funcionDTO.getHora());

    }

    public FuncionDTO translateToDTO(Funcion funcion) {
        return new FuncionDTO(funcion.getFecha(), funcion.getHora(), funcion.getEspectaculo().getId());
    }


}
