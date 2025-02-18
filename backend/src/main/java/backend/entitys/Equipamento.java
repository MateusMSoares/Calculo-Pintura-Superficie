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
    private EquipamentoDetails details;

    public void fromTipo(Tipos tipo, EquipamentoDto newEquipamento) {
        if (tipo == Tipos.TORRE || tipo == Tipos.TANQUE) {
            details = new Cilindro(newEquipamento);
        }
    }

    public void CreatefromTipo(Tipos tipo, EquipamentoDto newEquipamento) {
        if (tipo == Tipos.TORRE || tipo == Tipos.TANQUE) {
            this.details = new Cilindro(newEquipamento);
            this.dataHoraCriacao = System.currentTimeMillis();
        }
    }
    
}
