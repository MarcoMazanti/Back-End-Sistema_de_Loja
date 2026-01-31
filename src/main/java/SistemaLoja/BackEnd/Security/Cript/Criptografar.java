package SistemaLoja.BackEnd.Security.Cript;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Criptografar {
    /*
     * Eu recebo a chave simétrica criptografada (CSC), descriptografo utilizando a chave privada.
     * com isso eu uso a CSC para criptografar o texto.
     */
    public String criptografar(SecretKey chaveSimetrica, String texto) {
        try {
            Cipher cipherSimetrico = Cipher.getInstance("AES");
            cipherSimetrico.init(Cipher.ENCRYPT_MODE, chaveSimetrica);

            byte[] textoCriptografado = cipherSimetrico.doFinal(texto.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(textoCriptografado);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criptografar o texto!");
            return null;
        }
    }
}
