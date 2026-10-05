package tn.esprit.stageloop.applications.services;

import tn.esprit.stageloop.applications.entities.Candidature;
import tn.esprit.stageloop.applications.entities.Etudiant;

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
