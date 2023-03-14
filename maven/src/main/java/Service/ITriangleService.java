package Service;

import Model.TriangleType;

import java.util.EnumSet;
import java.util.Set;

public interface ITriangleService {
    boolean isValidTriangle(double a, double b, double c);
    Set<TriangleType> GetType(double a, double b, double c);
    double GetArea(double a, double b, double c);
    void ChangeValid(long Id);
    void Save(long Id, double A, double B, double C, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area);
}
