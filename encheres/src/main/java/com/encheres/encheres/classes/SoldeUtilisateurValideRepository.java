package com.encheres.encheres.classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldeUtilisateurValideRepository extends JpaRepository<SoldeUtilisateurValide, Integer> { }