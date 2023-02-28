package IO.SampleWeek3_Transaction.order.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class OrderCoffeeDto {
    @Positive
    private long coffeeId;

    @Positive
    private int quantity;
}
