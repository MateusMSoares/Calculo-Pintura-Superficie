package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.entitys.Tipo;
import backend.service.TipoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipo")
public class TipoController {

    @Autowired
    private TipoService tipoService;


    @GetMapping("/")
    public ResponseEntity<List<Tipo>> listarTipos() {
        try {
            List<Tipo> tipos = tipoService.carregarTipos();
            return ResponseEntity.ok(tipos);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }   
    }

}
