package com.wehavescience.jetty;

import lombok.SneakyThrows;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
public class ApplicationRunner {
    public static void main(String[] args)  {
        new ApplicationRunner().run();
    }

    @SneakyThrows
    public void run() {
        Server server = new Server(8080);

        ProtectionDomain domain = this.getClass().getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar(location.toExternalForm());

        webAppContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");

        server.setHandler(webAppContext);

        server.start();
        server.join();
    }
}
