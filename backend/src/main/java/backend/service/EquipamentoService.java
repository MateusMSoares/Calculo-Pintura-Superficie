package backend.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import backend.entitys.Equipamento;
import backend.entitys.Geometria;
import backend.entitys.Tipo;

@Service
public class EquipamentoService {
    private JsonReader jsonReader;

    public EquipamentoService() {
        this.jsonReader = new JsonReader();
    }

    public Equipamento montarEquipamento(int idEquipamento) throws IOException {
        // Não é mais necessário inicializar a variável antes do try
        try {
            // Carregar os dados dos arquivos JSON
            List<Tipo> tipos = jsonReader.carregarTipos();
            System.out.println("Tipos: " + tipos);
            List<Geometria> geometrias = jsonReader.carregarGeometrias();
            System.out.println("Geometrias: " + geometrias);
            List<Equipamento> equipamentos = jsonReader.carregarEquipamentos();
            System.out.println("Equipamentos: " + equipamentos);
    
            // Retornar o equipamento montado
            return null;
    
        } catch (Exception e) {
            // Caso ocorra algum erro, você pode registrar ou lançar uma exceção personalizada
            System.err.println("Erro ao montar o equipamento: " + e.getMessage());
            throw new RuntimeException("Erro ao montar o equipamento", e);  // Re-lançar a exceção, se necessário
        }
    }
    
    
    
}
