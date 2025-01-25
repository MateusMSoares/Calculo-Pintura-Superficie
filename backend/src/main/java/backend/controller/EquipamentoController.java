package backend.controller;

import org.springframework.web.bind.annotation.*;
import backend.dto.EquipamentoDto;
import backend.entitys.Equipamento;
import backend.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipamento")
public class EquipamentoController {

    // O Spring irá injetar a dependência automaticamente
    @Autowired
    EquipamentoService equipamentoService;

    @GetMapping("/")
    public ResponseEntity<List<EquipamentoDto>> listarEquipamentos() {
        try {
            List<EquipamentoDto> equipamentos = equipamentoService.listarEquipamentos();
            return ResponseEntity.ok(equipamentos);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDto> procurarPorId(@PathVariable String id) {
        try {
            EquipamentoDto equipamento = equipamentoService.procurarPorId(id);
            return ResponseEntity.ok(equipamento);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Equipamento> adicionarEquipamento(@RequestBody Equipamento newEquipamento) throws IOException {
        Equipamento equipamento = equipamentoService.criarEquipamento(newEquipamento);
        return ResponseEntity.ok(equipamento);
    }

    @PostMapping("/calcular")
    public ResponseEntity<Map<String, Object>> calcular(@RequestBody Equipamento equipamentoDto) {
        try {
            Map<String, Object> result = equipamentoService.calcularMedidas(equipamentoDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
