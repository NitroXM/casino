package org.casino.repositories;

import org.casino.gameplay.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findById(long id);

}
