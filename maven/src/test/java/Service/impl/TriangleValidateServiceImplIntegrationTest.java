package service.impl;

import dao.TriangleDao;
import dao.TriangleProvider;
import dao.impl.TriangleDaoImpl;
import model.Triangle;
import model.TriangleType;
import service.TriangleService;
import service.TriangleValidateService;
import dao.impl.TriangleProviderImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriangleValidateServiceImplIntegrationTest {
    private static TriangleService triangleService;
    private static TriangleProvider triangleProvider;
    private static TriangleValidateService triangleValidateService;
    private static TriangleDao triangleDao;
    @BeforeAll
    public static void setUp() {
        triangleDao = new TriangleDaoImpl();
        triangleProvider = new TriangleProviderImpl(triangleDao);
        triangleService = new TriangleServiceImpl(triangleDao);
        triangleValidateService = new TriangleValidateServiceImpl(triangleProvider, triangleService);
    }

    @Test
    public void testGetByIdTrue() {
        Set<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(1, 3, 4, 5, types, true, 6);
        triangleProvider.save(triangle);

        assertTrue(triangleValidateService.isValid(1));
    }

    @Test
    public void testGetAllTrue(){
        EnumSet<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(2, 5, 13, 12, types, true, 30);
        triangleProvider.save(triangle);

        EnumSet<TriangleType> types2 = EnumSet.of(TriangleType.Scalene, TriangleType.Obtuse);
        Triangle triangle2 = new Triangle(3, 3, 4, 6, types2, true, 6);
        triangleProvider.save(triangle2);
        assertTrue(triangleValidateService.isAllValid());
    }

    @Test
    public void testGetByIdFalse() {
        assertFalse(triangleValidateService.isValid(triangleProvider.getById(4).getId()));
    }

    @Test
    public void testGetAllFalse() {
        assertFalse(triangleValidateService.isAllValid()); //  если треугольников в бд нет
    }
}
