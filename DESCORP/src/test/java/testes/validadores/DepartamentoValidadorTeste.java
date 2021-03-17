
package testes.validadores;

import javax.validation.ConstraintViolationException;
import org.junit.Test;
import tests.DESCORP.java.Teste;

/**
 *
 * @author David
 */
public class DepartamentoValidadorTeste extends Teste{
    @Test(expected = ConstraintViolationException.class)
    public void persistirDepartamentoInvalido() {}
    public void atualizarDepartamentoInvalido() {}
}
