package com.Aarya.QuizApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Table(name = "question")
@Data  // lombok annotation as we do not want to create getter and setter of table columns
@Entity // ORM is being done and also table name is same as class name here.
public class Question
{
    @Id             // because Id is a primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // Auto generation strategy is being followed here..
    private Integer id;
    private String category;
    @Column(name = "difficulty_level")
    private String difficultyLevel;
    @Column(name = "question_title")
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @Column(name = "right_answer")
    private String rightAnswer;


    @ManyToMany(mappedBy = "questions")
    private List<Quiz> quizzes;


}
