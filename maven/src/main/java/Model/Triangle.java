package Model;

import javax.persistence.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;

@Entity
@Table (name = "triangles")
public class Triangle {
    @Id
    @Column(name = "id")
    private long Id;
    @Column (name = "a")
    private double A;
    @Column (name = "b")
    private  double B;
    @Column (name = "c")
    private double C;
    @Column(name = "type")
    private String Types;
    @Column (name = "isvalidtriangle")
    private boolean isValidTriangle;
    @Column (name = "area")
    private double Area;

    public Triangle() { }
    public Triangle(long Id, double A, double B, double C, Set<TriangleType> types, boolean isValidTriangle, double Area) {
        this.Id = Id;
        this.A = A;
        this.B = B;
        this.C = C;
        this.Types = serializeTypes(types);
        this.isValidTriangle = isValidTriangle;
        this.Area = Area;
    }

    public long getId() {
        return Id;
    }
    public double getA() {
        return A;
    }
    public double getB() {
        return B;
    }
    public double getC() {
        return C;
    }

    public boolean getValid() {
        return isValidTriangle;
    }

    public double getArea() {
        return Area;
    }

    public Set<TriangleType> getTypes() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(this.Types, Set.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String serializeTypes(Set<TriangleType> types) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(types);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}