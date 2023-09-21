package com.rsk.db.repository;

import com.rsk.db.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
}
