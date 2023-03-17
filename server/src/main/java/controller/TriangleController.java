package controller;

import dao.TriangleDao;
import dao.TriangleProvider;
import model.Triangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.TriangleValidateService;

@RestController
public class TriangleController {
    private TriangleValidateService validateService;
    private TriangleProvider provider;
    private TriangleDao triangleDao;
    @Autowired
    public TriangleController(TriangleValidateService validateService, TriangleProvider provider, TriangleDao triangleDao)
    {
        this.validateService = validateService;
        this.provider = provider;
        this.triangleDao = triangleDao;
    }

    @RequestMapping("/errorPage")
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @RequestMapping("/triangle/validateAll")
    public boolean validateAll() {
        if (validateService.isAllValid()) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/triangle/validate")
    public boolean validateOne(@RequestParam("a") double a, @RequestParam("b") double b, @RequestParam("c") double c) {
        try {
            Triangle triangle = triangleDao.selectBySides(a, b, c);
            if (validateService.isValid(triangle.getId())) {
                return true;
            }
        } catch (Error error) {
            return false;
        }
        return false;
    }


}
