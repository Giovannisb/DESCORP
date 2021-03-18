package testes.validadores;

import javax.validation.ConstraintViolationException;

import com.mycompany.descorp.Projeto;
import java.util.ArrayList;

import org.junit.Test;
import tests.DESCORP.java.Teste;

public class ProjetoValidadorTeste extends Teste{
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirProjetoInvalido() {

//        Projeto p = new Projeto();
//        p.setNome("");
//        p.setEmpregados(new ArrayList<>());


    }
    public void atualizarProjetoInvalido() {}
}
