/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice.handler;

import app.sis.gob.pe.edi.sisws.CredencialWS;
import app.sis.gob.pe.edi.sisws.ObjectFactory;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MsgHandler implements SOAPHandler<SOAPMessageContext> {

//    private final String VALID_PROPERTY = "RANDOM";

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
            Boolean outbound = (Boolean) context
                    .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

            // if this is an incoming message from the client
            if (outbound != null && outbound) {

                CredencialWS credencial = new CredencialWS();
                credencial.setUsuario("sisws");
                credencial.setClave("g525nke54su");

                ObjectFactory factory = new ObjectFactory();
                JAXBElement<CredencialWS> authHeader = factory.createCredencialWS(credencial);

                // obtaining marshaller which should marshal instance to xml
                Marshaller marshaller = JAXBContext.newInstance(CredencialWS.class).createMarshaller();
                // adding header because otherwise it's null
                SOAPHeader soapHeader = context.getMessage().getSOAPPart().getEnvelope().addHeader();
                // marshalling instance (appending) to SOAP header's xml node
                marshaller.marshal(authHeader, soapHeader);
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
}
