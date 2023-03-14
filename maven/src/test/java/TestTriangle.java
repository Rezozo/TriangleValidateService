import Dao.ITriangleProvider;
import Model.Triangle;
import Model.TriangleType;
import Service.ITriangleService;
import Service.ITriangleValidateService;
import Service.impl.TriangleServiceImpl;
import Service.impl.TriangleValidateServiceImpl;
import Dao.impl.TriangleProviderImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTriangle {
    private static ITriangleService triangleService;
    private static ITriangleProvider triangleProvider;
    private static ITriangleValidateService triangleValidateService;
    @BeforeAll
    public static void setUp() {
        triangleProvider = new TriangleProviderImpl();
        triangleService = new TriangleServiceImpl();
        triangleValidateService = new TriangleValidateServiceImpl(triangleProvider, triangleService);
    }

    @Test
    public void testGetByIdTrue() {
        Set<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(1, 3, 4, 5, types, false, 6);
        triangleProvider.Save(triangle);

        assertTrue(triangleValidateService.isValid(1));
    }

    @Test
    public void testGetAllTrue(){
        EnumSet<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(2, 5, 13, 12, types, false, 30);
        triangleProvider.Save(triangle);

        EnumSet<TriangleType> types2 = EnumSet.of(TriangleType.Scalene, TriangleType.Obtuse);
        Triangle triangle2 = new Triangle(3, 3, 4, 6, types2, false, 6);
        triangleProvider.Save(triangle2);
        assertTrue(triangleValidateService.isAllValid());
    }

    @Test
    public void testGetByIdFalse() {
        assertFalse(triangleValidateService.isValid(triangleProvider.GetById(4).getId()));
    }

    @Test
    public void testGetAllFalse() {
        assertFalse(triangleValidateService.isAllValid()); //  если треугольников в бд нет
    }
}
