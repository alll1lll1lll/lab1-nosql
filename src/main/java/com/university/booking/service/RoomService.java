package com.university.booking.service;

import com.university.booking.dto.RoomRequest;
import com.university.booking.exception.ResourceNotFoundException;
import com.university.booking.model.Room;
import com.university.booking.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
                .teacherOnly(request.isTeacherOnly())
                .build();

        return roomRepository.save(room);
    }

    public Room getRoom(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }
}
