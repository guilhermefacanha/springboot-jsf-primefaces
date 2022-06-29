package br.com.gfsolucoesti.utils;

import java.lang.management.ManagementFactory;
import java.util.Enumeration;
import java.util.Objects;
import javax.faces.context.FacesContext;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class WebUtils {

  public static String getBaseUrl() {
    HttpServletRequest request =
        (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String scheme = request.getScheme() + "://";
    String serverName = request.getServerName();
    String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
    String contextPath = request.getContextPath();
    String url = scheme + serverName + serverPort + contextPath;
    return url;
  }

  public static String getLocalContext() {
    HttpServletRequest request =
        (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String scheme = "http://";
    String serverPort = getServerInternalPort();
    String contextPath = request.getContextPath();
    String url = scheme + "localhost:" + serverPort + contextPath;
    return url;
  }

  public static String getServerInternalPort() {
    try {
      int port =
          NumberUtils.toInt(
              Objects.toString(
                  ManagementFactory.getPlatformMBeanServer()
                      .getAttribute(
                          new ObjectName(
                              "jboss.as:socket-binding-group=standard-sockets,socket-binding=http"),
                          "port")),
              8080);
      int offset =
          NumberUtils.toInt(
              Objects.toString(
                  ManagementFactory.getPlatformMBeanServer()
                      .getAttribute(
                          new ObjectName("jboss.as:socket-binding-group=standard-sockets"),
                          "port-offset")),
              0);

      return String.valueOf(port + offset);
    } catch (Exception e) {
      return "8080";
    }
  }

  public static String getContext() {
    String server = getServerNamePort();
    String app = getAppName();
    return server + app;
  }

  public static String getAppName() {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
  }

  private static String getServerNamePort() {
    String http = FacesContext.getCurrentInstance().getExternalContext().getRequestScheme() + "://";
    String port =
        ":" + FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();

    HttpServletRequest req =
        (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

    String origin = StringUtils.defaultString(req.getHeader("Origin"), "");
    if (StringUtils.isNotEmpty(origin)) {
      return origin;
    }

    return http
        + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
        + port;
  }

  @SuppressWarnings("unused")
  private static String printAllHeaders(HttpServletRequest req) {
    StringBuffer str = new StringBuffer();
    Enumeration<String> headerNames = req.getHeaderNames();

    while (headerNames.hasMoreElements()) {

      String headerName = headerNames.nextElement();

      Enumeration<String> headers = req.getHeaders(headerName);
      while (headers.hasMoreElements()) {
        String headerValue = headers.nextElement();
        str.append(headerName + " : " + headerValue);
        str.append(System.lineSeparator());
      }
    }

    return str.toString();
  }
}
