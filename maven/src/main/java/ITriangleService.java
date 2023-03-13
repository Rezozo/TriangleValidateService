import java.util.EnumSet;

public interface ITriangleService {
    boolean isValidTriangle(double a, double b, double c);
    TriangleType GetTypeSides(double a, double b, double c);
    TriangleType GetTypeCorners(double a, double b, double c);
    double GetArea(double a, double b, double c);
    void ChangeValid(long Id);
    void Save(long Id, double A, double B, double C, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area);
}
