package com.Aarya.QuizApp.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
public class Quiz
{
    // Since a quiz can have multiple questions, we will be having a list of questions.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String title;

@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
@JoinTable(
        name = "quiz_question",
        joinColumns = @JoinColumn(name = "quiz_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
)
private List<Question> questions ;

}
