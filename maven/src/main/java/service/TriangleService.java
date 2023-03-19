package service;

import model.TriangleType;

import java.util.EnumSet;
import java.util.Set;

public interface TriangleService {
    boolean isValidTriangle(Long id);
    Set<TriangleType> getType(double a, double b, double c);
    double getArea(double a, double b, double c);
    void save(Long Id, double a, double b, double c, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area);
}
