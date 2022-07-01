package br.com.gfsolucoesti.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioLogado implements Serializable {

  private static final long serialVersionUID = -301473952626673956L;

  private String nome;
  private OAuthToken oAuthToken;
  private boolean logado;
}
