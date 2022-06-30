package br.com.gfsolucoesti.main;

import java.io.Serializable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStart implements Serializable {

  private static final long serialVersionUID = -6355130499515341583L;

  public static void main(String[] args) {
    SpringApplication.run(ApplicationStart.class, args);
  }
}
