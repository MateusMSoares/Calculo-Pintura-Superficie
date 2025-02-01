package backend.entitys;

import java.util.List;
import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Geometria {
    private String nome;
    private List<String> propriedades_fundamentais;
    private Map<String, Object> propriedades;
    private Map<String, Object> formulas;

}
