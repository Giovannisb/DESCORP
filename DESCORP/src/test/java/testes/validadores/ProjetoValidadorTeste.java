package testes.validadores;

import javax.validation.ConstraintViolationException;
import org.junit.Test;
import tests.DESCORP.java.Teste;

public class ProjetoValidadorTeste extends Teste{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirProjetoInvalido() {}
    public void atualizarProjetoInvalido() {}
}
