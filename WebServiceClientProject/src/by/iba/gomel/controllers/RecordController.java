package by.iba.gomel.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.soap.SOAPMessage;

import org.richfaces.event.FileUploadEvent;
import org.w3c.dom.NodeList;

import by.iba.gomel.Constants;
import by.iba.gomel.logic.RecordLogic;
import by.iba.gomel.model.ListRecords;
import by.iba.gomel.model.Record;

/**
 * This bean uses for manipulating all requests associated with record.
 */
@ManagedBean(name = Constants.RECORD_BEAN_NAME)
@ViewScoped
public class RecordController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Record            record;
    private Record            currentRecord;
    private List<Record>      searchedRecords;
    private int               currentPage      = Constants.DEFAULT_PAGE;
    private int               quantityPages;
    private String            searchPhrase;
    final RecordLogic         recordLogic;

    public RecordController() {
        record = new Record();
        currentRecord = new Record();
        recordLogic = new RecordLogic();
    }

    /**
     *
     * @return records from database.
     */
    public List<Record> getListRecords() {
        ListRecords listRecords = null;
        if (getQuantityPages() != 0) {
            final Map<String, String> valuesMap = new HashMap<String, String>();
            valuesMap.put(Constants.PARAMETER_CURRENT_PAGE_NAME, Integer.toString(currentPage));
            valuesMap.put(Constants.PARAMETER_EVENT_NAME, Constants.EVENT_GET_ALL_RECORDS);
            final SOAPMessage soapMessage = recordLogic.createSOAPRequest(valuesMap);
            final NodeList returnedElements = recordLogic.getResponseFromSOAP(soapMessage,
                    Constants.URL_SOAP_RECORD_SERVICE);
            listRecords = recordLogic.unmarshallFromNode(returnedElements.item(0));
        }
        return listRecords.getListRecords();
    }

    public int getQuantityPages() {
        final Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put(Constants.PARAMETER_EVENT_NAME, Constants.EVENT_GET_QUANTITY_PAGES);
        final SOAPMessage soapMessage = recordLogic.createSOAPRequest(valuesMap);
        final NodeList returnedElements = recordLogic.getResponseFromSOAP(soapMessage,
                Constants.URL_SOAP_RECORD_SERVICE);
        final int quantityPages = Integer.valueOf(returnedElements.item(0).getChildNodes().item(0)
                .getTextContent());
        setQuantityPages(quantityPages);
        return quantityPages;
    }

    /**
     *
     * @return list of pages.
     */
    public List<Integer> getValuesPages() {
        final List<Integer> values = new ArrayList<Integer>();
        for (int i = 1; i <= quantityPages; i++) {
            values.add(i);
        }
        return values;
    }

    /**
     * this method updates page.
     */
    public void updatePage() {
        final Map<String, String> mapParameters = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        final String page = mapParameters.get(Constants.ATTRIBUTE_PAGE_TYPE);
        currentPage = Integer.parseInt(page);
    }

    /**
     *
     * @return image in base64 code.
     */
    public String getImage() {
        final Record usedRecord;
        final String servletPath = SessionController.getRequest().getServletPath();
        if (servletPath.equals(Constants.PAGE_EDIT) || servletPath.equals(Constants.PAGE_SEARCH)) {
            usedRecord = record;
        } else {
            usedRecord = currentRecord;
        }
        if ((usedRecord.getPathFile() == null) || usedRecord.getPathFile().isEmpty()) {
            usedRecord.setPathFile(Constants.DEFAULT_PATH_NO_IMAGE);
        }
        return recordLogic.getByteFile(usedRecord.getPathFile());
    }

    /**
     *
     * @param event
     *            uses for loading image from user.
     */
    public void addImage(final FileUploadEvent event) {
        record.setPathFile(recordLogic.getPathFile(event));
    }

    /**
     * Adding record.
     **/
    public void addRecord() {
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put(Constants.PARAMETER_EVENT_NAME, Constants.PARAMETER_EVENT_ADD_RECORD);
        valuesMap = recordLogic.addRecordInMap(valuesMap, record);
        final SOAPMessage addRecordMessage = recordLogic.createSOAPRequest(valuesMap);
        final NodeList returnedElements = recordLogic.getResponseFromSOAP(addRecordMessage,
                Constants.URL_SOAP_RECORD_SERVICE);
        final boolean response = Boolean.valueOf(returnedElements.item(0).getChildNodes().item(1)
                .getTextContent());
        if (response == true) {
            SessionController.addMessage(Constants.MESSAGE_ADD_RECORD_SUCCESS,
                    Constants.TAG_ERROR_ADD_FORM);
        } else {
            SessionController.addMessage(Constants.MESSAGE_ADD_RECORD_ERROR,
                    Constants.TAG_ERROR_ADD_FORM);
        }
        record = new Record();
    }

    /**
     * 
     * @param record
     *            record.
     */
    public void deleteRecord(final Record record) {
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put(Constants.PARAMETER_EVENT_NAME, Constants.PARAMETER_EVENT_DELETE_RECORD);
        valuesMap = recordLogic.addRecordInMap(valuesMap, record);
        final SOAPMessage addRecordMessage = recordLogic.createSOAPRequest(valuesMap);
        final NodeList returnedElements = recordLogic.getResponseFromSOAP(addRecordMessage,
                Constants.URL_SOAP_RECORD_SERVICE);
        final boolean response = Boolean.valueOf(returnedElements.item(0).getChildNodes().item(1)
                .getTextContent());
        if (response == true) {
            SessionController.addMessage(Constants.MESSAGE_DELETE_RECORD_SUCCESS, null);
        } else {
            SessionController.addMessage(Constants.MESSAGE_DELETE_RECORD_ERROR, null);
        }
    }

    /**
     * Changing record.
     */
    public void changeRecord() {
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put(Constants.PARAMETER_EVENT_NAME, Constants.PARAMETER_EVENT_CHANGE_RECORD);
        valuesMap = recordLogic.addRecordInMap(valuesMap, record);
        final SOAPMessage addRecordMessage = recordLogic.createSOAPRequest(valuesMap);
        final NodeList returnedElements = recordLogic.getResponseFromSOAP(addRecordMessage,
                Constants.URL_SOAP_RECORD_SERVICE);
        final boolean response = Boolean.valueOf(returnedElements.item(0).getChildNodes().item(1)
                .getTextContent());
        if (response == true) {
            SessionController.addMessage(Constants.MESSAGE_CHANGE_RECORD_SUCCESS, null);
        } else {
            SessionController.addMessage(Constants.MESSAGE_CHANGE_RECORD_ERROR, null);
        }
    }

    /**
     * Searching record.
     */
    public void searchRecord() {
        final Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put(Constants.PARAMETER_EVENT_NAME, Constants.PARAMETER_EVENT_SEARCH_RECORD);
        valuesMap.put(Constants.PARAMETER_SEARCH_PHRASE, searchPhrase);
        final SOAPMessage soapMessage = recordLogic.createSOAPRequest(valuesMap);
        final NodeList returnedElements = recordLogic.getResponseFromSOAP(soapMessage,
                Constants.URL_SOAP_RECORD_SERVICE);
        searchedRecords = recordLogic.unmarshallFromNode(returnedElements.item(0)).getListRecords();
        if ((searchedRecords == null) || !(searchedRecords.size() > 0)) {
            SessionController.addMessage(Constants.MESSAGE_SEARCH_NOTHING, null);
        }
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(final String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public List<Record> getSearchedRecords() {
        return searchedRecords;
    }

    public void setSearchedRecords(final List<Record> searchedRecords) {
        this.searchedRecords = searchedRecords;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(final Record record) {
        this.record = record;
    }

    public void setQuantityPages(final int quantityPages) {
        this.quantityPages = quantityPages;
    }

    public Record getCurrentRecord() {
        return currentRecord;
    }

    public void setCurrentRecord(final Record currentRecord) {
        this.currentRecord = currentRecord;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

}
