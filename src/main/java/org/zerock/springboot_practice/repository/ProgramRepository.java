package org.zerock.springboot_practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.springboot_practice.domain.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
