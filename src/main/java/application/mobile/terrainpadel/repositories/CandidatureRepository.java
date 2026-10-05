package application.mobile.terrainpadel.repositories;

import application.mobile.terrainpadel.entities.Candidature;
import application.mobile.terrainpadel.entities.StatutCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByEtudiantIsNotNull();
    @Query("SELECT c FROM Candidature c WHERE LOWER(c.etudiant.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.statut) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Candidature> searchCandidatures(@Param("keyword") String keyword);

    List<Candidature> findByDateExpirationBeforeOrDateCandidatureBefore(LocalDate expirationDate, LocalDate candidatureDate);
    List<Candidature> findByStatut(StatutCandidature statut);


}


