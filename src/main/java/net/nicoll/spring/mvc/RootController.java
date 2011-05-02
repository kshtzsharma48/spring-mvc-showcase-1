package net.nicoll.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The root controller handling endpoints that are not specific to any feature
 * in particular.
 *
 * @author Stephane Nicoll
 */
@Controller
public class RootController {

    /**
     * Redirects to the welcome page.
     *
     * @return a redirect to /index.html
     */
    @RequestMapping("/foo")
    public String homePage() {
        return "forward:/index.html";
    }
}
