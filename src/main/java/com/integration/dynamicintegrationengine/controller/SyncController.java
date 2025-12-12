package com.integration.dynamicintegrationengine.controller;

import com.integration.dynamicintegrationengine.service.SyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
@RequiredArgsConstructor
public class SyncController {

    private final SyncService syncService;

    @GetMapping("/calendly")
    public String syncCalendly() {
        syncService.syncCalendlyUsers();
        return "Calendly sync completed";
    }
}