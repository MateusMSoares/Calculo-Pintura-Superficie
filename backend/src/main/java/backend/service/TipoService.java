package backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.entitys.Tipos;

@Service
public class TipoService {


    public List<String> carregarTipos() {
        return Tipos.getTipos();
    }

}
