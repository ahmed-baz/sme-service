package com.sme.app.controller;


import com.sme.app.logging.annotation.ExecutionTimeLogger;
import com.sme.app.permission.annotations.SuperAdminOnly;
import com.sme.app.service.MigrationService;
import com.sme.app.vo.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/migration")
@RequiredArgsConstructor
public class MigrationController {

    private final MigrationService migrationService;

    @SuperAdminOnly
    @ExecutionTimeLogger
    @GetMapping("/dummyCsv")
    public AppResponse<Void> createDummyCsv() {
        migrationService.createDummyCsv();
        return new AppResponse<>(null);
    }

    @SuperAdminOnly
    @ExecutionTimeLogger
    @GetMapping("/migrate")
    public AppResponse<Void> doMigration() {
        migrationService.doMigration();
        return new AppResponse<>(null);
    }
}
