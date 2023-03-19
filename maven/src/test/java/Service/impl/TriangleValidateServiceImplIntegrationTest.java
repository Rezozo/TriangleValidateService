package service.impl;

import dao.TriangleProvider;
import dao.impl.TriangleProviderImpl;
import model.Triangle;
import model.TriangleType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.TriangleRepository;
import service.TriangleService;
import service.TriangleValidateService;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriangleValidateServiceImplIntegrationTest {

    private static TriangleService triangleService;
    private static TriangleProvider triangleProvider;
    private static TriangleValidateService triangleValidateService;

    private static TriangleRepository triangleRepository;
    @BeforeAll
    public static void setUp() {
        triangleProvider = new TriangleProviderImpl(triangleRepository);
        triangleService = new TriangleServiceImpl(triangleProvider);
        triangleValidateService = new TriangleValidateServiceImpl(triangleProvider, triangleService);
    }
    @Test
    public void testGetByIdTrue() {
        Set<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(1L, 3, 4, 5, types, true, 6);
        triangleProvider.save(triangle);

        assertTrue(triangleValidateService.isValid(1L));
    }

    @Test
    public void testGetAllTrue(){
        EnumSet<TriangleType> types = EnumSet.of(TriangleType.Scalene, TriangleType.Right);
        Triangle triangle = new Triangle(2L, 5, 13, 12, types, true, 30);
        triangleProvider.save(triangle);

        EnumSet<TriangleType> types2 = EnumSet.of(TriangleType.Scalene, TriangleType.Obtuse);
        Triangle triangle2 = new Triangle(3L, 3, 4, 6, types2, true, 6);
        triangleProvider.save(triangle2);
        assertTrue(triangleValidateService.isAllValid());
    }

    @Test
    public void testGetByIdFalse() {
        assertFalse(triangleValidateService.isValid(4L));
    }

    @Test
    public void testGetAllFalse() {
        assertFalse(triangleValidateService.isAllValid()); //  если треугольников в бд нет
    }

}
