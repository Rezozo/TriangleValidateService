import java.util.EnumSet;

public class TriangleValidateService implements ITriangleValidateService{
    private ITriangleService triangleService;
    private  ITriangleProvider triangleProvider;

    public TriangleValidateService(ITriangleProvider triangleProvider, ITriangleService triangleService) {
        this.triangleProvider = triangleProvider;
        this.triangleService = triangleService;
    }

    public boolean isAllValid() {
        boolean IsAllValid = true;
        for (var triangle :
                triangleProvider.GetAll()) {
            if (!IsAllValid) {
                break;
            }

            if (!triangleService.isValidTriangle(triangle.A, triangle.B, triangle.C)) {
                IsAllValid = false;
                triangle.isValidTriangle = false;
                break;
            }

            if (triangleService.GetArea(triangle.A, triangle.B, triangle.C) != triangle.Area) {
                IsAllValid = false;
                triangle.isValidTriangle = false;
                break;
            }

            TriangleType type = triangleService.GetTypeSides(triangle.A, triangle.B, triangle.C);
            TriangleType type1 = triangleService.GetTypeCorners(triangle.A, triangle.B, triangle.C);
            EnumSet<TriangleType> types = EnumSet.of(type1, type);
            if (types.equals(triangle.Type)) {
                triangle.isValidTriangle = true;
            }

            if (!triangle.isValidTriangle) {
                IsAllValid = false;
                break;
            }
        }
        return IsAllValid;
    }

    public boolean isValid(long id) {
        Triangle triangle = triangleProvider.GetById(id);
        if (!triangleService.isValidTriangle(triangle.A, triangle.B, triangle.C)) {
            return triangle.isValidTriangle = false;
        }

        if (triangleService.GetArea(triangle.A, triangle.B, triangle.C) != triangle.Area) {
            return triangle.isValidTriangle = false;
        }

        TriangleType type = triangleService.GetTypeSides(triangle.A, triangle.B, triangle.C);
        TriangleType type1 = triangleService.GetTypeCorners(triangle.A, triangle.B, triangle.C);
        EnumSet<TriangleType> types = EnumSet.of(type1, type);
        if (types.equals(triangle.Type)) {
            triangle.isValidTriangle = true;
        }
        return triangle.isValidTriangle;
    }
}
