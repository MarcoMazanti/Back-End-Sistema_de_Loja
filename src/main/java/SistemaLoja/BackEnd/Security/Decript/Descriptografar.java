package SistemaLoja.BackEnd.Security.Decript;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class Descriptografar {
    @Value("${chave.publica}")
    private String chavePublicaString;
    @Value("${chave.privada}")
    private String chavePrivadaString;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    private void init() {
        try {
            publicKey = getPublicKeyFromBase64(chavePublicaString);
            privateKey = getPrivateKeyFromBase64(chavePrivadaString);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao iniciar a chave privada!");
        }
    }

    /*
     * Eu recebo a chave simétrica criptografada (CSC), descriptografo utilizando a chave privada.
     * com isso eu uso a CSC para descriptografar o texto criptografado.
     */
    public String descriptografar(String chaveSimetrica, String textoCriptografado) {
        try {
            Cipher cipherAssimetrico = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipherAssimetrico.init(Cipher.DECRYPT_MODE, privateKey);

            String secretKeyString = new String(cipherAssimetrico.doFinal(Base64.getDecoder().decode(chaveSimetrica)));
            SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKeyString), "AES");

            Cipher cipherSimetrico = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherSimetrico.init(Cipher.DECRYPT_MODE, secretKey);

            return new String(Base64.getEncoder().encode(cipherSimetrico.doFinal(textoCriptografado.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao descriptografar o texto!");
            return null;
        }
    }

    private PublicKey getPublicKeyFromBase64(String base64PublicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private PrivateKey getPrivateKeyFromBase64(String base64PrivateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
