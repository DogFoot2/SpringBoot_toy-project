package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;  //하나의 상품은 여러 주문 상품으로 들어갈 수 있으므로 주문 상품 기준 다대일 단방향 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 한번의 주문에 여러 개의 상품을 주문할 수 있으므로 주문 상품 엔티티와 주문 엔티티를 다대일 단방향 매핑을 먼저 설정

    private int orderPrice; //주문가격

    private int count;  //수량

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);    // 주문할 상품과 주문 수량을 세팅
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());

        item.removeStock(count);    //주문 수량만큼 상품의 재고 수량을 감소
        return orderItem;
    }

    public int getTotalPrice(){ //주문가격과 주문 수량을 곱해서 해당 상품을 주문한 총 가격을 계산하는 메소드
        return orderPrice*count;
    }

    //주문 취소시 주문 수량만큼 상품 재고 증가
    public void cancel(){
        this.getItem().addStock(count);
    }

}
