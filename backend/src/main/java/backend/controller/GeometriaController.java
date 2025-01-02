package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println("Listando geometrias");
        try {
            List<Geometria> geometrias = geometriaService.carregarGeometrias();
            return ResponseEntity.ok(geometrias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        }   
    }
}
