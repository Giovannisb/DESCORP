package tests.DESCORP.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 *
 * @author Giovanni
 * @author David
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        test.TesteDepartamento.class,
        test.TesteConta.class,
        test.TesteEmpregado.class,
        test.TesteEndereco.class,
        test.TesteProjeto.class,
        test.validadores.ContaValidadorTeste.class,
        test.validadores.DepartamentoValidadorTeste.class,
        test.validadores.EmpregadoValidadorTeste.class,
        test.validadores.EnderecoValidadorTeste.class,
        test.validadores.ProjetoValidadorTeste.class,
        })
public class TestSuite {
    
}
