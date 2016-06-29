package by.iba.gomel.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import by.iba.gomel.Constants;
import by.iba.gomel.managers.MessageManager;

@ManagedBean(name = Constants.SUB_BEAN_NAME)
@SessionScoped
/**
 * This bean uses for additional requests.
 */
public class SubsidiaryController implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<String>      listItemsMenu;
    private String            linkPage;

    public SubsidiaryController() {
        // Empty constructor
    }

    /**
     * Initialization items of menu.
     */
    private void initList() {
        listItemsMenu = new ArrayList<String>();
        final Locale currentLocale = new SessionController().getCurrentLocale();
        final MessageManager messageManager = new MessageManager(currentLocale);
        if (new SessionController().getUserType().equals(Constants.TYPE_ADMIN)) {
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_ADDING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_EDITING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_CONTROL));
        } else if (new SessionController().getUserType().equals(Constants.TYPE_USER)) {
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_VIEWING));
            listItemsMenu.add(messageManager.getProperty(Constants.MESSAGE_MENU_SEARCHING));
        }
    }

    /**
     * 
     * @return linkPage.
     */
    public String determineAction() {
        final Map<String, String> mapParameters = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        final int linkType = Integer.parseInt(mapParameters.get(Constants.ATTRIBUTE_LINK_TYPE));
        final String typeUser = mapParameters.get(Constants.ATTRIBUTE_TYPE_USER);
        if (linkType == Constants.NUMBER_VIEW_PAGE) {
            linkPage = Constants.PAGE_VIEW;
        } else if (linkType == Constants.NUMBER_ADD_PAGE) {
            if (typeUser.equals(Constants.TYPE_USER)) {
                linkPage = Constants.PAGE_SEARCH;
            } else {
                linkPage = Constants.PAGE_ADD;
            }
        } else if (linkType == Constants.NUMBER_EDIT_PAGE) {
            linkPage = Constants.PAGE_EDIT;
        } else if (linkType == Constants.NUMBER_SEARCH_PAGE) {
            linkPage = Constants.PAGE_SEARCH;
        } else if (linkType == Constants.NUMBER_CONTROL_PAGE) {
            linkPage = Constants.PAGE_CONTROL;
        }
        return linkPage;
    }

    public String getLinkPage() {
        return linkPage;
    }

    public void setLinkPage(final String linkPage) {
        this.linkPage = linkPage;
    }

    public List<String> getListItemsMenu() {
        initList();
        return listItemsMenu;
    }

    public void setListItemsMenu(final List<String> listItemsMenu) {
        this.listItemsMenu = listItemsMenu;
    }

}
