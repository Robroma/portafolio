package security;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

public class ClavePublica {
    public byte[] descifrar(byte[] datosCifrados, PrivateKey clavePrivada) {
        try {
            byte[] claveEnvoltorio = new byte[256];
            System.arraycopy(datosCifrados, 0, claveEnvoltorio, 0, 256);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE, clavePrivada);

            SecretKey claveSimetrica = (SecretKey) cipher.unwrap(claveEnvoltorio, "AES", Cipher.SECRET_KEY);

            int longitudTextoCifrado = datosCifrados.length - 256;
            byte[] textoCifrado = new byte[longitudTextoCifrado];
            System.arraycopy(datosCifrados, 256, textoCifrado, 0, longitudTextoCifrado);

            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, claveSimetrica);

            byte[] textoDescifrado = new byte[0];

            textoDescifrado = cipher.doFinal(textoCifrado);
            return (textoDescifrado);
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
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

    }
}
