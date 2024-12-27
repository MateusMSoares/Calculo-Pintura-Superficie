package backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.NewEquipamentDto;
import backend.entitys.Equipamento;
import backend.service.EquipamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipamento")
public class EquipamentoController {

    @Autowired
    EquipamentoService equipamentoService = new EquipamentoService();

    @GetMapping("/")
    public ResponseEntity<Equipamento> listarEquipamentos() {
   
        return ResponseEntity.ok(null); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> listarEquipamento(@PathVariable int id) {
        try {
            Equipamento equipamento = equipamentoService.montarEquipamento(id);
            return ResponseEntity.ok(equipamento);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<NewEquipamentDto> adicionarEquipamento(@RequestBody NewEquipamentDto newEquipamentDto) {
        System.out.println(newEquipamentDto);
   
        return ResponseEntity.ok(newEquipamentDto); 
    }

}

