package dev.huachin.java.spring.api_concert_tickets.dto.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// @interface es una anotación de Java que se utiliza para definir una anotación personalizada.
// @Documented indica que la anotación debe incluirse en la documentación generada por herramientas como Javadoc.
@Documented
// @Constraint se utiliza para definir una restricción de validación personalizada.
@Constraint(validatedBy = AlphaNumericWithSpacesValidator.class)
// @Target se utiliza para especificar en qué elementos de código se puede aplicar la anotación. Ejemplo: campos, métodos, parámetros.
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
// @Retention se utiliza para especificar cuánto tiempo debe estar disponible la anotación. En este caso, se retiene en tiempo de ejecución.
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphaNumericWithSpaces {
    // Se puede especificar el mensaje de error que se mostrará si la validación falla.
    String message() default "El campo debe contener solo letras, números y espacios";

    // Se puede especificar el grupo de validación al que pertenece esta anotación.
    Class<?>[] groups() default {};

    // Se puede especificar la carga adicional que se puede asociar con esta anotación.
    Class<? extends Payload>[] payload() default {};
}
