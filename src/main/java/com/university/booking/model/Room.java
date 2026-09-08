package com.university.booking.model;

import com.university.booking.enums.RoomType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room implements Serializable {

    private String id;
    private String name;
    private RoomType type;
    private int capacity;
    private String location;
}
