package com.example.electronicshop.communication.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class CommentResponse {
    private String id;
    private String content;
    private double rate;
    private String state;
    private String reviewedBy;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdDate;
}
