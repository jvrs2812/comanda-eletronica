package com.comanda.comanda.Category.repository;
import com.comanda.comanda.Product.Repository.ProductModelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModelRepository, UUID> {

    @Query(value = "select * from category where enterprise_id = :enterprise_id", nativeQuery = true)
    Page<CategoryModelRepository> findAll(Pageable pageable, @Param("enterprise_id") UUID enterpriseId);

    @Query(value = "select case when count(*) > 0 then true else false end from category where id = :id and enterprise_id = :enterprise_id", nativeQuery = true)
    boolean existById(@Param("id") UUID id, @Param("enterprise_id") UUID enterprise_id);

    @Query(value = "select * from category where id = :id and enterprise_id = :enterprise_id", nativeQuery = true)
    CategoryModelRepository findById(@Param("id") UUID id, @Param("enterprise_id") UUID enterprise_id);

}
