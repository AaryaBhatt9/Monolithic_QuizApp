package com.Aarya.QuizApp.service;

import com.Aarya.QuizApp.model.Question;
import com.Aarya.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionService
{

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try
        {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);  // here, we will be using findall instead of getAllQuestions();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);  // here, we will be using findall instead of getAllQuestions()
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
    {

            try
            {
                return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question)
    {
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question question)
    {
        Optional<Question> optionalQuestion =  questionDao.findById(id);
        if(optionalQuestion.isPresent())
        {

           Question existingQuestion  = optionalQuestion.get();


            existingQuestion.setQuestionTitle(question.getQuestionTitle());
            existingQuestion.setCategory(question.getCategory());
            existingQuestion.setOption1(question.getOption1());
            existingQuestion.setOption2(question.getOption2());
            existingQuestion.setOption3(question.getOption3());
            existingQuestion.setOption4(question.getOption4());
            existingQuestion.setRightAnswer(question.getRightAnswer());
            existingQuestion.setDifficultyLevel(question.getDifficultyLevel());

            questionDao.save(existingQuestion);
            return new ResponseEntity<>("Question Updated!!",HttpStatus.OK);
        }
        else
        {
            throw new RuntimeException("Question with id " + id+ " not found!!");
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id)
    {
        if(questionDao.existsById(id))
        {
            questionDao.deleteById(id);

            try
            {
                return new ResponseEntity<>("Question Deleted!!",HttpStatus.OK);
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
            return new ResponseEntity<>("Technical error",HttpStatus.BAD_REQUEST);
        }

        else
        {
            throw new RuntimeException("Question with id " + id + " not found!!");
        }


    }

    // In dao, we have save() instead of add method to add any new data field in our database.

}
