import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTriangle {
    private static ITriangleService triangleService;
    private static ITriangleProvider triangleProvider;
    private static ITriangleValidateService triangleValidateService;
    @BeforeAll
    public static void setUp() throws SQLException {
        Connection connection = DbConnect.getConnection();
        triangleProvider = new TriangleProvider(connection);
        triangleService = new TriangleService(connection);
        triangleValidateService = new TriangleValidateService(triangleProvider, triangleService);
    }

    @Test
    public void testGetByIdTrue() {
        EnumSet<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(2, 3, 4, 5, types, false, 6);
        triangleProvider.Save(triangle);

        Triangle actualTriangle = triangleProvider.GetById(2);
        boolean res = triangleValidateService.isValid(actualTriangle.Id);
        if (res) {
            triangleService.ChangeValid(actualTriangle.Id);
        }
        assertTrue(res);
    }
    @Test
    public void testGetByIdFalse() {
        EnumSet<TriangleType> types = EnumSet.of(TriangleType.Oxygon, TriangleType.Right);
        Triangle triangle = new Triangle(3, 3, 4, 5, types, false, 6);
        triangleProvider.Save(triangle);

        Triangle actualTriangle = triangleProvider.GetById(3);
        boolean res = triangleValidateService.isValid(actualTriangle.Id);
        if (res) {
            triangleService.ChangeValid(actualTriangle.Id);
        }
        assertFalse(res);
    }

    @Test
    public void testGetAllTrue(){
        EnumSet<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(4, 5, 13, 12, types, false, 30);
        triangleProvider.Save(triangle);

        EnumSet<TriangleType> types2 = EnumSet.of(TriangleType.Scalene, TriangleType.Obtuse);
        Triangle triangle2 = new Triangle(5, 3, 4, 6, types2, false, 6);
        triangleProvider.Save(triangle2);
        boolean res = triangleValidateService.isAllValid();

        if (res) {
            for (Triangle triangles :
                    triangleProvider.GetAll()){
                triangleService.ChangeValid(triangles.Id);
            }
        }
        assertTrue(res);
    }

    @Test
    public void testGetAllFalse() {
        EnumSet<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Oxygon);
        Triangle triangle = new Triangle(6, 5, 13, 12, types, false, 30);
        triangleProvider.Save(triangle);

        EnumSet<TriangleType> types2 = EnumSet.of(TriangleType.Scalene, TriangleType.Obtuse);
        Triangle triangle2 = new Triangle(7, 9, 5, 6, types2, false, 15);
        triangleProvider.Save(triangle2);
        boolean res = triangleValidateService.isAllValid();

        if (res) {
            for (Triangle triangles :
                    triangleProvider.GetAll()){
                triangleService.ChangeValid(triangles.Id);
            }
        }
        assertFalse(res);
    }
}
