package testes.validadores;

import javax.validation.ConstraintViolationException;
import org.junit.Test;
import tests.DESCORP.java.Teste;

/**
 *
 * @author David
 */
public class EmpregadoValidadorTeste extends Teste {
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirEmpregadoInvalido() {}
    public void atualizarEmpregadoInvalido() {}
}
