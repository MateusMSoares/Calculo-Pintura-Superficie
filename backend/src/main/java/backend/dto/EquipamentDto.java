package backend.dto;
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
public class EquipamentDto {
    private String nome;
    private int tipoId;
    private int geometriaId;
    private Map<String, Object> propriedades_fundamentais;
    
}
