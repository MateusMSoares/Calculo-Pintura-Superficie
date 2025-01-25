package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.dto.GeometriaDTO;
import backend.entitys.Geometria;
import backend.service.GeometriaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/geometria")
public class GeometriaController {

    @Autowired
    private GeometriaService geometriaService;

    @GetMapping("/")
    public ResponseEntity<List<GeometriaDTO>> listarGeometrias() {
        try {
            List<GeometriaDTO> geometrias = geometriaService.carregarGeometrias();
            return ResponseEntity.ok(geometrias);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        }   
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeometriaDTO> procurarPorId(@PathVariable String id) {
        System.out.println("Carregando geometria por id " + id);
        try {
            GeometriaDTO geometria = geometriaService.procurarPorId(id);
            return ResponseEntity.ok(geometria);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/salvarEmLote")
    public void salvarEmLote(List<Geometria> geometrias) {
        try {
            geometriaService.salvarEmLote(geometrias);
        } catch (Exception e) {
            System.out.println("Erro ao salvar geometrias em lote: " + e.getMessage());
        }
    }
}
