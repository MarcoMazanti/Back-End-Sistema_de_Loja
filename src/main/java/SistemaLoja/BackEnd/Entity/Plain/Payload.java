package SistemaLoja.BackEnd.Entity.Plain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    private String secretKey;
    private List<Object> values;

    public Payload(String secretKey) {
        this.secretKey = secretKey;
    }
}
