package com.mycompany.descorp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import validadores.ValidaSalarioAnnotation;

/**
 *
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
@DiscriminatorValue(value = "E")
@PrimaryKeyJoinColumn(name="ID_USUARIO", referencedColumnName = "ID")
public class Empregado extends Usuario implements Serializable {

    
    @Column(name = "salario")
    @ValidaSalarioAnnotation
    private double salario;
    
    @Column(name = "cargo")
    @NotBlank
    private String cargo;
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    @NotNull
    private Departamento departamento;
    

    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "empregado")
    private List<Conta> contas = new ArrayList<>();
    
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        joinColumns={@JoinColumn(name="id_empregado")},
        inverseJoinColumns={@JoinColumn(name="id_projeto")}
    )
    private List<Projeto> projetos;
    
    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }


    public Empregado(Long id, 
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


    @Override
    public String toString() {
        return "com.mycompany.descorp.Empregado[ id=" + id + " ]";
    }

}
