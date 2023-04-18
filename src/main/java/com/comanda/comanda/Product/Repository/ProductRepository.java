package com.comanda.comanda.Product.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<ProductModelRepository, UUID> {

    @Query(value = "select * from product where enterprise_id = :enterprise_id", nativeQuery = true)
    Page<ProductModelRepository> findAll(Pageable pageable, @Param("enterprise_id") UUID enterpriseId);

    @Query(value = "select case when count(*) > 0 then true else false end from product where category_id = :category_id", nativeQuery = true)
    boolean existCategoryId(@Param("category_id") UUID id);
}
