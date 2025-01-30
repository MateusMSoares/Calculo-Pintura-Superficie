package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.TipoDTO;
import backend.entitys.Tipo;
import backend.service.TipoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipo")
public class TipoController {

    @Autowired
    private TipoService tipoService;


    @GetMapping("/")
    public ResponseEntity<List<TipoDTO>> listarTipos() {
        try {
            List<TipoDTO> tipos = tipoService.carregarTipos();
            return ResponseEntity.ok(tipos);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }   
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> procurarPorId(@PathVariable String id) {
        try {
            TipoDTO tipo = tipoService.procurarPorId(id);
            return ResponseEntity.ok(tipo);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/criar")
    public void criarTipo(@RequestBody Tipo tipo) {

        System.out.println(tipo);
        try {
            tipoService.criarTipo(tipo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar tipos em lote: " + e.getMessage());
        }
    
    }

}
