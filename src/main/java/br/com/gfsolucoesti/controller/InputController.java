package br.com.gfsolucoesti.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import br.com.gfsolucoesti.entity.UsuarioLogado;
import br.com.gfsolucoesti.util.WebUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@SessionScoped
public class InputController implements Serializable {

  private static final long serialVersionUID = -8082431543568921926L;

  @Autowired
  private Environment env;

  @Getter
  @Setter
  private String state;
  @Getter
  @Setter
  private String name;

  @Getter
  private UsuarioLogado usuarioLogado;
  
  public void init() {
    log.info("Init InputController method");
  }

  public void doAction() {
    FacesContext.getCurrentInstance().addMessage("INFO",
        new FacesMessage("Welcome to Prime Ultima Lab " + name + "!",
            "<b>Ultima Theme</b> and <b>SpringBoot</b> integration via <b>JoinFaces.</b>"));
  }

  public String getAppUrl() {
    return WebUtils.getContext();
  }

  public String getBackendUrl() {
    return env.getProperty("backend.service.url", "http://localhost");
  }
}
