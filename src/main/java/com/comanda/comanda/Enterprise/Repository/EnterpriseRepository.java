package com.comanda.comanda.Enterprise.Repository;

import com.comanda.comanda.Enterprise.UseCases.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnterpriseRepository extends JpaRepository<EnterpriseModelRepository, UUID>{

    @Query(value = "select case when count(*) > 0 then true else false end from enterprise where cnpj = :cnpj", nativeQuery = true)
    boolean existCnpj(@Param("cnpj") String cnpj);

    @Query(value = "select enter.* from enterprise_users users inner join enterprise enter on (enter.id = users.enterprise_id) where users.users_id = :user_id", nativeQuery = true)
    List<EnterpriseModelRepository> findAllEnterpriseByUserId(@Param("user_id") UUID user_id);
}
