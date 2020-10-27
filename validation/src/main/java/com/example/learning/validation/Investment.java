package com.example.learning.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Size(min = 1,max = 24)
    @Email(message = "Wprowadź poprawny email")
    @NotBlank(message = "Email jest wymagany")
    @Column(unique = true, nullable = false)
    private String email;

    @Min(value = 1000, message = "Value should not be less than 1 000")
    @Max(value = 1000000, message = "Value should not be bigger than 1 000 0000")
    private BigDecimal initialCapital;

    @Min(value = 3, message = "Value should not be less than 3")
    @Max(value = 120, message = "Value should not be bigger than 1 000 0000")
    private Long duration;

    @DecimalMin(value = "0.5", message = "Value should not be less than 0.5")
    @DecimalMax(value = "10.0", message = "Value should not be bigger than 10")
    private BigDecimal returnRate;
}
