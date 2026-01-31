package SistemaLoja.BackEnd.Security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class GerarSecretKey {
    @Value("${chave.privada}")
    private String chavePrivadaString;
    private PrivateKey privateKey;

    @PostConstruct
    private void init() {
        try {
            privateKey = getPrivateKeyFromBase64(chavePrivadaString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao iniciar a chave privada!");
        }
    }

    public SecretKey descriptSecretKey(String secretKeyCript) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(secretKeyCript));

            return new SecretKeySpec(bytes, "AES");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private PrivateKey getPrivateKeyFromBase64(String base64PrivateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
