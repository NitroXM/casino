package org.casino.sevices;

import org.casino.gameplay.Room;
import org.casino.repositories.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room findById(long id) {
        return roomRepository.findById(id);
    }

    public void save(Room room) {
        roomRepository.save(room);
    }

}
