package security;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class DES {

    public byte[] descifrarDES(byte[] textoCifrado, SecretKey clave) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, clave);

            byte[] textoDescifrado = new byte[0];

            textoDescifrado = cipher.doFinal(textoCifrado);
            return textoDescifrado;
        } catch (IllegalBlockSizeException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        } catch (NoSuchPaddingException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (InvalidKeyException e) {
            return null;
        }
    }

}
