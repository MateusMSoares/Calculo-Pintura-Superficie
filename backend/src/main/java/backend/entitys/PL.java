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
public class PL {

    private Double raio;
    private Double lado;
    private Double resultado;

    public void calcularResultado() {
        resultado = lado * 3 * raio;
        String valorFormatado = String.format("%.2f", resultado);
    
        valorFormatado = valorFormatado.replace(',', '.');
        
        this.resultado = Double.parseDouble(valorFormatado);
    }

}
