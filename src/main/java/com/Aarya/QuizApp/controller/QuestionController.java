package com.Aarya.QuizApp.controller;
import com.Aarya.QuizApp.model.Question;
import com.Aarya.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")

// Returning both data and status code means we use response entity.

public class QuestionController
{

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable  String category)
    {
        return questionService.getQuestionsByCategory(category);
    }


    // While adding questions, spring will be converting the JSON and will me automatically converting it to JSON.
    // RequestBody means the data is being transferred from client side to server side.
    @PostMapping("add")    // postMapping because we are sending data to server.
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
       return questionService.addQuestion(question);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id,@RequestBody Question question)
    {
        return questionService.updateQuestion(id,question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id)
    {
        return questionService.deleteQuestion(id);
    }



}
