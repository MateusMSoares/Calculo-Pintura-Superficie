package backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import backend.entitys.Tipo;

@Service
public class TipoService {
    private JsonReader jsonReader;

    public TipoService() {
        this.jsonReader = new JsonReader();
    }

    public List<Tipo> carregarTipos() throws IOException {
        return jsonReader.carregarTipos();
    }

    public Tipo procurarPorId(int id) throws IOException {
        return jsonReader.carregarTipoPorId(id);
    }


}
