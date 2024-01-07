package com.sme.app.controller;

import com.sme.app.permission.annotations.SuperAdminOnly;
import com.sme.app.service.SmeService;
import com.sme.app.vo.SmeVo;
import com.sme.app.vo.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sme")
public class SmeController {

    private final SmeService smeService;

    @GetMapping("/{id}")
    public AppResponse<SmeVo> findSme(@PathVariable Long id) {
        return new AppResponse<>(smeService.findSmeById(id));
    }

    @GetMapping("/code/{code}")
    public AppResponse<SmeVo> findSmeByCode(@PathVariable String code) {
        return new AppResponse<>(smeService.findSmeByCode(code));
    }

    @SuperAdminOnly
    @GetMapping("/all")
    public AppResponse<List<SmeVo>> findAll() {
        return new AppResponse<>(smeService.findAllSmes());
    }

    @SuperAdminOnly
    @PostMapping
    public AppResponse<SmeVo> addSme(@RequestBody SmeVo user) {
        return new AppResponse<>(smeService.addSme(user));
    }

    @SuperAdminOnly
    @PutMapping("/{id}")
    public AppResponse<SmeVo> updateSme(@PathVariable Long id, @RequestBody SmeVo user) {
        return new AppResponse<>(smeService.updateSme(id, user));
    }

    @SuperAdminOnly
    @PostMapping("/{id}")
    public AppResponse<Long> activateSme(@PathVariable Long id) {
        return new AppResponse<>(smeService.activateSme(id));
    }

    @SuperAdminOnly
    @DeleteMapping("/{id}")
    public AppResponse<Long> deleteSme(@PathVariable Long id) {
        return new AppResponse<>(smeService.deleteSme(id));
    }

}
