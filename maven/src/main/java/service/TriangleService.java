package service;

import model.TriangleType;

import java.util.EnumSet;
import java.util.Set;

public interface TriangleService {
    boolean isValidTriangle(double a, double b, double c);
    Set<TriangleType> getType(double a, double b, double c);
    double getArea(double a, double b, double c);
    void save(long Id, double A, double B, double C, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area);
}
