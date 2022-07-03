package br.com.gfsolucoesti.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.gfsolucoesti.util.WebUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@SessionScoped
public class DataAnalyzerController implements Serializable {

  private static final long serialVersionUID = -8082431543568921926L;

  @Autowired
  private Environment env;

  @Getter
  @Setter
  private String dataLoad;

  
  public void init() {
    log.debug("===================================");
    log.debug("  Init {} method", this.getClass().getName());
    log.debug("===================================");
  }
  
  public void loadData() {
	  if(StringUtils.isBlank(dataLoad))
		  FacesContext.getCurrentInstance().addMessage("ERROR",
			        new FacesMessage("Data to be analyzed cannot be empty"));
  }


  public String getAppUrl() {
    return WebUtils.getContext();
  }

  public String getBackendUrl() {
    return env.getProperty("backend.service.url", "http://localhost");
  }
}
