package com.example.dto;

import com.example.entity.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartResponse {

    private Long userId;
    private List<CartItem> items;
    private Double totalAmount;

}