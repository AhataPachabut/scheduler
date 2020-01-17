package com.it.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Session response dto.
 */
@NoArgsConstructor
@Data
public class SessionResponseDTO {

    private String accessToken;

    private String refreshToken;
}
