package com.serviceSystem.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.serviceSystem.entity.enums.Status;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity
@Table(name = "orders",schema = "restaurantdb")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private BigDecimal totalPrice = new BigDecimal(0);
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_TAKEN;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<DishInOrder> dishesInOrder = null;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @Column(name = "creation_time")
    private LocalDateTime creationTime = LocalDateTime.now();
    @Column(name = "booking_time_begin")
    private LocalDateTime bookingTimeBegin;
    @Column(name = "booking_time_end")
    private LocalDateTime bookingTimeEnd;



    public Order(){

    }
    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice.setScale(2, RoundingMode.DOWN);
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
//    public void setStatus(String status) {
//        this.status = Status.valueOf(status);
//    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public List<DishInOrder> getDishesInOrder() {
        return dishesInOrder;
    }

    public void setDishesInOrder(List<DishInOrder> orderDishes) {
        this.dishesInOrder = orderDishes;
        countTotalPrice();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    private void countTotalPrice(){
        dishesInOrder.forEach(a -> totalPrice = totalPrice.add(new BigDecimal(a.getDish().getPrice() * a.getAmount())));
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getBookingTimeBegin() {
        return bookingTimeBegin;
    }

    public void setBookingTimeBegin(LocalDateTime bookingTimeBegin) {
        this.bookingTimeBegin = bookingTimeBegin;
    }

    public LocalDateTime getBookingTimeEnd() {
        return bookingTimeEnd;
    }

    public void setBookingTimeEnd(LocalDateTime bookingTimeEnd) {
        this.bookingTimeEnd = bookingTimeEnd;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", table=" + table +
//                ", dishesInOrder=" + dishesInOrder +
                ", client=" + client +
                ", worker=" + worker +
                ", creationTime=" + creationTime +
                ", bookingTimeBegin=" + bookingTimeBegin +
                '}';
    }
}
