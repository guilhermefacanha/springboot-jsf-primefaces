package br.com.gfsolucoesti.filter;

import java.io.IOException;
import java.net.URL;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFilter implements Filter {

  private static final String[] PUBLIC_PAGES = {"actuator", "access", "access_associado", "access_inativo", "404", "empty", "error"};
  private static final String LOGIN_PAGE = "login";
  private static final String INDEX_PAGE = "index";
  private static final String SUFFIX = ".xhtml";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.debug("===================================");
    log.debug("Init LoginFilter ...");
    log.debug("===================================");
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    String pagina = getPaginaAtual(servletRequest);
    log.debug("===================================");
    log.debug("  Init Login Filter.... ");
    log.debug("    Login Filter... recurso: {}", pagina);

    boolean resource = !pagina.contains(".jsf") && StringUtils
        .containsAny(pagina, "javax.faces.resource", ".css", ".js");

    if (StringUtils.isBlank(pagina)) {
      navegarParaPagina(LOGIN_PAGE + SUFFIX, servletRequest, servletResponse);
    }

    if (StringUtils.containsAny(pagina, PUBLIC_PAGES)) {
      resource = true;
    }

    if (!resource) {

      boolean isLogado = true;

      if (isLogado && pagina.contains(INDEX_PAGE)) {
        log.trace("    index page... continue...");
      } else if (!pagina.contains(LOGIN_PAGE) && !isLogado) {
        navegarParaPagina(LOGIN_PAGE + SUFFIX, servletRequest, servletResponse);
      } else if (pagina.contains(LOGIN_PAGE) && isLogado) {
        navegarParaPagina("index.xhtml", servletRequest, servletResponse);
      } 
    }
    
    log.debug("===================================");

    filterChain.doFilter(servletRequest, servletResponse);
  }

  @SuppressWarnings("unused")
  private String getPathQuery(ServletRequest req) {
    String url = ((HttpServletRequest) req).getRequestURL().toString();
    String queryString = ((HttpServletRequest) req).getQueryString();
    url = url + "?" + queryString;
    try {
      URL urlObj = new URL(url + "?" + queryString);
      return urlObj.getPath() + "?" + urlObj.getQuery();
    } catch (Exception e) {
      return url;
    }
  }

  @Override
  public void destroy() {
  }

  public static void navegarParaPagina(String pagina, ServletRequest req, ServletResponse resp)
      throws IOException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    response.sendRedirect(request.getContextPath() + "/" + pagina);
    response.flushBuffer();
  }

  public static void forward(String pagina, ServletRequest req, ServletResponse resp)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    request.getRequestDispatcher(pagina).forward(req, resp);
  }

  private String getPaginaAtual(ServletRequest req) {
    HttpServletRequest request = (HttpServletRequest) req;
    String paginaAtual = request.getRequestURI();
    try {
      if (!request.getContextPath().isEmpty()) {
        paginaAtual = paginaAtual.replace(request.getContextPath() + "/", "");
      } else {
        paginaAtual = paginaAtual.replaceFirst("/", "");
      }
      paginaAtual = paginaAtual.replace(".jsf", "").replace(".xhtml", "");
    } catch (Exception e) {
      log.debug("Erro ao determinar a página atual {}", paginaAtual);
      log.debug("Mensagem da Exceção: {}", e.getMessage(), e);
    }
    return paginaAtual;
  }
}
