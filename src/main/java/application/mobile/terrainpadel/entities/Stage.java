package application.mobile.terrainpadel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity






public class Stage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idStage;


    private String titre;
    private String description;
    private Date dateDebut;
    private Date dateFin;


    private String competencesRequises;
    private String codepostal;

}