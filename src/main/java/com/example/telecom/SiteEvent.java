package com.example.telecom;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteEvent {
    private String siteId;
    private String status;
    private String message;
    private LocalDateTime timestamp;
}