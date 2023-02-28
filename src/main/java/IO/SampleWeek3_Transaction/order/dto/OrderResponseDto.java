package IO.SampleWeek3_Transaction.order.dto;

import IO.SampleWeek3_Transaction.member.entity.Member;
import IO.SampleWeek3_Transaction.order.entity.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private long orderId;

    @Setter(AccessLevel.NONE)
    private long memberId;
    private Order.OrderStatus orderStatus;
    private List<OrderCoffeeResponseDto> orderCoffees;
    private LocalDateTime createdAt;

    public void setMember(Member member) {
        this.memberId = member.getMemberId();
    }
}
