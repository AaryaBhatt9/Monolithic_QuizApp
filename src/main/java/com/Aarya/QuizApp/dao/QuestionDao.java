package com.Aarya.QuizApp.dao;


import com.Aarya.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> // JpaRepository<class name ORM ,primary ket type>
{
        List<Question> findByCategory(String category);


        // we will be writing the JPQL query here

        @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
        List<Question> findRandomQuestionsByCategory(String category, int numQ);
}


