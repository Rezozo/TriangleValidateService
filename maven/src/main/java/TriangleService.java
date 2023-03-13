import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

public class TriangleService  implements ITriangleService {
    public Connection connection;

    public TriangleService(Connection connection) {
        this.connection = connection;
    }
    public boolean isValidTriangle(double a, double b, double c) {
        try  {
            String sql = "SELECT * FROM Triangles WHERE \"A\" = ? AND b = ? AND \"C\" = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, a);
            stmt.setDouble(2, b);
            stmt.setDouble(3, c);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return !(a + b <= c) && !(a + c <= b) && !(b + c <= a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new Error("Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
    }

    public TriangleType GetTypeSides(double a, double b, double c) {
        try {
            String sql = "SELECT * FROM Triangles WHERE \"A\" = ? AND b = ? AND \"C\" = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, a);
            stmt.setDouble(2, b);
            stmt.setDouble(3, c);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (!isValidTriangle(a, b, c))
                {
                    return null;
                }
                else if (Math.pow(a, 2) + Math.pow(b, 2) < Math.pow(c, 2) || Math.pow(a, 2) + Math.pow(c, 2) < Math.pow(b, 2)
                        || Math.pow(b, 2) + Math.pow(c, 2) < Math.pow(a, 2))
                {
                    return TriangleType.Obtuse;
                }
                else if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2) || Math.pow(a, 2) + Math.pow(c, 2) == Math.pow(b, 2)
                        || Math.pow(b, 2) + Math.pow(c, 2) == Math.pow(a, 2))
                {
                    return TriangleType.Right;
                }
                else
                {
                    return TriangleType.Oxygon;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new Error("Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
    }

    public TriangleType GetTypeCorners(double a, double b, double c) {
        try {
            String sql = "SELECT * FROM Triangles WHERE \"A\" = ? AND b = ? AND \"C\" = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, a);
            stmt.setDouble(2, b);
            stmt.setDouble(3, c);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (!isValidTriangle(a, b, c))
                {
                    return null;
                }
                else if (a == b && b == c)
                {
                    return TriangleType.Equilateral;
                }
                else if (a == c || b == c || b == a)
                {
                    return TriangleType.Isosceles;
                }
                else
                {
                    return TriangleType.Scalene;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new Error("Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
    }

    public double GetArea(double a, double b, double c) {
        try {
            String sql = "SELECT * FROM Triangles WHERE \"A\" = ? AND b = ? AND \"C\" = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, a);
            stmt.setDouble(2, b);
            stmt.setDouble(3, c);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (!isValidTriangle(a, b, c))
                {
                    return 0;
                }
                double p = (a + b + c) / 2.0;
                double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
                return Math.ceil(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new Error("Triangle with sides " + a + ", " + b + ", " + c + " was not found.");
    }

    public void ChangeValid(long Id) {
        try{
            String sql = "Update Triangles SET isvalidtriangle = true where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, Id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Save(long Id, double A, double B, double C, EnumSet<TriangleType> Type, boolean isValidTriangle, double Area) {
        try {
            Triangle triangle = new Triangle(Id, A, B, C, Type, isValidTriangle, Area);

            String sql = "Insert into Triangles values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, triangle.Id);
            stmt.setDouble(2, triangle.A);
            stmt.setDouble(3, triangle.B);
            stmt.setDouble(4, triangle.C);
            stmt.setInt(5, triangle.Type.stream().mapToInt(TriangleType::getValue).sum());
            stmt.setBoolean(6, triangle.isValidTriangle);
            stmt.setDouble(7, triangle.Area);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
