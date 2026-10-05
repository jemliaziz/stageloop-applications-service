package application.mobile.terrainpadel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("candidatures") // Empêche la boucle infinie JSON
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String nom;


    private String prenom;


    private String email;


    private String telephone;


    private String adresse;


    private String competences;
    private String codepostal;


    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Candidature> candidatures;


}
