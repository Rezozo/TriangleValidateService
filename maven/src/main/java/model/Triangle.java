package model;

import jakarta.persistence.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;

@Entity
@Table (name = "triangles")
public class Triangle {
    @Id
    @Column(name = "id")
    private long id;
    @Column (name = "a")
    private double a;
    @Column (name = "b")
    private  double b;
    @Column (name = "c")
    private double c;
    @Column(name = "type")
    private String types;
    @Column (name = "isvalidtriangle")
    private boolean isValidTriangle;
    @Column (name = "area")
    private double area;

    public Triangle() { }
    public Triangle(long id, double a, double b, double c, Set<TriangleType> types, boolean isValidTriangle, double area) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
        this.types = serializeTypes(types);
        this.isValidTriangle = isValidTriangle;
        this.area = area;
    }

    public long getId() {
        return id;
    }
    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }

    public boolean getValid() {
        return isValidTriangle;
    }

    public double getArea() {
        return area;
    }

    public Set<TriangleType> getTypes() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(this.types, Set.class);
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