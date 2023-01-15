package com.comanda.comanda.Product.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<ProductModelRepository, Long> {
    Page<ProductModelRepository> findAll(Pageable pageable);

    @Query(value = "select count(product) > 0 from product where id = :id",nativeQuery = true)
    boolean existByProductId(@Param("id") UUID id);

    @Query(value = "select * from product where id = :id",nativeQuery = true)
    ProductModelRepository getByUUID(@Param("id") UUID id);
}
