package application.mobile.terrainpadel.services;

import application.mobile.terrainpadel.entities.Candidature;
import application.mobile.terrainpadel.entities.Etudiant;

import java.util.List;

public interface IServices {


    List<Etudiant> getAllEtudiants();
    Etudiant createEtudiant(Etudiant etudiant);
    Etudiant updateEtudiant(Long id, Etudiant etudiantDetails);
    void deleteEtudiant(Long id);
    Etudiant getEtudiantById( Long id);

    List<Candidature> getAllCandidatures();

    Candidature createCandidature(Candidature candidature);
     Candidature updateCandidature( Long id,  Candidature candidatureDetails);
    Candidature getCandidatureById(Long id);
    void deleteCandidature(Long id);
    List<Etudiant> searchEtudiants(String keyword);

    List<Candidature> searchCandidatures(String keyword);

    String generateCv(Etudiant etudiant);

    String generateLettreMotivation(Candidature candidature, Etudiant etudiant);


    void verifierEtMettreAJourCandidaturesExpirees();

    void supprimerCandidaturesAnciennes();

}
