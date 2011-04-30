package net.nicoll.spring.mvc.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Stupid and configurable hello world servlet
 *
 * @author Stephane Nicoll
 */
public class HelloWorldServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(HelloWorldServlet.class);

    private String text;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        text = config.getInitParameter("text");
        logger.info("Initialization complete, using [" + text + "]");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter out = resp.getWriter();
        out.println("Hello " + text + "!");
        out.close();
    }
}
