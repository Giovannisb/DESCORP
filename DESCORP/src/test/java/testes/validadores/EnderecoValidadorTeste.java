package testes.validadores;

import javax.validation.ConstraintViolationException;
import org.junit.Test;
import tests.DESCORP.java.Teste;

/**
 *
 * @author David
 */
public class EnderecoValidadorTeste extends Teste{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirEnderecoInvalido() {}
    public void atualizarEnderecoInvalido() {}
}
