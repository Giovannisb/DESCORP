package com.mycompany.descorp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_CONTA")
@NamedQueries(
    {
        @NamedQuery(
            name = "Update.Conta.banco",
            query = "UPDATE Conta c SET c.banco = :banco WHERE c.id = :id"
        ),
        
        @NamedQuery(
            name = "Update.Conta.conta",
            query = "UPDATE Conta c SET c.conta = :conta WHERE c.id = :id"
        ),
        
        @NamedQuery(
            name = "Update.Conta.digito",
            query = "UPDATE Conta c SET c.digito = :digito WHERE c.id = :id"
        ),
        
        @NamedQuery(
            name = "Update.Conta.agencia",
            query = "UPDATE Conta c SET c.agencia = :agencia WHERE c.id = :id"
        ),
        
        @NamedQuery(
            name = "Delete.Conta",
            query = "DELETE FROM Conta c WHERE c.id = :id "
        )
    }
)
public class Conta implements Serializable {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 
    private int id;

    @Column(name = "banco", nullable = false)
    @Size(min = 2, max = 15)
    @NotBlank
    private String banco;
    
    @Column(name = "conta", nullable = false)
    @NotBlank
    private String conta;
    
    @Column(name = "digito", nullable = false)
    @Size(max = 3)
    @NotBlank
    private int digito;
    
    @Column(name = "agencia", nullable = false)
    @NotBlank
    private String agencia;
    

    @ManyToOne
    @JoinColumn(name = "id_empregado")
    private Empregado empregado;

    public Conta(int id, String banco, String conta, int digito, String agencia){
        super();
        this.id = id;
        this.banco = banco;
        this.conta = conta;
        this.digito = digito;
        this.agencia = agencia;
    }

    public Conta(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public int getDigito() {
        return digito;
    }

    public void setDigito(int digito) {
        this.digito = digito;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }
}