package backend.entitys;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;

import backend.dto.EquipamentoDto;
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
    @DocumentId
    private String id;
    private String nome;
    @PropertyName("tipo")
    private Tipos Tipo;
    private Long dataHoraCriacao;
    private Update historico;

    public static Equipamento fromTipo(Tipos tipo, EquipamentoDto newEquipamento) {
        if (tipo == Tipos.TORRE) {
            return new Cilindro(newEquipamento);
        } else if (tipo == Tipos.TANQUE) {
            return new Tanque();
        }
        return null;
    }
    
}
