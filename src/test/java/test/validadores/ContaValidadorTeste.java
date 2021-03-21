package test.validadores;

import com.mycompany.descorp.Conta;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import tests.DESCORP.java.Teste;

/**
 *
 * @author David
 */
public class ContaValidadorTeste extends Teste {
    @Test(expected = ConstraintViolationException.class)
    public void persistirContaInvalida() {
    
        Conta c =null;
        
        
        try {
            c = new Conta();
            c.setConta(""); //conta inválida
            c.setDigito(0);
            c.setBanco("");//banco inválido
            c.setAgencia("");//agencia inválida
            c.setEmpregado(null); //empregado inválido
            c.setSenha("Atx18$pqlx21K");
            em.persist(c);
            em.flush();
            
        } catch (ConstraintViolationException e) {
             Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

            constraintViolations.forEach(violation -> {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class com.mycompany.descorp.Conta.conta: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Conta.banco: tamanho deve ser entre 2 e 15"),
                                startsWith("class com.mycompany.descorp.Conta.agencia: não deve estar em branco"),
                                startsWith("class com.mycompany.descorp.Conta.empregado: não deve ser nulo")
                        )
                );
            });

            assertEquals(4, constraintViolations.size());
            assertEquals(c.getId(), 0);
            throw e;
        }

    
    
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarContaBancoInvalido() {
        
        int idp = 3;
        TypedQuery<Conta> query = em.createQuery("SELECT c FROM Conta c WHERE c.id = :id", Conta.class);
        query.setParameter("id", idp);
        Conta c = query.getSingleResult();
        c.setBanco("AOSDIJASLDASOJDOASIDOAISJDOIASJDOIJASIODJOIHKJASBKD"); //Banco inválido.
        //maior que 15 caracteres
        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("tamanho deve ser entre 2 e 15", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }

    }
    @Test(expected = ConstraintViolationException.class)
    public void atualizarContaSenhaInvalida() {
        
        int idp = 1;
        TypedQuery<Conta> query = em.createQuery("SELECT c FROM Conta c WHERE c.id = :id", Conta.class);
        query.setParameter("id", idp);
        Conta c = query.getSingleResult();
        c.setSenha("conta123"); //senha inválida
        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("A senha deve possuir pelo menos um caractere de: pontuação,"
                    + " maiúscula, minúscula e número", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }

    }
    
}
