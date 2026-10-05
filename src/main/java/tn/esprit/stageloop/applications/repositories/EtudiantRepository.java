package tn.esprit.stageloop.applications.repositories;

import tn.esprit.stageloop.applications.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface    EtudiantRepository extends JpaRepository<Etudiant, Long> {
    //List<Etudiant> findAllByEmail(String email);
    // Recherche par nom ou email (insensible à la casse)
    @Query("SELECT e FROM Etudiant e WHERE " +
            "LOWER(e.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.prenom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.competences) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Etudiant> searchEtudiants(@Param("keyword") String keyword);
}

