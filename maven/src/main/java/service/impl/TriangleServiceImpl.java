package service.impl;

import dao.TriangleProvider;
import model.Triangle;
import model.TriangleType;
import service.TriangleService;

import java.util.EnumSet;
import java.util.Set;

public class TriangleServiceImpl implements TriangleService {
    private final TriangleProvider triangleProvider;
    public TriangleServiceImpl(TriangleProvider triangleProvider) {
        this.triangleProvider = triangleProvider;
    }
    public boolean isValidTriangle(Long id) {
        Triangle triangle = triangleProvider.getById(id);
        if (triangle == null) return false;
        else return true;
    }

    public Set<TriangleType> getType(double a, double b, double c) {
        try {
            Triangle triangle = triangleProvider.getBySides(a, b, c);
            return triangle.getTypes();
        } catch (Error error) {
            throw new Error();
        }
    }

    public double getArea(double a, double b, double c) {
        try {
            Triangle triangle = triangleProvider.getBySides(a, b, c);
            return triangle.getArea();
        } catch(Error error) {
            throw new Error();
        }
    }

    public void save(Long Id, double a, double b, double c, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area) {
        triangleProvider.save(new Triangle(Id, a, b, c, Type, isValidTriangle, Area));
    }
}
