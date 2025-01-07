package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.entitys.Geometria;
import backend.service.GeometriaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/geometria")
public class GeometriaController {

    @Autowired
    private GeometriaService geometriaService;

    @GetMapping("/")
    public ResponseEntity<List<Geometria>> listarGeometrias() {
        try {
            List<Geometria> geometrias = geometriaService.carregarGeometrias();
            return ResponseEntity.ok(geometrias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        }   
    }

    @GetMapping("/{id}")
    public ResponseEntity<Geometria> procuraPorId(@PathVariable int id) {
        System.out.println("Carregando geometria por id " + id);
        try {
            Geometria geometria = geometriaService.procuraPorId(id);
            return ResponseEntity.ok(geometria);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
