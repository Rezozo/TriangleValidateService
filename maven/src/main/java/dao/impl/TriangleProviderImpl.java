package dao.impl;

import dao.TriangleDao;
import dao.TriangleProvider;
import model.Triangle;

import java.util.List;

public class TriangleProviderImpl implements TriangleProvider {
    private final TriangleDao triangleDao;
    public TriangleProviderImpl(TriangleDao triangleDao) {
        this.triangleDao = triangleDao;
    }
    public Triangle getById(long id) {
        return triangleDao.selectById(id);
    }
    public List<Triangle> getAll() {
        return triangleDao.selectAll();
    }
    public void save(Triangle triangle) {
        triangleDao.save(triangle);
    }
}