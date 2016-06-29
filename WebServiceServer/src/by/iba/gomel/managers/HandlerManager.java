package by.iba.gomel.managers;

import by.iba.gomel.Constants;
import by.iba.gomel.JAXWS.RecordSOAPBusiness;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.model.DataRequest;
import by.iba.gomel.model.DataResponse;

/**
 * It's handler for processing SOAP requests and redirecting to special methods.
 */
public class HandlerManager {

    private DataRequest dataRequest;
    private int         papameterEvent;
    private DatabaseDAO data;

    public HandlerManager(final DataRequest dataRequest, final int papameterEvent,
            final DatabaseDAO data) {
        this.dataRequest = dataRequest;
        this.papameterEvent = papameterEvent;
        this.data = data;
    }

    /**
     * 
     * @return response of soap message.
     */
    public DataResponse getResponse() {
        final RecordSOAPBusiness recordSOAPBusiness = new RecordSOAPBusiness(data);
        DataResponse dataResponse = null;
        if (papameterEvent == Constants.PARAMETER_EVENT_GET_ALL_RECORDS) {
            dataResponse = recordSOAPBusiness.getAllRecords(dataRequest);
        } else if (papameterEvent == Constants.PARAMETER_EVENT_GET_QUANTITY_PAGES) {
            dataResponse = recordSOAPBusiness.getQuantityPages();
        } else if (papameterEvent == Constants.PARAMETER_EVENT_ADD_RECORD) {
            dataResponse = recordSOAPBusiness.addRecordInDB(dataRequest);
        } else if (papameterEvent == Constants.PARAMETER_EVENT_CHANGE_RECORD) {
            dataResponse = recordSOAPBusiness.changeRecordInDB(dataRequest);
        } else if (papameterEvent == Constants.PARAMETER_EVENT_DELETE_RECORD) {
            dataResponse = recordSOAPBusiness.deleteRecordInDB(dataRequest);
        } else if (papameterEvent == Constants.PARAMETER_EVENT_SEARCH_RECORDS) {
            dataResponse = recordSOAPBusiness.searchRecord(dataRequest);
        }
        return dataResponse;
    }
}
