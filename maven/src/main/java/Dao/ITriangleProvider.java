package Dao;

import Model.Triangle;

import java.util.List;

public interface ITriangleProvider {
    Triangle GetById(long id);
    List<Triangle> GetAll();
    void Save(Triangle triangle);
}

