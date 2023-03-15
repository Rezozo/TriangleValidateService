package dao;

import model.Triangle;

import java.util.List;

public interface TriangleProvider {
    Triangle getById(long id);
    List<Triangle> getAll();
    void save(Triangle triangle);
}

