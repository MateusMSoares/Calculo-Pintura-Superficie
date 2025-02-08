package backend.entitys;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Tipos {
    TORRE, 
    TANQUE;

    @JsonValue
    public String toJson() {
        return this.name(); // Retorna o nome do enum como String
    }

    @JsonCreator
    public static Tipos fromString(String tipo) {
        return Arrays.stream(Tipos.values())
                     .filter(t -> t.name().equalsIgnoreCase(tipo)) 
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + tipo));
    }

    public static List<String> getTipos() {
        return Arrays.stream(Tipos.values())
                     .map(Enum::name) // Retorna os nomes dos enums como String
                     .toList();
    }
}
