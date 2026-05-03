package re.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.dto.response.RevenueResponse;
import re.edu.repository.OrderRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public RevenueResponse getRevenue(String type, Integer year, Integer month) {
        int resolvedYear = (year != null) ? year : LocalDate.now().getYear();
        int resolvedMonth = (month != null) ? month : LocalDate.now().getMonthValue();

        return switch (type.toLowerCase()) {
            case "day" -> RevenueResponse.builder()
                    .type("day")
                    .period(LocalDate.now().toString())
                    .revenue(orderRepository.revenueToday())
                    .build();

            case "month" -> RevenueResponse.builder()
                    .type("month")
                    .period(resolvedYear + "-" + String.format("%02d", resolvedMonth))
                    .revenue(orderRepository.revenueByMonth(resolvedYear, resolvedMonth))
                    .build();

            case "year" -> RevenueResponse.builder()
                    .type("year")
                    .period(String.valueOf(resolvedYear))
                    .revenue(orderRepository.revenueByYear(resolvedYear))
                    .build();

            default -> throw new IllegalArgumentException("Invalid type. Use: day, month, year");
        };
    }
}
