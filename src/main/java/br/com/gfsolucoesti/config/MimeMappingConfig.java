package br.com.gfsolucoesti.config;

import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MimeMappingConfig
    implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

  @Override
  public void customize(ConfigurableServletWebServerFactory factory) {
    MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
    mappings.add("xsd", "text/xml; charset=utf-8");
    mappings.add("otf", "font/opentype");
    mappings.add("ico", "image/x-icon");
    mappings.add("svg", "image/svg+xml");
    mappings.add("svg#exosemibold", "image/svg+xml");
    mappings.add("svg#exobolditalic", "image/svg+xml");
    mappings.add("svg#exomedium", "image/svg+xml");
    mappings.add("svg#exoregular", "image/svg+xml");
    mappings.add("svg#fontawesomeregular", "image/svg+xml");
    mappings.add("eot", "application/vnd.ms-fontobject");
    mappings.add("eot?#iefix", "application/vnd.ms-fontobject");
    mappings.add("ttf", "application/x-font-ttf");
    mappings.add("woff", "application/x-font-woff");
    mappings.add("woff2", "application/x-font-woff2");
    factory.setMimeMappings(mappings);
  }
}
