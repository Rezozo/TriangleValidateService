import java.util.EnumSet;

public class Triangle {
    public long Id;
    public double A;
    public  double B;
    public double C;
    public EnumSet<TriangleType> Type;
    public boolean isValidTriangle;
    public double Area;

    public Triangle(long Id, double A, double B, double C, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area) {
        this.Id = Id;
        this.A = A;
        this.B = B;
        this.C = C;
        this.Type = Type;
        this.isValidTriangle = isValidTriangle;
        this.Area = Area;
    }
}