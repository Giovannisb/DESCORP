
package validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author David
 */
public class ValidaSalario implements ConstraintValidator<ValidaSalarioAnnotation, Double>{

    @Override
    public void initialize(ValidaSalarioAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value > 0.0;
    }
    
}
