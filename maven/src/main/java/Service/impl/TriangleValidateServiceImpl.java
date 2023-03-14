package Service.impl;

import Dao.ITriangleProvider;
import Model.Triangle;
import Model.TriangleType;
import Service.ITriangleService;
import Service.ITriangleValidateService;

import java.util.Set;

public class TriangleValidateServiceImpl implements ITriangleValidateService {
    private final ITriangleService triangleService;
    private final ITriangleProvider triangleProvider;

    public TriangleValidateServiceImpl(ITriangleProvider triangleProvider, ITriangleService triangleService) {
        this.triangleProvider = triangleProvider;
        this.triangleService = triangleService;
    }

    private boolean isValidTriangle(Triangle triangle) {
        boolean isValid = true;
        if (!triangleService.isValidTriangle(triangle.getA(), triangle.getB(), triangle.getC())) {
            isValid = false;
        }

        if (triangleService.GetArea(triangle.getA(), triangle.getB(), triangle.getC()) != triangle.getArea()) {
            isValid = false;
        }

        Set<TriangleType> types = triangleService.GetType(triangle.getA(), triangle.getB(), triangle.getC());
        if (!types.equals(triangle.getTypes())) {
            isValid = false;
        }

        return isValid;
    }

    public boolean isAllValid() {
        boolean isAllValid = false;

        for (var triangle : triangleProvider.GetAll()) {
            if (isValidTriangle(triangle)) {
                isAllValid = true;
                break;
            }
        }
        if (isAllValid) {
            for (Triangle triangles :
                    triangleProvider.GetAll()){
                triangleService.ChangeValid(triangles.getId());
            }
        }
        return isAllValid;
    }

    public boolean isValid(long id) {
        Triangle triangle = triangleProvider.GetById(id);
        boolean isValid = isValidTriangle(triangle);

        if (isValid) {
            triangleService.ChangeValid(triangle.getId());
        }
        return isValid;
    }
}
