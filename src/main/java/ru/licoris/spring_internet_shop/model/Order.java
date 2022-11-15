package ru.licoris.spring_internet_shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate orderDate;

    @Column(name = "status", columnDefinition = "varchar(255) CHECK (status in ('pending','paid'))")
    @NotNull
    private String status = "pending";

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

    @Transient
    public double getTotalOrderPrice() {
        double sum = 0D;
        List<OrderItem> items = getOrderItems();
        for (OrderItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }
}
