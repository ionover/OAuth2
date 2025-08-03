package org.example.oauth2.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("/read-only")
    @Secured("ROLE_READ")
    public ResponseEntity<Map<String, String>> readOnlyData() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "This data is available only for users with READ role");
        data.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok(data);
    }

    @GetMapping("/write-only")
    @RolesAllowed("ROLE_WRITE")
    public ResponseEntity<Map<String, String>> writeOnlyData() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "This data is available only for users with WRITE role");
        data.put("operation", "write");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/write-or-delete")
    @PreAuthorize("hasRole('WRITE') or hasRole('DELETE')")
    public ResponseEntity<Map<String, String>> writeOrDeleteData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> data = new HashMap<>();
        data.put("message", "This data is available for users with WRITE or DELETE role");
        data.put("user", auth.getName());
        data.put("authorities", auth.getAuthorities().toString());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/user-specific")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<Map<String, String>> userSpecificData(@RequestParam String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> data = new HashMap<>();
        data.put("message", "This data is available only if the authenticated user matches the requested username");
        data.put("authenticatedUser", auth.getName());
        data.put("requestedUser", username);
        data.put("personalData", "Secret information for " + username);
        return ResponseEntity.ok(data);
    }
}