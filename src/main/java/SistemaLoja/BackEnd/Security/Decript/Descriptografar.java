package SistemaLoja.BackEnd.Security.Decript;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Descriptografar {
    /*
     * Eu recebo a chave simétrica criptografada (CSC), descriptografo utilizando a chave privada.
     * com isso eu uso a CSC para descriptografar o texto criptografado.
     */
    public String descriptografar(SecretKey chaveSimetrica, String textoCriptografado) {
        try {
            Cipher cipherSimetrico = Cipher.getInstance("AES");
            cipherSimetrico.init(Cipher.DECRYPT_MODE, chaveSimetrica);

            byte[] bytesCriptografados = Base64.getDecoder().decode(textoCriptografado);
            byte[] decryptedBytes = cipherSimetrico.doFinal(bytesCriptografados);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao descriptografar o texto!");
            return null;
        }
    }
}
