package backend.entitys;

import backend.dto.EquipamentoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cilindro extends Equipamento {
    @NonNull
    private Double diametro;
    @NonNull
    private Double altura;
    private Double area;
    private Double volume;
    private BV bv;
    private Isolamento isolamento;
    private PL pl;
    private Double resultado;
    private boolean hasBV;
    private boolean hasPL;
    private boolean hasIsolamento;

    public Cilindro(EquipamentoDto equipamento) {
        super.setNome(equipamento.getNome());
        super.setTipo(equipamento.getTipo());
        super.setDataHoraCriacao(System.currentTimeMillis());
        this.diametro = equipamento.getDiametro();
        this.altura = equipamento.getAltura();
        this.hasBV = equipamento.isHasBV();
        this.hasPL = equipamento.isHasPL();
        this.hasIsolamento = equipamento.isHasIsolamento();
        if (this.hasBV) {
            this.bv = equipamento.getBv();
        }
        if (this.hasPL) {
            this.pl = equipamento.getPl();
        }
        if (this.hasIsolamento) {
            this.isolamento = equipamento.getIsolamento();
        }
        this.calcularResultado();
        this.calcularIsolamento();
    }

    public void calcularResultado() {
        System.out.println("Calculando resultado do cilindro");
        this.area = formatarResultado(diametro * Math.PI * altura);
        this.volume = formatarResultado(Math.PI * Math.pow(diametro / 2, 2) * altura);
    
        if (this.bv != null && this.pl != null) {
            bv.calcularResultado();
            pl.calcularResultado();
            resultado = formatarResultado(area - bv.getResultado() - pl.getResultado());
            return;
        } else if (this.bv != null) {
            bv.calcularResultado();
            resultado = formatarResultado(area - bv.getResultado());
            return;
        } else if (this.pl != null) {
            pl.calcularResultado();
            resultado = formatarResultado(area - pl.getResultado());
            return;
        }
    }
    
    public void calcularIsolamento() {
       if (this.isolamento != null) {
           isolamento.calcularResultado(area);
       }   
    }

    private Double formatarResultado(Double valor) {
        if (valor == null) {
            return null;
        }
        String valorFormatado = String.format("%.2f", valor);
    
        valorFormatado = valorFormatado.replace(',', '.');
        
        return Double.parseDouble(valorFormatado);
    }
    
}
