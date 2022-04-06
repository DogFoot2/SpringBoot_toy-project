package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    //상품 코드

    @Column(nullable = false, length =50)
    private String itemName; //상품명

    @Column(name="price", nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber;    //재고 수량

    @Lob
    @Column(nullable = false)
    private  String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    // 상품 데이터 업데이트 로직
    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    //상품을 주문할 경우 상품의 재고를 감소시키는 로직
    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber; // 상품의 재고 수량에서 주문후 남은 재고 수량 구함
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량: " + this.stockNumber + ")");
            //상품의 재고가 주문 수량보다 작을 경우 재고 부족 예외 발생
        }
        this.stockNumber = restStock; // 주문후 남은 재고 수량을 상품의 현재 재고 값으로 할당
    }

    //주문 취소
    public void addStock(int stockNumber){ //상품 재고 증가 메소드
        this.stockNumber += stockNumber;
    }

}
