package dao;

import model.Triangle;

import java.util.List;

public interface TriangleDao {
    List<Triangle> selectAll();
    Triangle selectById(long id);
    Triangle selectBySides(double a, double b, double c);
    void save(Triangle triangle);
}
