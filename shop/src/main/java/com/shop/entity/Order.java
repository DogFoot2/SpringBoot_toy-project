package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //정렬할 때 사용하는 "order" 키워트가 있기 때문에 테이블로 orders를 지정
@Getter @Setter
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //한 명의 회원은 여러번 주문할 수 있음 -> 주문 엔티티 기준에서 다대일 단방향 매핑

    private LocalDateTime orderDate;    //주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    //주문 상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)  //주문 상품 엔티티와 일대다 매핑
    //부모 엔티티의 영속성 상태 변화를 자식 엔티티에 모두 전이하는 CascadeType.ALL 옵션 설정
    //외래키(order_id)가 order_item 테이블에 있으므로 연관 관계의 주인은 OrderItem 엔티티
    //order가 주인이 아니므로 mappedBy로 연관 관계의 주인을 설정
    private List<OrderItem> orderItems = new ArrayList<>();
    //하나의 주문이 여러 개의 주문 상품을 갖으므로 List 자료형을 사용해서 매핑

    //주문 객체 메소드
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList){
        Order order = new Order();
        order.setMember(member);
        for(OrderItem orderItem : orderItemList){ //상품을 주문한 회원 정보 세팅
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER); // 주문 상태를 "ORDER"로 세팅
        order.setOrderDate(LocalDateTime.now()); //현재 시간을 주문 시간으로 세팅
        return order;
    }
    //총 주문금액 메소드
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    // 주문 취소시 재고 더해주기 & 주문 상태를 취소 상태로 변경
    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCEL;

        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }
}
