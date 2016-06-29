package by.iba.gomel.managers;

import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import by.iba.gomel.Constants;

/**
 * It's handler of XSL.
 */
public class XSLManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(XSLManager.class);

    /**
     * 
     * @param context
     *            soap message context.
     * @param isOutbound
     *            out bound.
     * @return path to concrete xsl.
     */
    public String getPathXSL(final SOAPMessageContext context, final boolean isOutbound) {
        String path = null;
        try {
            final SOAPPart part = context.getMessage().getSOAPPart();
            final SOAPEnvelope env = part.getEnvelope();
            final SOAPHeader header = env.getHeader();
            if (header == null) {
                return path;
            }
            final NodeList companyNode = header
                    .getElementsByTagName(Constants.ELEMENT_NAME_COMPANY);
            final String company = companyNode.item(0).getChildNodes().item(0).getNodeValue();
            if (company.equals(Constants.NAME_COMPANY_GOOGLE)) {
                if (!isOutbound) {
                    path = Constants.PATH_XSL_INBOUND_TEMPLATE_GOOGLE;
                }
            }
        } catch (final SOAPException e) {
            XSLManager.LOGGER.error(Constants.SOAP_EXCEPTION, e);
        }
        return path;
    }
}
