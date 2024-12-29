package backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.dto.NewEquipamentDto;
import backend.entitys.Equipamento;
import backend.entitys.Geometria;
import backend.entitys.Tipo;

@Service
public class EquipamentoService {
    private JsonReader jsonReader;
    @Autowired
    private TipoService tipoService;
    @Autowired
    private GeometriaService geometriaService;

    public EquipamentoService() {
        this.jsonReader = new JsonReader();
    }

    public Equipamento procuraPorId(int idEquipamento) throws IOException {
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

    public List<Equipamento> listarEquipamentos() throws IOException{
        List<Equipamento> equipamentos = jsonReader.carregarEquipamentos();
        return equipamentos;
    }
    
    public Equipamento criarEquipamento(NewEquipamentDto newEquipamento) throws IOException {
        Equipamento equipamento = new Equipamento();
        equipamento.setTipo(tipoService.carregarTipoPorId(newEquipamento.getTipoId()));
        equipamento.setGeometria(geometriaService.carregarGeometriaPorId(newEquipamento.getGeometriaId()));

        return equipamento;
    }
    
}
