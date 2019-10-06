package io.github.HubertGalimski.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Transactional
    List<Todo> deleteAllByDone(boolean b);



}
