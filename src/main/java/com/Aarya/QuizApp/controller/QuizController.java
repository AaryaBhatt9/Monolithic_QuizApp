package com.Aarya.QuizApp.controller;

import com.Aarya.QuizApp.model.Question;
import com.Aarya.QuizApp.model.QuestionWrapper;
import com.Aarya.QuizApp.model.Response;
import com.Aarya.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// http://localhost:8080/quiz/create?category=java&numQ=5&title=JQuiz
@RestController
@RequestMapping("quiz")
public class QuizController
{

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title)
    {
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id)
    {
        return quizService.getQuiz(id);
    }

    // Integer in ResponseEntity because we will be getting back the quiz score
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
        return quizService.calculateResult(id,responses);
    }


}
