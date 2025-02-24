package com.example.demo.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "Must not be null")
    private String name;

    @NotNull(message = "Must not be null")
    @Size(message = "Esta fuera del rango entre tres digitos y maximo diez.", min = 3, max = 10)
    private String lastName;

    @NotBlank(message = "Must not be empty or null")
    @Pattern(message = "El correo tiene un formato incorrecto. Ejemplo: USERNAME@DOMINIO.COM", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    //@CreditCardNumber // Se utiliza para validar tarjetas de crédito
    private Long phone;

    @Min(18)
    @Max(100)
    private byte age;

    @Digits(message = "Se esperaban dos digitos enteros y tres decimales maximo", integer = 2, fraction = 3)
    private double height;

    //@AssertTrue debe ser verdadero
    //AssertFalse debe ser falso
    @AssertTrue
    private boolean married;

    // La fecha debe ser mayor a la fecha actual
    @Future
    //@Past la fecha debe ser anterior a la fecha actual
    private LocalDate dateOfBirth;

    @Valid
    private DepartmentDTO departmentDTO;
}
