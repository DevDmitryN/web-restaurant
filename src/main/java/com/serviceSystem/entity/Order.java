package com.serviceSystem.entity;

import com.serviceSystem.entity.enums.Status;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Entity
@Table(name = "orders",schema = "restaurantdb")
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
//    @ManyToMany
//    @JoinTable(
//            name = "order_dish",
//            schema = "restaurantdb",
//            joinColumns = {@JoinColumn(name = "order_id")},
//            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
//    )
//    private List<Dish> orderDishes;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderDish> orderDishes;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @Column(name = "creation_time")
    private LocalDateTime creationTime = LocalDateTime.now();
    @Column(name = "booking_time")
    private LocalDateTime bookingTime;



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
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public List<OrderDish> getOrderDish() {
        return orderDishes;
    }

    public void setOrderDish(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
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
        orderDishes.forEach( a -> totalPrice = totalPrice.add(new BigDecimal(a.getDish().getPrice() * a.getAmount())));
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getFormatedCreationTime(){
        return creationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getFormatedBookingTime(){
        return bookingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    public String getStatusAsString(){
        return status.getNameInRussian();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", table=" + table +
//                ", orderDishes=" + orderDishes +
                ", client=" + client +
                ", worker=" + worker +
                ", creationTime=" + creationTime +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
