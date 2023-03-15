package service.impl;

import dao.TriangleProvider;
import model.Triangle;
import model.TriangleType;
import service.TriangleService;
import service.TriangleValidateService;

import java.util.List;
import java.util.Set;

public class TriangleValidateServiceImpl implements TriangleValidateService {
    private final TriangleService triangleService;
    private final TriangleProvider triangleProvider;

    public TriangleValidateServiceImpl(TriangleProvider triangleProvider, TriangleService triangleService) {
        this.triangleProvider = triangleProvider;
        this.triangleService = triangleService;
    }

    private boolean isValidTriangle(Triangle triangle) {
        boolean isValid = true;
        if (!triangleService.isValidTriangle(triangle.getA(), triangle.getB(), triangle.getC())) {
            isValid = false;
        }

        if (triangleService.getArea(triangle.getA(), triangle.getB(), triangle.getC()) != triangle.getArea()) {
            isValid = false;
        }

        Set<TriangleType> types = triangleService.getType(triangle.getA(), triangle.getB(), triangle.getC());
        if (!types.equals(triangle.getTypes())) {
            isValid = false;
        }

        return isValid;
    }

    public boolean isAllValid() {
        boolean isAllValid = false;
        List<Triangle> triangles = triangleProvider.getAll();

        for (var triangle : triangles) {
            if (isValidTriangle(triangle)) {
                isAllValid = true;
                break;
            }
        }

        return isAllValid;
    }

    public boolean isValid(long id) {
        Triangle triangle = triangleProvider.getById(id);
        boolean isValid = isValidTriangle(triangle);
        return isValid;
    }
}
