package com.project.blog.springbootrestapi.dtos;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message, String details) {

}
