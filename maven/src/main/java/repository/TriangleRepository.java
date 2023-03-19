package repository;

import model.Triangle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriangleRepository extends CrudRepository<Triangle, Long> {
    Triangle findByAAndBAndC(double a, double b, double c);
}
