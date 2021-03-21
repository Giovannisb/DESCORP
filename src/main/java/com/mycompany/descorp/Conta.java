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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_CONTA")
@NamedQueries(
    {
        @NamedQuery(
            name = "Update.Conta",
            query = "UPDATE " + 
            "Conta c SET " + 
            "c.banco = :banco, " + 
            "c.conta = :conta, " +
            "c.digito = :digito, " +
            "c.agencia = :agencia " +
            "WHERE c.id = :id"
        )
    }
)
public class Conta implements Serializable {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY ) 
    private int id;

    @Column(name = "banco", nullable = false)
    @Size(min = 2, max = 15)
    private String banco;
    
    @Column(name = "conta", nullable = false)
    @NotBlank
    private String conta;
    
    @Column(name = "digito", nullable = false)
    private int digito;
    
    @Column(name = "agencia", nullable = false)
    @NotBlank
    private String agencia;
    
    
    @Column(name= "senha")
    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})", 
            message = "A senha deve possuir pelo menos um caractere de: pontuação, maiúscula, minúscula e número")
    private String senha;
    

    @ManyToOne
    @JoinColumn(name = "id_empregado")
    @NotNull
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}