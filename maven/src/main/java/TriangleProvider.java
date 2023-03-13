import java.sql.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TriangleProvider implements ITriangleProvider{

    private List<Triangle> triangles = new ArrayList<>();
    public Connection connection;
    public TriangleProvider(Connection connection) {
        this.connection = connection;
    }
    public Triangle GetById(long id) {
        try {
            String sql = "SELECT * FROM Triangles WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double A = rs.getDouble("A");
                double B = rs.getDouble("B");
                double C = rs.getDouble("C");
                int typeInt = rs.getInt("Type");
                EnumSet<TriangleType> Types = EnumSet.noneOf(TriangleType.class);
                for (TriangleType type : TriangleType.values()) {
                    if ((typeInt & type.getValue()) == type.getValue()) {
                        Types.add(type);
                    }
                }
                boolean isValid = rs.getBoolean("isValidTriangle");
                double Area = rs.getDouble("Area");
                return new Triangle(id, A, B, C, Types, isValid, Area);
            } else {
                System.out.println("Element with id=" + id + " not found.");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Triangle> GetAll() {
        try  {
            String sql = "SELECT * FROM Triangles";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                double A = rs.getDouble("A");
                double B = rs.getDouble("B");
                double C = rs.getDouble("C");
                int typeInt = rs.getInt("Type");
                EnumSet<TriangleType> Types = EnumSet.noneOf(TriangleType.class);
                for (TriangleType type : TriangleType.values()) {
                    if ((typeInt & type.getValue()) == type.getValue()) {
                        Types.add(type);
                    }
                }
                boolean isValid = rs.getBoolean("isValidTriangle");
                double Area = rs.getDouble("Area");
                Triangle triangle = new Triangle(id, A, B, C, Types, isValid, Area);
                triangles.add(triangle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return triangles;
    }

    public void Save(Triangle triangle) {
        try {
            int typeValue = 0;
            for (TriangleType type : triangle.Type) {
                typeValue |= type.getValue();
            }

            String sql = "Insert into Triangles values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, triangle.Id);
            stmt.setDouble(2, triangle.A);
            stmt.setDouble(3, triangle.B);
            stmt.setDouble(4, triangle.C);
            stmt.setInt(5, typeValue);
            stmt.setBoolean(6, triangle.isValidTriangle);
            stmt.setDouble(7, triangle.Area);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}