package com.example.blog2.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findAll() {
       return em.createQuery("select b from Board b order by b.id DESC ", Board.class).getResultList();
    }

    public Optional<Board> findById(int id) {

        return Optional.ofNullable(em.find(Board.class, id));
    }


    public void save(Board board) {
        // 비영속 상태
       em.persist(board);
       // 동기화 완료 (영속화 완료)

    }


    public void delete(int id) {
        em.createQuery("delete from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }


}
