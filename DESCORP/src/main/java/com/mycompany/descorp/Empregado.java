/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.descorp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Giovanni
 * @author David
 */

@Entity
@Table(name = "TB_EMPREGADO")
@NamedQueries(
    {
        @NamedQuery(
            name = "Update.Empregado",
            query = "UPDATE Empregado e SET e.salario = :salario WHERE e.id = :id"
        ),

        @NamedQuery(
            name = "Delete.Empregado",
            query = "DELETE FROM Empregado e WHERE e.id = :id "
        )
    }
)
public class Empregado implements Serializable {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "salario", nullable = false)
    private double salario;
    
    @Column(name = "cargo", nullable = false, unique = true)
    private String cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    private Departamento departamento;
    
    public Empregado(int id, 
    String name, double salario, String cargo){
        super( );
        this.id = id;
        this.name = name;
        this.salario = salario;
        this.cargo = cargo;
    }

    public Empregado( ){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
