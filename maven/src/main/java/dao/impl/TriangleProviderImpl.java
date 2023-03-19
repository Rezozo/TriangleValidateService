package dao.impl;

import dao.TriangleProvider;
import repository.TriangleRepository;
import model.Triangle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TriangleProviderImpl implements TriangleProvider {
    private  TriangleRepository triangleRepository;
    @Autowired
    public TriangleProviderImpl(TriangleRepository triangleRepository) {
        this.triangleRepository = triangleRepository;
    }
    public Triangle getById(Long id) {
        return triangleRepository.findById(id).get();
    }
    public List<Triangle> getAll() {
        return (List<Triangle>) triangleRepository.findAll();
    }
    public Triangle getBySides(double a, double b, double c) {
        return triangleRepository.findByAAndBAndC(a, b, c);
    }
    public void save(Triangle triangle) {
        triangleRepository.save(triangle);
    }
}