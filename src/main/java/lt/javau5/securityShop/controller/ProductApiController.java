package lt.javau5.securityShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductApiController {
    @GetMapping("/home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/products")
    public String showProducts() {
        return "products";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

}
