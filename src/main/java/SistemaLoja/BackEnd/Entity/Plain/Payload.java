package SistemaLoja.BackEnd.Entity.Plain;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
    @Setter(AccessLevel.NONE)
    private String secretKey;
    private List<Object> values;

    public Payload(String secretKey) {
        this.secretKey = secretKey;
    }
}
