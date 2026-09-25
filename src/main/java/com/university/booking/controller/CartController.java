package com.university.booking.controller;

import com.university.booking.dto.AddToCartRequest;
import com.university.booking.model.Booking;
import com.university.booking.model.Cart;
import com.university.booking.service.CartService;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{personId}")
    public ResponseEntity<Cart> addToCart(@PathVariable("personId") String personId, @Valid @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(personId, request));
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Cart> getCart(@PathVariable("personId") String personId) {
        Cart cart = cartService.getCart(personId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> clearCart(@PathVariable("personId") String personId) {
        cartService.clearCart(personId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{personId}/checkout")
    public ResponseEntity<List<Booking>> checkout(@PathVariable("personId") String personId) {
        return ResponseEntity.ok(cartService.checkout(personId));
    }
}
