package uz.pdp.warehouse.model.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
public class TokenDto {
    private String token;
    private Date expiry;
}
