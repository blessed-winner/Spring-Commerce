package com.xenon.store.dto;

import com.xenon.store.entities.CartItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class CartDto {
    private UUID id;
    private LocalDateTime createdAt;
    private Set<CartItem> items = new LinkedHashSet<>();
}
