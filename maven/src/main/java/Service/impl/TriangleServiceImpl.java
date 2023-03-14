package Service.impl;

import Dao.impl.TriangleDaoImpl;
import Model.Triangle;
import Model.TriangleType;
import Service.ITriangleService;

import java.util.EnumSet;
import java.util.Set;

public class TriangleServiceImpl implements ITriangleService {
    private final TriangleDaoImpl triangleDao = new TriangleDaoImpl();
    public boolean isValidTriangle(double a, double b, double c) {
        Triangle triangle = triangleDao.SelectBySides(a, b, c);
        if (triangle == null) return false;
        else return true;
    }

    public Set<TriangleType> GetType(double a, double b, double c) {
        try {
            Triangle triangle = triangleDao.SelectBySides(a, b, c);
            Set<TriangleType> type = triangle.getTypes();
            return type;
        } catch (Error error) {
            throw new Error("Model.Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
        }
    }

    public double GetArea(double a, double b, double c) {
        try {
            Triangle triangle = triangleDao.SelectBySides(a, b, c);
            return triangle.getArea();
        } catch(Error error) {
            throw new Error("Model.Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
        }
    }

    public void ChangeValid(long Id) {
        triangleDao.Update(Id);
    }

    public void Save(long Id, double A, double B, double C, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area) {
        Triangle triangle = new Triangle(Id, A, B, C, Type, isValidTriangle, Area);
        triangleDao.Save(triangle);
    }
}
