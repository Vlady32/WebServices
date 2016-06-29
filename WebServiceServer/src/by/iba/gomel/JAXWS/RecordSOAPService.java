package by.iba.gomel.JAXWS;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.iba.gomel.Constants;
import by.iba.gomel.factories.DatabaseDAOFactory;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.managers.HandlerManager;
import by.iba.gomel.model.DataRequest;
import by.iba.gomel.model.DataResponse;

/**
 * This class describes method for SOAP service.
 */
@WebService
@HandlerChain(file = Constants.PATH_HANDLER_CHAIN)
@Stateless
public class RecordSOAPService implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = Constants.PERSISTENT_NAME_DB2)
    private EntityManager     em;
    private DatabaseDAO       data;

    @WebMethod(exclude = true)
    @PostConstruct
    public void init() {
        data = DatabaseDAOFactory.getInstance(em);
    }

    /**
     * 
     * @param dataRequest
     *            data of request.
     * @param parameterEvent
     *            parameter of event.
     * @return response from soap service.
     */
    public DataResponse handle(final DataRequest dataRequest, final int parameterEvent) {
        final HandlerManager handlerManager = new HandlerManager(dataRequest, parameterEvent, data);
        return handlerManager.getResponse();
    }
}
