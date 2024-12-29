package backend.entitys;

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
    private int id;
    private String nome;
    private Tipo tipo;
    private Geometria geometria;
    
}
