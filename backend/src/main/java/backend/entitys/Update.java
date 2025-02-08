package backend.entitys;

import java.util.List;

import com.google.api.client.util.DateTime;

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
public class Update {
    private String id;
    private Equipamento equipamento;
    private List<DateTime> datas;
}
