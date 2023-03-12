package com.comanda.comanda.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModelRepository, UUID> {

    @Query(value = "select case when count(*) > 0 then true else false end from user_table where email = :email", nativeQuery = true)
    boolean existbyEmail(@Param("email") String email);

    Optional<UserModelRepository> findByEmail(String email);
}
