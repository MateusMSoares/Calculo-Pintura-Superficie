package backend.entitys;

import backend.dto.GeometriaDTO;
import backend.dto.TipoDTO;
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
public class Equipamento {
    private String nome;
    private TipoDTO tipo;
    private GeometriaDTO geometria;
}
