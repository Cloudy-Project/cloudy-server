package com.cloudy.server.answer.service;

import com.cloudy.server.answer.domain.Answer;
import com.cloudy.server.answer.repository.AnswerRepository;
import com.cloudy.server.letter.domain.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer findByLetter(Letter letter) {
        return answerRepository.findByLetterId(letter.getId()).orElse(null);
    }
}
