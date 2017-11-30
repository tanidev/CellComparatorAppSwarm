package com.rhcloud.cellcomparator.cdi.producer;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.cellcomparator.cdi.qualifier.SmartphoneDAO;
import com.rhcloud.cellcomparator.dao.impl.SmartphoneDAOImpl;

/** Simple resource to test correct deployment and accessibility of {@code MyDS}.
 */
@WebServlet("/smartphonesTest")
public class MyResource extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 733198175798256040L;
	
	@Inject @SmartphoneDAO
	private SmartphoneDAOImpl smartPhoneDAOImpl;

	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		smartPhoneDAOImpl.findAll().forEach(smartphone -> {
			try {
				resp.getWriter().write(smartphone.getSmartphoneId() + "-" + smartphone.getName() + System.lineSeparator());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
        
    }
}
