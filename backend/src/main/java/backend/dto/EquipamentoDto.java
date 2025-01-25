package backend.dto;

import backend.entitys.Geometria;
import backend.entitys.Tipo;
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
    private Tipo tipo;
    private Geometria geometria;
    
}
