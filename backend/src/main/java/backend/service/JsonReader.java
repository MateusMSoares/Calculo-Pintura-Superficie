package backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import backend.entitys.Equipamento;
import backend.entitys.Geometria;
import backend.entitys.Tipo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Tipo> carregarTipos() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("banco/tipos.json");
        if (inputStream == null) {
            throw new IOException("Arquivo tipos.json não encontrado");
        }
        List<Tipo> tipos = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Tipo.class));
        return tipos;
    }
    public Tipo carregarTipoPorId(int id) throws IOException {
        List<Tipo> tipos = carregarTipos();
        for (Tipo tipo : tipos) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        return null;
    }

    public List<Geometria> carregarGeometrias() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("banco/geometrias.json");
        if (inputStream == null) {
            throw new IOException("Arquivo geometrias.json não encontrado");
        }
        List<Geometria> geometrias = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Geometria.class));
        return  geometrias;
    }

    public Geometria carregarGeometriaPorId(int id) throws IOException {
        List<Geometria> geometrias = carregarGeometrias();
        for (Geometria geometria : geometrias) {
            if (geometria.getId() == id) {
                return geometria;
            }
        }
        return null;
    }

    public List<Equipamento> carregarEquipamentos() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("banco/equipamentos.json");
        if (inputStream == null) {
            throw new IOException("Arquivo equipamento.json não encontrado");
        }
        List<Equipamento> equipamentos = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Equipamento.class));
        return equipamentos;
    }
}

