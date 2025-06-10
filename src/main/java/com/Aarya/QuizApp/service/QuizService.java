package com.Aarya.QuizApp.service;

import com.Aarya.QuizApp.dao.QuestionDao;
import com.Aarya.QuizApp.dao.QuizDao;
import com.Aarya.QuizApp.model.Question;
import com.Aarya.QuizApp.model.QuestionWrapper;
import com.Aarya.QuizApp.model.Quiz;
import com.Aarya.QuizApp.model.Response;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);
        @Autowired
        QuizDao quizDao;

        @Autowired
        QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
        logger.info("Creating quiz with title: {}, category: {}, numQ: {}", title, category, numQ);

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        logger.info("Found {} questions", questions.size());


        if (questions.isEmpty()) {
            logger.warn("No questions found for category: {}", category);
            return new ResponseEntity<>("No questions found for category: " + category, HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        logger.info("Quiz {} created successfully", title);


        return new ResponseEntity<>("Success!! Quiz " + title + " created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>>  getQuiz(@PathVariable Integer id)
    {
            Optional<Quiz> quiz =  quizDao.findById(id);   // the data will be present in the object
            List<Question>  questionsFromDB = quiz.get().getQuestions(); //   .get().getQuestions() first get the object and then the questions, because we have used Optional<Quiz> quiz
            List<QuestionWrapper> questionsForUser = new ArrayList<>();

            for(Question q: questionsFromDB)
            {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForUser.add(qw);
            }

            return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

    }



    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses)
    {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
         int right =0,i=0;
        for(Response response: responses)
        {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
            {
                right++;
            }
            i++;
        }

            return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
