package controller;

import Dao.Lectura;
import security.AES;
import security.ClavePublica;
import security.DES;
import security.RSA;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class Controller {
    private Lectura lectura = new Lectura();
    private DES des = new DES();
    private  RSA rsa = new RSA();
    private   AES aes = new AES();
    ClavePublica clavePublica = new ClavePublica();

    public Controller () {
    }

    public void init () throws Exception {

        SecretKey keyDes = lectura.getSecretKey("files/key1-2.jceks");
        SecretKey keyAes = lectura.getSecretKey("files/key2-2.jceks");
        KeyPair keyClavePublica = lectura.getKeyPair("files/key3-2.jceks");
        KeyPair keyRsa = lectura.getKeyPair("files/key4-2.jceks");

        byte[] output = lectura.lecturaOutput("files/output2.txt");

        System.out.println(new String(descifrarOutput(output,keyDes,keyAes,keyClavePublica,keyRsa)));
        System.out.println("final");
    }

    private byte[] descifrarOutput(byte[] output, SecretKey keyDes, SecretKey keyAes, KeyPair keyClavePublica, KeyPair keyRsa) throws Exception {

        if(des.descifrarDES(output,keyDes)!=null) {
            output = des.descifrarDES(output,keyDes);
            output = descifrarOutput(output, keyDes, keyAes, keyClavePublica, keyRsa);
        };
        if(rsa.descifrarRSA(output,keyRsa)!=null){
            output = rsa.descifrarRSA(output,keyRsa);
            output = descifrarOutput(output, keyDes, keyAes, keyClavePublica, keyRsa);
        };
        if(aes.descifrarAES(output,keyAes)!=null){
            output = aes.descifrarAES(output,keyAes);
            output = descifrarOutput(output, keyDes, keyAes, keyClavePublica, keyRsa);
        };
        if(clavePublica.descifrar(output,keyClavePublica.getPrivate())!=null){
            output = clavePublica.descifrar(output,keyClavePublica.getPrivate());
            output = descifrarOutput(output, keyDes, keyAes, keyClavePublica, keyRsa);
        }

        return output;
    }

}
