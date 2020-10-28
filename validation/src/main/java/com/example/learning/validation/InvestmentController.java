package com.example.learning.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/investments")
@RequiredArgsConstructor
@Slf4j
public class InvestmentController {

    private final InvestmentRepository investmentRepository;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Investment getInvestmentById(@PathVariable Long id) {
        return investmentRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //todo: byla jeszcze jedna adnotacja pozwalająca wyłuskać obiekt z żądania, co to była za adnotacja?
    public Investment createInvestment(@RequestBody @Valid Investment investment) {
        return investmentRepository.save(investment);
    }

    @DeleteMapping("/{id}")
    //todo: ciągle nie jestem pewien tego jakie są dobre zasady projektowania API, chciałbym sobie opisać bardzo
    // proste api i co powinno w których wypadkach zwracać
    public void deleteInvestmentById(@PathVariable Long id) {
        investmentRepository.deleteById(id);
    }
}
