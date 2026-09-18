package com.university.booking.service;

import com.university.booking.dto.AddToCartRequest;
import com.university.booking.dto.BookingRequest;
import com.university.booking.enums.RedisKeyPrefix;
import com.university.booking.model.Booking;
import com.university.booking.model.Cart;
import com.university.booking.model.CartItem;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final BookingService bookingService;

    @Value("${app.cart.ttl-seconds:900}")
    private long cartTtlSeconds;

    public Cart addToCart(String personId, AddToCartRequest request) {

        String key = RedisKeyPrefix.CART.getKey(personId);
        Cart cart = getCart(personId);

        if (cart == null) {
            cart = Cart.builder()
                    .personId(personId)
                    .items(new ArrayList<>())
                    .build();
        }

        CartItem item = CartItem.builder()
                .id(UUID.randomUUID().toString())
                .roomId(request.getRoomId())
                .categoryId(request.getCategoryId())
                .eventName(request.getEventName())
                .eventDate(request.getEventDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .participantCount(request.getParticipantCount())
                .contactPhone(request.getContactPhone())
                .build();

        cart.getItems().add(item);

        redisTemplate.opsForValue().set(key, cart, Duration.ofSeconds(cartTtlSeconds));
        return cart;
    }

    public Cart getCart(String personId) {
        String key = RedisKeyPrefix.CART.getKey(personId);
        Object obj = redisTemplate.opsForValue().get(key);

        return obj != null ? (Cart) obj : null;
    }

    public void clearCart(String personId) {
        String key = RedisKeyPrefix.CART.getKey(personId);

        redisTemplate.delete(key);
    }

    public List<Booking> checkout(String personId) {
        Cart cart = getCart(personId);
        if (cart == null || cart.getItems().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty or has expired");

        List<Booking> createdBookings = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            
            BookingRequest bookingRequest = new BookingRequest();
            bookingRequest.setPersonId(personId);
            bookingRequest.setRoomId(item.getRoomId());
            bookingRequest.setCategoryId(item.getCategoryId());
            bookingRequest.setEventName(item.getEventName());
            bookingRequest.setEventDate(item.getEventDate());
            bookingRequest.setStartTime(item.getStartTime());
            bookingRequest.setEndTime(item.getEndTime());
            bookingRequest.setParticipantCount(item.getParticipantCount());
            bookingRequest.setContactPhone(item.getContactPhone());

            Booking booking = bookingService.createBooking(bookingRequest);
            createdBookings.add(booking);
        }

        clearCart(personId);
        return createdBookings;
    }
}
