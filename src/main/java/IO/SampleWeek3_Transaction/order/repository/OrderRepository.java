package IO.SampleWeek3_Transaction.order.repository;

import IO.SampleWeek3_Transaction.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
