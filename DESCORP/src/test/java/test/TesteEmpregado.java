/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mycompany.descorp.Departamento;
import tests.DESCORP.java.DbUnitUtil;
import com.mycompany.descorp.Empregado;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Giovanni
 * @author David
 */
public class TesteEmpregado extends Teste{
    @Test
    public void persistEmpregado() {
        Empregado aux = new Empregado();
        aux.setName("Ulisses");
        aux.setCargo("DIRETOR");
        aux.setSalario(15800.00);
        
        Departamento dpto = em.find(Departamento.class, 1);
        aux.setDepartamento(dpto);
        em.persist(aux);
        em.flush(); //força que a persistência realizada vá para o banco neste momento.

        assertNotNull(aux.getId());
        assertEquals(aux.getDepartamento().getName(), "TI");
    }
    
    @Test
    public void consultDepartamento() throws ParseException {
        Empregado empregado;
        empregado = em.find(Empregado.class, 1);

        assertEquals("Giovanni", empregado.getName());
        assertEquals(1, empregado.getId());
    }
    
    @Test
    public void updateEmpregado(){
        TypedQuery<Empregado> query = em.createNamedQuery("Update.Empregado",
                Empregado.class);
        double sal = 4000.00;
        query.setParameter("salario", sal).setParameter("id", 1);
        int r = query.executeUpdate();
        assertEquals(1, r);
        Empregado empregado = em.find(Empregado.class, 1);
        em.refresh(empregado);
        System.out.println(empregado.getSalario());
        assertTrue(sal == empregado.getSalario());
    }
    
    @Test
    public void deleteEmpregado(){
        TypedQuery<Empregado> query = em.createNamedQuery("Delete.Empregado",
                Empregado.class);
        query.setParameter("id", 3);
        int r = query.executeUpdate();
        assertEquals(1, r);
    }
}
