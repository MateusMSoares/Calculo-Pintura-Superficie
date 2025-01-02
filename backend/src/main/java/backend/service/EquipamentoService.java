package backend.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.dto.NewEquipamentDto;
import backend.entitys.Equipamento;
import backend.entitys.Geometria;
import backend.entitys.Tipo;
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
        equipamento.setTipo(newEquipamento.getTipoId());
        equipamento.setGeometria(newEquipamento.getGeometriaId());
        // Passando o Map de propriedades_fundamentais para o método setPropriedades
        equipamento.setPropriedades(this.setPropriedades(equipamento, newEquipamento.getPropriedades_fundamentais()));
    
        return equipamento;
    }
    
    public Map<String, Object> setPropriedades(Equipamento equipamento, Map<String, Object> propriedades_fundamentais) throws IOException {
        Map<String, Object> propriedades = new HashMap<>();
    
        // Carregar Tipo e Geometria
        Tipo tipo = tipoService.carregarTipoPorId(equipamento.getTipo());
        Geometria geometria = geometriaService.carregarGeometriaPorId(equipamento.getGeometria());
    
        if (tipo != null && tipo.getPropriedades() != null) {
            propriedades.putAll(tipo.getPropriedades());
        }
    
        if (geometria != null && geometria.getPropriedades() != null) {
            propriedades.putAll(geometria.getPropriedades());
        }

        for (Map.Entry<String, Object> entry : propriedades_fundamentais.entrySet()) {
            String chave = entry.getKey();
            Object valor = entry.getValue();
    
            if (propriedades.containsKey(chave)) {
                propriedades.put(chave, valor); 
            } else {
                propriedades.put(chave, valor); 
            }
        }
    
        return propriedades;
    }
    

}
