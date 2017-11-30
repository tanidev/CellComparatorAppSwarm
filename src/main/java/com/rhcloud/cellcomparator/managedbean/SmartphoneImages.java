package com.rhcloud.cellcomparator.managedbean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.rhcloud.cellcomparator.cdi.qualifier.SmartphoneDAO;
import com.rhcloud.cellcomparator.dao.impl.SmartphoneDAOImpl;
import com.rhcloud.cellcomparator.entity.Smartphone;
import com.rhcloud.cellcomparator.task.InitialInputTask;

/**
 * 
 * Classe responsável por carregar as imagens de cada smartphone.
 * FIX: BUG StreamedContent p:graphicImage
 * 
 * @author Estanislau Araújo
 * @since 22/12/2015
 *
 */

@Named
@SessionScoped
public class SmartphoneImages implements Serializable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1873981669698912881L;
	
	@Inject @SmartphoneDAO
    private SmartphoneDAOImpl smartphoneDAOImpl;

    public StreamedContent getImage() throws IOException {
    	
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String smartphoneId = context.getExternalContext().getRequestParameterMap().get("smartphoneId");
//            Smartphone smartphone = smartphoneDAOImpl.findById(Smartphone.class, Integer.valueOf(smartphoneId));
            Smartphone smartphone = smartphoneDAOImpl.findAll().stream().filter(sp -> sp.getSmartphoneId().equals(Integer.parseInt(smartphoneId))).findFirst().get();
            return new DefaultStreamedContent(new ByteArrayInputStream(
            		IOUtils.toByteArray(InitialInputTask.class.getResourceAsStream("image/" + smartphone.getImageName()))
            	), "image/jpg");
        }
    }

}