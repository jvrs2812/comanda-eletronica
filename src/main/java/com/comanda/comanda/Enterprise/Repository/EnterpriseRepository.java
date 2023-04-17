package com.comanda.comanda.Enterprise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnterpriseRepository extends JpaRepository<EnterpriseModelRepository, UUID>{
}
