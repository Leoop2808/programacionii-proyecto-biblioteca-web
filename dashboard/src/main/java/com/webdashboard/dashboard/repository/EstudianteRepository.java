package com.webdashboard.dashboard.repository;

import com.webdashboard.dashboard.model.Estudiante;
import com.webdashboard.dashboard.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
    @Query(value = "SELECT o FROM Estudiante o WHERE o.user=?1")
    Estudiante findByUsuario(Usuario user);
}
