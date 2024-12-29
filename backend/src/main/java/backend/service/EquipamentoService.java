package backend.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.dto.NewEquipamentDto;
import backend.entitys.Equipamento;
import lombok.Getter;


@Service
@Getter
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
        try {
            return jsonReader.carregarEquipamentoPorId(idEquipamento);
    
        } catch (Exception e) {
            System.err.println("Erro ao montar o equipamento: " + e.getMessage());
            throw new RuntimeException("Erro ao montar o equipamento", e);
        }
    }

    public List<Equipamento> listarEquipamentos() throws IOException{
        List<Equipamento> equipamentos = jsonReader.carregarEquipamentos();
        return equipamentos;
    }
    
    public Equipamento criarEquipamento(NewEquipamentDto newEquipamento) throws IOException {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(newEquipamento.getNome());
        equipamento.setTipo(tipoService.carregarTipoPorId(newEquipamento.getTipoId()));
        equipamento.setGeometria(geometriaService.carregarGeometriaPorId(newEquipamento.getGeometriaId()));
        equipamento.setPropriedades(this.setPropriedades(equipamento));

        return equipamento;
    }
    
    public Map<String, Object> setPropriedades(Equipamento equipamento) {
        Map<String, Object> propriedades = new HashMap<>();

        if (equipamento.getTipo() != null) {
            propriedades.putAll(equipamento.getTipo().getPropriedades());
        }
        
        if (equipamento.getGeometria() != null) {
            propriedades.putAll(equipamento.getGeometria().getPropriedades());
        }
        return propriedades;
    }
}
