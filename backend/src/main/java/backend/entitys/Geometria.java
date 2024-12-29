package backend.entitys;

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
    private int id;
    private String nome;
    private Map<String, Object> propriedades;
    private Map<String, String> formulas;

}