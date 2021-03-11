package com.mycompany.descorp;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Table;

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
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "salario", nullable = false)
    private double salario;
    
    @Column(name = "cargo", nullable = false, unique = true)
    private String cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    private Departamento departamento;
    
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    
    @OneToMany(mappedBy = "empregado", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="TB_PROJETO_REL", joinColumns={@JoinColumn(name="PROJETO_ID")}, inverseJoinColumns={@JoinColumn(name="EMPREGADO_ID")})
    private List<Projeto> projetos;
    
   
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

    public List<Conta> getContas() {
        return contas;
    }
    public void setContas(List<Conta> conta) {
        this.contas = conta;
    }
    
    public void addConta(Conta c) {
        this.contas.add(c);
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public List<Projeto> getProjetos() {
        return projetos;
    }
    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

}
