package com.xenon.luxe.dto;

import com.xenon.luxe.entities.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
public class CartDto {
    private UUID id;
    private List<CartItem> items = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
