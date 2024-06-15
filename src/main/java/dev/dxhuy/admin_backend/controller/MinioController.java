package dev.dxhuy.admin_backend.controller;

import dev.dxhuy.admin_backend.service.MinIOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/minio")
public class MinioController {
    private final MinIOService minIOService;

    public MinioController(MinIOService minIOService) {
        this.minIOService = minIOService;
    }

    @GetMapping("/")
    public List<String> getAllImageUrls() {
        try {
            return minIOService.getAllImageUrls();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
