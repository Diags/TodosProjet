package com.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entity.Todo;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}