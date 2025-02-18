package backend.entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Isolamento {
    private Double altura;
    private Double resultado;


    public void calcularResultado(Double altura) {
        if (this.altura == null) {
            this.resultado = null;
            return;
        }
        resultado = altura * this.altura;
        String valorFormatado = String.format("%.2f", resultado);
    
        valorFormatado = valorFormatado.replace(',', '.');
        
        this.resultado = Double.parseDouble(valorFormatado);
    }
}
