package org.sid.poccadorimaxon.queries.repositories;

import org.sid.poccadorimaxon.queries.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
