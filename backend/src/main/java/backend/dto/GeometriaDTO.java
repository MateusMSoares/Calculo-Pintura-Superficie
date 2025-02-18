package backend.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class GeometriaDTO {
    private String id;
    private String nome;
    private List<String> propriedades_fundamentais;
    private Map<String, Object> propriedades;
    private Map<String, Object> formulas;
}
