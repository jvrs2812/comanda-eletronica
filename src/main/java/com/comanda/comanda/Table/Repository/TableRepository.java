package com.comanda.comanda.Table.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<TableModelRepository, UUID> {

    @Query(value = "select case when count(*) > 0 then true else false end from table_restaurant where identification = :ind", nativeQuery = true)
    boolean existIdentification(@Param("ind") String identification);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update table_restaurant set enable = :ena where id = :id", nativeQuery = true)
    void updateEnableTable(@Param("ena") boolean enable, @Param("id") UUID id);

    @Query(value = "select * from table_restaurant where enable = true",
            countQuery = "select count(*) from table_restaurant where enable = true",
            nativeQuery = true)
    Page<TableModelRepository> getAll(Pageable page);

    @Query(value = "select res.enable from table_restaurant res where res.identification = :ind LIMIT 1", nativeQuery = true)
    boolean isEnableIdentification(@Param("ind") String ind);

    @Query(value = "select res.id from table_restaurant res where res.identification = :ind", nativeQuery = true)
    UUID getIdWithIdentification(@Param("ind") String ind);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update table_restaurant set amount_people = :amount where id = :id", nativeQuery = true)
    void updateAmountPeople(@Param("amount") int amount, @Param("id") UUID id);
}
