package com.mycompany.descorp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

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
public class Empregado implements Serializable {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 
    private int id;
    
    @Column(name = "name")
    @NotBlank
    @Size(min = 1, max = 30)
    @Pattern(regexp= "\\p{Upper}{1}\\p{Lower}+", message = "com.mycompany.descorp.Empregado.name")
    private String name;
    
    @Column(name = "salario")
    @NotBlank
    private double salario;
    
    @Column(name = "cargo")
    @NotBlank
    @Size(min = 2, max = 15)
    private String cargo;
    
    @Column(name ="cpf", nullable =false, unique= true)
    @CPF
    private String cpf;
    
    @Column(name = "email")
    @Email
    private String email;
  
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    private Departamento departamento;
    
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private Endereco endereco;
   
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "empregado")
    private List<Conta> contas = new ArrayList<>();
    
    
    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }


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

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
