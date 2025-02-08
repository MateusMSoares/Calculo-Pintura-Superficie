package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.service.TipoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipo")
public class TipoController {

    @Autowired
    private TipoService tipoService;


    @GetMapping("/")
    public ResponseEntity<List<String>> listarTipos() {
        List<String> tipos = tipoService.carregarTipos();
        return ResponseEntity.ok(tipos);
 
    }

}
