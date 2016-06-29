package by.iba.gomel.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import by.iba.gomel.Constants;
import by.iba.gomel.model.ListRecords;
import by.iba.gomel.model.Record;

import com.ibm.ws.util.Base64;

/**
 * This class uses for business logic of application associated with record.
 */
public class RecordLogic implements Serializable {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory.getLogger(RecordLogic.class);

    /**
     * 
     * @param soapMessage
     *            soapMessage.
     * @param soapURL
     *            soapURL.
     * @return node list.
     */
    public NodeList getResponseFromSOAP(final SOAPMessage soapMessage, final String soapURL) {
        SOAPConnectionFactory soapConnectionFactory;
        SOAPMessage soapResponse = null;
        NodeList returnedResult = null;
        SOAPConnection soapConnection = null;
        try {
            soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();
            soapResponse = soapConnection.call(soapMessage, soapURL);
            returnedResult = soapResponse.getSOAPBody().getElementsByTagName(
                    Constants.ELEMENT_RETURN);
        } catch (final UnsupportedOperationException e) {
            RecordLogic.LOGGER.error(Constants.UNSUPPORTED_OPERATION_EXCEPTION, e);
        } catch (final SOAPException e) {
            RecordLogic.LOGGER.error(Constants.SOAP_EXCEPTION, e);
        } finally {
            try {
                soapConnection.close();
            } catch (final SOAPException e) {
                RecordLogic.LOGGER.error(Constants.SOAP_EXCEPTION, e);
            }
        }
        return returnedResult;
    }

    /**
     * 
     * @param mapValues
     *            values of parameters.
     * @return soap message.
     */
    public SOAPMessage createSOAPRequest(final Map<String, String> mapValues) {
        SOAPMessage soapMessage = null;
        try {
            final MessageFactory messageFactory = MessageFactory.newInstance();
            soapMessage = messageFactory.createMessage();
            final SOAPPart soapPart = soapMessage.getSOAPPart();
            final SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.addNamespaceDeclaration(Constants.NAMESPACE_GOOGLE,
                    Constants.NAMESPACE_GOOGLE_URL);

            final SOAPHeader header = soapMessage.getSOAPHeader();
            final SOAPElement elemenetCompany = header.addChildElement(
                    Constants.HEADER_ELEMENT_COMPANY, Constants.NAMESPACE_GOOGLE);
            elemenetCompany.addTextNode(Constants.HEADER_ELEMENT_COMPANY_GOOGLE_VALUE);

            final SOAPBody soapBody = envelope.getBody();
            final SOAPElement soapBodyElem = soapBody
                    .addChildElement(Constants.ELEMENT_HANDLER_NAME);
            for (final String value : mapValues.keySet()) {
                final SOAPElement soapElement = soapBodyElem.addChildElement(value);
                soapElement.addTextNode(mapValues.get(value));
            }
        } catch (final Exception e) {
            RecordLogic.LOGGER.info(Constants.EXCEPTION_EXCEPTION, e);
        }
        return soapMessage;
    }

    /**
     * 
     * @param element
     *            node element.
     * @return list of records.
     */
    public ListRecords unmarshallFromNode(final Node element) {
        JAXBContext context;
        ListRecords result = null;
        try {
            context = JAXBContext.newInstance(ListRecords.class);
            final Unmarshaller unMarshaller = context.createUnmarshaller();
            result = (ListRecords) unMarshaller.unmarshal(element);
        } catch (final JAXBException e) {
            RecordLogic.LOGGER.error(Constants.JAXB_EXCEPTION, e);
        }
        return result;
    }

    /**
     * 
     * @param pathToFile
     *            path to file.
     * @return byte of file.
     */
    public String getByteFile(final String pathToFile) {
        FileInputStream fileInputStream = null;
        final File file = new File(pathToFile);
        final byte[] bFile = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (final IOException e) {
            RecordLogic.LOGGER.error(Constants.IO_EXCEPTION, e);
        }
        return Base64.encode(bFile);
    }

    /**
     * 
     * @param event
     *            file upload event.
     * @return path to file.
     */
    public String getPathFile(final FileUploadEvent event) {
        final UploadedFile uploadedFile = event.getUploadedFile();
        final String path = Constants.PATH_VALUE_PHOTOS + File.separator + new Date().getTime()
                + uploadedFile.getName();
        final File file = new File(path);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = uploadedFile.getInputStream();
            out = new FileOutputStream(file);
            int read = 0;
            final byte[] bytes = new byte[Constants.ONE_KB];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (final FileNotFoundException e) {
            RecordLogic.LOGGER.error(Constants.FILE_NOT_FOUND_EXCEPTION, e);
        } catch (final IOException e) {
            RecordLogic.LOGGER.error(Constants.IO_EXCEPTION, e);
        } finally {
            try {
                in.close();
                out.flush();
                out.close();
            } catch (final IOException e) {
                RecordLogic.LOGGER.error(Constants.IO_EXCEPTION, e);
            }
        }
        return path;
    }

    /**
     * 
     * @param valuesMap
     * @param record
     * @return
     */
    public Map<String, String> addRecordInMap(final Map<String, String> valuesMap,
            final Record record) {
        final Format formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        valuesMap.put(Constants.PARAMETER_RECORD_ADDRESS, record.getAddress());
        if (record.getBirthDate() != null) {
            valuesMap.put(Constants.PARAMETER_RECORD_BIRTHDAY,
                    formatter.format(record.getBirthDate()));
        }
        valuesMap.put(Constants.PARAMETER_RECORD_FULLNAME, record.getFullName());
        valuesMap.put(Constants.PARAMETER_RECORD_MAIL, record.getMail());
        valuesMap.put(Constants.PARAMETER_RECORD_PHONE_NUMBER, record.getPhoneNumber());
        valuesMap.put(Constants.PARAMETER_RECORD_PATH_FILE, record.getPathFile());
        valuesMap.put(Constants.PARAMETER_RECORD_ITEM, Integer.toString(record.getItem()));
        return valuesMap;
    }

}
