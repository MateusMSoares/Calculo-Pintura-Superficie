package backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import backend.entitys.Equipamento;
import backend.entitys.Geometria;
import backend.entitys.Tipo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonReader {

    private static final String TIPOS_FILE_PATH = "banco/tipos.json";
    private static final String GEOMETRIAS_FILE_PATH = "banco/geometrias.json";
    private static final String EQUIPAMENTOS_FILE_PATH = "banco/equipamentos.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Tipo> carregarTipos() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(TIPOS_FILE_PATH);
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
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(GEOMETRIAS_FILE_PATH);
        if (inputStream == null) {
            throw new IOException("Arquivo geometrias.json não encontrado");
        }
        List<Geometria> geometrias = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Geometria.class));
        return geometrias;
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
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(EQUIPAMENTOS_FILE_PATH);
        if (inputStream == null) {
            throw new IOException("Arquivo equipamento.json não encontrado");
        }
        List<Equipamento> equipamentos = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Equipamento.class));
        return equipamentos;
    }

    public Equipamento carregarEquipamentoPorId(int id) throws IOException {
        List<Equipamento> equipamentos = carregarEquipamentos();
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() == id) {
                return equipamento;
            }
        }
        return null;
    }

    public void salvarEquipamento(Equipamento equipamento) throws IOException {
        List<Equipamento> equipamentos = carregarEquipamentos();

        equipamentos.add(equipamento);

        String caminhoArquivo = "backend/src/main/resources/banco/equipamentos.json"; 
        File equipamentosFile = new File(caminhoArquivo);

        try {        
            if (!equipamentosFile.exists()) {
                equipamentosFile.createNewFile();
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(equipamentosFile, equipamentos);

            System.out.println("Equipamento salvo com sucesso em: " + equipamentosFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o equipamento.");
        }
    }
    
    
}
