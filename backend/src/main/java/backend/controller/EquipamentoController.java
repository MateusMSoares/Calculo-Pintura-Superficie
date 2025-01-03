package backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.EquipamentDto;
import backend.entitys.Equipamento;
import backend.service.EquipamentoService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    EquipamentoService equipamentoService = new EquipamentoService();

    @GetMapping("/")
    public ResponseEntity<List<Equipamento>> listarEquipamentos() {
        try {
            List<Equipamento> equipamentos = equipamentoService.listarEquipamentos();
            System.out.println("Equipamentos: " + equipamentos);
            return ResponseEntity.ok(equipamentos);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }   
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> procuraPorId(@PathVariable int id) {
        try {
            Equipamento equipamento = equipamentoService.procuraPorId(id);
            return ResponseEntity.ok(equipamento);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Equipamento> adicionarEquipamento(@RequestBody EquipamentDto newEquipamentDto) throws IOException{
        Equipamento equipamento = equipamentoService.criarEquipamento(newEquipamentDto);
        System.out.println("Equipamento: " + equipamento.getPropriedades());
        equipamentoService.getJsonReader().salvarEquipamento(equipamento);
   
        return ResponseEntity.ok(equipamento);
    }

}

