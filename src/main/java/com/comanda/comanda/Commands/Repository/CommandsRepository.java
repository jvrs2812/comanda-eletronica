package com.comanda.comanda.Commands.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommandsRepository extends JpaRepository<CommandsModelRepository, UUID> {
}
