package controller;

import dao.TriangleProvider;
import lombok.SneakyThrows;
import model.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.TriangleValidateService;


@RestController
public class TriangleController{
    private TriangleValidateService validateService;
    private TriangleProvider provider;
    @Autowired
    public TriangleController(TriangleValidateService validateService, TriangleProvider provider)
    {
        this.validateService = validateService;
        this.provider = provider;
    }

    @RequestMapping("/errorPage")
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @RequestMapping("/triangle/validateAll")
    public boolean validateAll() {
        return validateService.isAllValid();
    }

    @RequestMapping("/triangle/validate")
    @SneakyThrows
    public boolean validateOne(@RequestParam("a") double a, @RequestParam("b") double b, @RequestParam("c") double c) {
        Triangle triangle = provider.getBySides(a, b, c);
        if (triangle != null) {
            return validateService.isValid(triangle.getId());
        } else {
            return false;
        }
    }
}
