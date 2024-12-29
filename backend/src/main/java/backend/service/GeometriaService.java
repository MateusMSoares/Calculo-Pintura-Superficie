package backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import backend.entitys.Geometria;

@Service
public class GeometriaService {

    private JsonReader jsonReader;

    public GeometriaService() {
        this.jsonReader = new JsonReader();
    }

    public List<Geometria> carregarGeometrias() throws IOException {
        return jsonReader.carregarGeometrias();
    }

    public Geometria carregarGeometriaPorId(int id) throws IOException {
        return jsonReader.carregarGeometriaPorId(id);
    }

}
