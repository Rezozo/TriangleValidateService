package service.impl;

import dao.TriangleDao;
import model.Triangle;
import model.TriangleType;
import service.TriangleService;

import java.util.EnumSet;
import java.util.Set;

public class TriangleServiceImpl implements TriangleService {
    private final TriangleDao triangleDao;
    public TriangleServiceImpl(TriangleDao triangleDao) {
        this.triangleDao = triangleDao;
    }
    public boolean isValidTriangle(double a, double b, double c) {
        Triangle triangle = triangleDao.selectBySides(a, b, c);
        if (triangle == null) return false;
        else return true;
    }

    public Set<TriangleType> getType(double a, double b, double c) {
        try {
            Triangle triangle = triangleDao.selectBySides(a, b, c);
            Set<TriangleType> type = triangle.getTypes();
            return type;
        } catch (Error error) {
            throw new Error("Model.Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
        }
    }

    public double getArea(double a, double b, double c) {
        try {
            Triangle triangle = triangleDao.selectBySides(a, b, c);
            return triangle.getArea();
        } catch(Error error) {
            throw new Error("Model.Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
        }
    }

    public void save(long Id, double A, double B, double C, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area) {
        Triangle triangle = new Triangle(Id, A, B, C, Type, isValidTriangle, Area);
        triangleDao.save(triangle);
    }
}
