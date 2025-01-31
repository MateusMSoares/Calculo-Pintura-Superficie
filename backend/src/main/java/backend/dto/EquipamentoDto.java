package backend.dto;

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
public class EquipamentoDto {
    private String id;
    private String nome;
    private TipoDTO tipo;
    private GeometriaDTO geometria;
    
}
