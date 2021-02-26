/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import tests.DESCORP.java.DbUnitUtil;
import com.mycompany.descorp.Departamento;
import java.text.ParseException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
public class TesteDepartamento extends Teste {
   
    @Test
    public void persistDepartamento() {
        Departamento aux = new Departamento();
        aux.setName("VENDAS");
        em.persist(aux);
        em.flush(); //força que a persistência realizada vá para o banco neste momento.

        assertNotNull(aux.getId());
    }
    
    @Test
    public void consultDepartamento() throws ParseException {
        Departamento departamento;
        departamento = em.find(Departamento.class, 1);

        assertEquals("TI", departamento.getName());
        assertEquals(1, departamento.getId());
    }

}
