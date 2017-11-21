package arka;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.resource.spi.work.SecurityContext;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Logger;

@Named
@SessionScoped
public class StartBean implements Serializable {
	private static final long serialVersionUID = -6746806481518377966L;
	
	private String name = "Guest";
	





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
