package Dao;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class Lectura {
    private static final String PASSWORD = "myPassword";
    private static final String KEY = "myKey";
    private static final String ALGORITHM = "JCEKS";

    public byte[] lecturaOutput(String str) throws IOException {
        FileInputStream fis = new FileInputStream(str);
        byte[] data = new byte[fis.available()];
        fis.read(data);
        fis.close();

        return data;
    }

    public KeyPair getKeyPair(String file) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
            IOException, UnrecoverableKeyException {
        // Cargar las claves desde el archivo
        KeyStore ks2 = KeyStore.getInstance(ALGORITHM);
        FileInputStream fis = new FileInputStream(file);
        ks2.load(fis, PASSWORD.toCharArray());
        KeyPair kp2 = new KeyPair(ks2.getCertificate(KEY).getPublicKey(),
                (PrivateKey) ks2.getKey(KEY, PASSWORD.toCharArray()));
        return kp2;
    }

    public SecretKey getSecretKey(String file) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
            IOException, UnrecoverableKeyException {
        KeyStore ks2 = KeyStore.getInstance(ALGORITHM);
        FileInputStream fis = new FileInputStream(file);
        ks2.load(fis, PASSWORD.toCharArray());
        SecretKey secretKey2 = (SecretKey) ks2.getKey(KEY, PASSWORD.toCharArray());
        return secretKey2;
    }
}
