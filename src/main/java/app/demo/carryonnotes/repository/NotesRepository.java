package app.demo.carryonnotes.repository;

import app.demo.carryonnotes.entity.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<Note, Long> {

    @Query(value= "SELECT * FROM Note n WHERE n.user_id = :userId",
            nativeQuery = true)
    List<Note> findAllByUserId(@Param("userId") Long userId);
}
