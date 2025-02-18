package backend.controller;

import org.springframework.web.bind.annotation.*;
import backend.dto.EquipamentoDto;
import backend.entitys.Equipamento;
import backend.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipamento")
public class EquipamentoController {

    // O Spring irá injetar a dependência automaticamente
    @Autowired
    EquipamentoService equipamentoService;

    @GetMapping("/")
    public ResponseEntity<List<Equipamento>> listarEquipamentos() {
        try {
           List<Equipamento> equipamentos = equipamentoService.listarEquipamentos();
            return ResponseEntity.ok(equipamentos);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDto> procurarPorId(@PathVariable String id) {
        try {
            //EquipamentoDto equipamento = equipamentoService.procurarPorId(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Equipamento> adicionarEquipamento(@RequestBody EquipamentoDto newEquipamento) throws IOException {

        Equipamento equipamento = new Equipamento();
        System.out.println(newEquipamento);

        equipamento.CreatefromTipo(newEquipamento.getTipo(), newEquipamento);

        System.out.println(equipamento);
        Equipamento equipamentoCriado = equipamentoService.criarEquipamento(equipamento);
        return ResponseEntity.ok(equipamentoCriado);
    }

    @GetMapping("/calcular")
    public ResponseEntity<Equipamento> calcularDimensoes(@RequestBody EquipamentoDto equipamentoDto) {
        Equipamento equipamento = new Equipamento();
        equipamento.fromTipo(equipamentoDto.getTipo(), equipamentoDto);
        return ResponseEntity.ok(equipamento);
    }
}
