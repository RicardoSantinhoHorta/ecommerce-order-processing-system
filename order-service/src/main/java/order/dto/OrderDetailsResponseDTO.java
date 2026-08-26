package order.dto;

import java.math.BigDecimal;

public record OrderDetailsResponseDTO(BigDecimal orderTotal) {
}
