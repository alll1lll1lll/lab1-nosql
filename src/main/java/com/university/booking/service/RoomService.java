package com.university.booking.service;

import com.university.booking.dto.RoomRequest;
import com.university.booking.model.Room;
import com.university.booking.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room createRoom(RoomRequest request) {

        Room room = Room.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .type(request.getType())
                .capacity(request.getCapacity())
                .location(request.getLocation())
                .build();

        return roomRepository.save(room);
    }

    public Room getRoom(String id) {
        
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Room not found with id: " + id));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void deleteRoom(String id) {
        roomRepository.delete(id);
    }
}