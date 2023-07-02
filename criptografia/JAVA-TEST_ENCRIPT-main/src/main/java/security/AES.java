package security;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AES {
    public byte[] descifrarAES(byte[] textoCifrado, SecretKey clave) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
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
