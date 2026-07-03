package com.pavaniprojects.june_edition.service;

import com.pavaniprojects.june_edition.SoftwareEngineer;
import com.pavaniprojects.june_edition.SoftwareEngineerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    // GET
    public List<SoftwareEngineer> getSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    // POST
    public SoftwareEngineer addSoftwareEngineer(SoftwareEngineer engineer) {
        return softwareEngineerRepository.save(engineer);
    }

    // PUT
    public SoftwareEngineer updateSoftwareEngineer(Integer id,
                                                   SoftwareEngineer updatedEngineer) {

        SoftwareEngineer engineer =
                softwareEngineerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Engineer not found"));

        engineer.setName(updatedEngineer.getName());
        engineer.setTechStack(updatedEngineer.getTechStack());

        return softwareEngineerRepository.save(engineer);
    }

    // PATCH
    public SoftwareEngineer updateTechStack(Integer id,
                                            Map<String, String> updates) {

        SoftwareEngineer engineer =
                softwareEngineerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Engineer not found"));

        if (updates.containsKey("name")) {
            engineer.setName(updates.get("name"));
        }

        if (updates.containsKey("techStack")) {
            engineer.setTechStack(updates.get("techStack"));
        }

        return softwareEngineerRepository.save(engineer);
    }

    // DELETE
    public void deleteSoftwareEngineer(Integer id) {

        softwareEngineerRepository.deleteById(id);
    }
}