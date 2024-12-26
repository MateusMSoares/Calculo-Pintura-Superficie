package backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.NewEquipamentDto;
import backend.entitys.Equipamento;
import backend.entitys.Tipo;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipamento")
public class EquipamentoController {

    @GetMapping("/")
    public ResponseEntity<Equipamento> listarEquipamentos() {
        Tipo tipo1 = new Tipo("Tipo 1", new HashMap<String, Boolean>(){{
            put("Propriedade 1", true);
            put("Propriedade 2", false);
        }});

        Equipamento equipamento1 = new Equipamento("Equipamento 1", tipo1, null, null);
   
        return ResponseEntity.ok(equipamento1); 
    }

    @PostMapping("/")
    public ResponseEntity<NewEquipamentDto> adicionarEquipamento(@RequestBody NewEquipamentDto newEquipamentDto) {
        System.out.println(newEquipamentDto);
   
        return ResponseEntity.ok(newEquipamentDto); 
    }
}

