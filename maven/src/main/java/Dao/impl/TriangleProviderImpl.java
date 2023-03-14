package Dao.impl;

import Dao.ITriangleProvider;
import Model.Triangle;

import java.util.List;

public class TriangleProviderImpl implements ITriangleProvider {

    private final TriangleDaoImpl triangleDao = new TriangleDaoImpl();

    public Triangle GetById(long id) {
        return triangleDao.SelectById(id);
    }

    public List<Triangle> GetAll() {
        return triangleDao.SelectAll();
    }

    public void Save(Triangle triangle) {
        triangleDao.Save(triangle);
    }
}