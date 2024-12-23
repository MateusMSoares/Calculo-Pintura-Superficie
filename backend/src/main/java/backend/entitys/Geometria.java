package backend.entitys;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Geometria {
    private int id;
    private String nome;
    private Map<String, Double> propriedades;
    private Map<String, String> formulas;

}
