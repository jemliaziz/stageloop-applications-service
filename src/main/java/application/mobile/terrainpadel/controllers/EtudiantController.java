package application.mobile.terrainpadel.controllers;

import java.util.List;

import application.mobile.terrainpadel.entities.Etudiant;
import application.mobile.terrainpadel.repositories.EtudiantRepository;
import application.mobile.terrainpadel.services.IServices;
import lombok.AllArgsConstructor;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@EnableDiscoveryClient
@AllArgsConstructor
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final IServices services;
    private final EtudiantRepository etudiantRepository;
    private final SimpMessagingTemplate messagingTemplate; // Inject SimpMessagingTemplate


    @GetMapping("/search")
    public List<Etudiant> searchEtudiants(@RequestParam String keyword) {
        return etudiantRepository.searchEtudiants(keyword);
    }


    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return services.getAllEtudiants();
    }


    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id) {
        return services.getEtudiantById(id);
    }


    @PostMapping("/create")
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant created = services.createEtudiant(etudiant);
        messagingTemplate.convertAndSend("/topic/etudiantNotifications", created);
        return created;
    }

    // 📌 4. Mettre à jour un étudiant (avec mise à jour partielle optimisée)
    @PutMapping("/update/{id}")
    public Etudiant updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        return services.updateEtudiant(id, etudiantDetails);
    }

    // 📌 5. Supprimer un étudiant
    @DeleteMapping("/delete/{id}")
    public void deleteEtudiant(@PathVariable Long id) {
        services.deleteEtudiant(id);
    }
}
