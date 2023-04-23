package com.comanda.comanda.Command.Repository;

import com.comanda.comanda.Category.repository.CategoryModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandRepository extends JpaRepository<CommandModelRepository, UUID> {

    @Query(value = "select * from commands where id = :id and enterprise_id = :enterprise_id", nativeQuery = true)
    CommandModelRepository findById(@Param("id") UUID id, @Param("enterprise_id") UUID enterprise_id);

    @Query(value = "select case when count(*) > 0 then true else false end from commands where id = :id and enterprise_id = :enterprise_id", nativeQuery = true)
    boolean existByUUID(@Param("id") UUID id, @Param("enterprise_id") UUID enterprise_id);
}
