package dao;

import model.Triangle;

import java.util.List;

public interface TriangleProvider {
    Triangle getById(Long id);
    List<Triangle> getAll();
    Triangle getBySides(double a, double b, double c);
    void save(Triangle triangle);
}

