package br.com.gfsolucoesti.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.gfsolucoesti.filter.CharFilter;
import br.com.gfsolucoesti.filter.LoginFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class LoginFilterConfig {

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  public FilterRegistrationBean characterFilter() {
    log.debug("===================================");
    log.debug("  Config CharFilter.... ");
    log.debug("===================================");
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new CharFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  public FilterRegistrationBean loginFilter() {
    log.debug("===================================");
    log.debug("  Config LoginFilter.... ");
    log.debug("===================================");
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new LoginFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }
}
