package backend.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TipoDTO {
    private String id;
    private String nome;
    private Map<String, Object> propriedades;
}
