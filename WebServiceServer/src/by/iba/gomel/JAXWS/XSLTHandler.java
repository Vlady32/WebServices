package by.iba.gomel.JAXWS;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.lang.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.managers.XSLManager;

/**
 * Handler of SOAP messages.
 */
public class XSLTHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(XSLTHandler.class);

    @Override
    public void close(final MessageContext arg0) {
    }

    @Override
    public boolean handleFault(final SOAPMessageContext arg0) {
        return true;
    }

    @Override
    public boolean handleMessage(final SOAPMessageContext arg0) {
        final XSLManager xslManager = new XSLManager();
        final Boolean outboundProperty = (Boolean) arg0
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        final String pathToXSL = xslManager.getPathXSL(arg0, outboundProperty);
        if (pathToXSL == null) {
            return true;
        }
        if (!outboundProperty) {
            final String SOAPMessage = convertSOAPToString(arg0.getMessage());
            final String transformedSOAPMessage = transformXML(SOAPMessage, pathToXSL);
            arg0.setMessage(convertStringToSOAP(transformedSOAPMessage));
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    /**
     * 
     * @param text
     *            xml text for transforming.
     * @param pathToXSL
     *            path to xsl file.
     * @return transforming text.
     */
    private String transformXML(final String text, final String pathToXSL) {
        final TransformerFactory factory = TransformerFactory.newInstance();
        StringWriter writer = null;
        try {
            final Transformer transformer = factory.newTransformer(new StreamSource(pathToXSL));
            writer = new StringWriter();
            final StreamResult result = new StreamResult(writer);
            transformer.transform(
                    new StreamSource(new ByteArrayInputStream(text.getBytes(CharEncoding.UTF_8))),
                    result);
        } catch (final TransformerConfigurationException e) {
            XSLTHandler.LOGGER.error(Constants.TRANSFORMER_CONFIGURATION_EXCEPTION, e);
        } catch (final UnsupportedEncodingException e) {
            XSLTHandler.LOGGER.error(Constants.UNSUPPORTED_ENCODING_EXCEPTION, e);
        } catch (final TransformerException e) {
            XSLTHandler.LOGGER.error(Constants.TRANSFORMER_EXCEPTION, e);
        }
        return writer.toString();
    }

    /**
     * 
     * @param mesage
     *            xml message.
     * @return soap message.
     */
    private SOAPMessage convertStringToSOAP(final String mesage) {
        SOAPMessage request = null;
        try {
            final InputStream is = new ByteArrayInputStream(mesage.getBytes(CharEncoding.UTF_8));
            request = MessageFactory.newInstance().createMessage(null, is);
        } catch (final UnsupportedEncodingException e) {
            XSLTHandler.LOGGER.error(Constants.UNSUPPORTED_ENCODING_EXCEPTION, e);
        } catch (final IOException e) {
            XSLTHandler.LOGGER.error(Constants.IO_EXCEPTION, e);
        } catch (final SOAPException e) {
            XSLTHandler.LOGGER.error(Constants.SOAP_EXCEPTION, e);
        }
        return request;
    }

    /**
     * 
     * @param message
     *            soap message.
     * @return xml message.
     */
    private String convertSOAPToString(final SOAPMessage message) {
        String strMsg = null;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            message.writeTo(out);
            strMsg = new String(out.toByteArray());
        } catch (final SOAPException e) {
            XSLTHandler.LOGGER.error(Constants.SOAP_EXCEPTION, e);
        } catch (final IOException e) {
            XSLTHandler.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
        return strMsg;
    }

}
