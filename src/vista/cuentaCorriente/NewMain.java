/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaCorriente;

import java.lang.ref.Reference;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.XMLSignatureFactory;

/**
 *
 * @author MYS1
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        javax.xml.crypto.dsig.Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null));
    }
    
}
