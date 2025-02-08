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
public class BV {
    private Double diametro;
    private Double quantidade;
    private Double resultado;


    public void calcularResultado() {
        resultado = this.diametro * this.quantidade;
        String valorFormatado = String.format("%.2f", resultado);
    
        valorFormatado = valorFormatado.replace(',', '.');
        
        this.resultado = Double.parseDouble(valorFormatado);
    }
}
