package backend.dto;

import backend.entitys.BV;
import backend.entitys.Isolamento;
import backend.entitys.PL;
import backend.entitys.Tipos;
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
    private String nome;
    private Double diametro;
    private Double altura;
    private Tipos tipo;
    private boolean hasBV;
    private boolean hasPL;
    private boolean hasIsolamento;
    private BV bv;
    private PL pl;
    private Isolamento isolamento;

}
