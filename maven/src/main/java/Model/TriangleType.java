package Model;

public enum TriangleType {
    Oxygon(1),
    Obtuse(2),
    Right(4),
    Scalene(8),
    Isosceles(16),
    Equilateral(32);

    private final int value;

    TriangleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
