package com.shep.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FreeBookDTO {
    private Long id;
    private Long bookId;
    private LocalDateTime borrowedTime;
    private LocalDateTime returnTime;
}