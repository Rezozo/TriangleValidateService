package Dao;

import Model.Triangle;

import java.util.List;

public interface ITriangleDaoImpl {
    List<Triangle> SelectAll();
    Triangle SelectById(long id);
    Triangle SelectBySides(double a, double b, double c);
    void Save(Triangle triangle);
    void Update(long id);
}
