package com.pavaniprojects.june_edition.controller;

import com.pavaniprojects.june_edition.SoftwareEngineer;
import com.pavaniprojects.june_edition.service.SoftwareEngineerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/allengineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getSoftwareEngineers() {
        return softwareEngineerService.getSoftwareEngineers();
    }

    @PostMapping
    public SoftwareEngineer addEngineer(@RequestBody SoftwareEngineer engineer) {
        return softwareEngineerService.addSoftwareEngineer(engineer);
    }

    @PutMapping("/{id}")
    public SoftwareEngineer updateEngineer(
            @PathVariable Integer id,
            @RequestBody SoftwareEngineer updatedEngineer) {

        return softwareEngineerService.updateSoftwareEngineer(id, updatedEngineer);
    }

    @PatchMapping("/{id}")
    public SoftwareEngineer updateTechStack(
            @PathVariable Integer id,
            @RequestBody Map<String, String> updates) {

        return softwareEngineerService.updateTechStack(id, updates);
    }

    @DeleteMapping("/{id}")
    public String deleteEngineer(@PathVariable Integer id) {

        softwareEngineerService.deleteSoftwareEngineer(id);
        return "Engineer deleted successfully";
    }
}