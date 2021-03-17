package com.mycompany.descorp;

import com.mycompany.descorp.Conta;
import com.mycompany.descorp.Departamento;
import com.mycompany.descorp.Endereco;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-03-16T23:42:26")
@StaticMetamodel(Empregado.class)
public class Empregado_ { 

    public static volatile SingularAttribute<Empregado, Endereco> endereco;
    public static volatile SingularAttribute<Empregado, Double> salario;
    public static volatile SingularAttribute<Empregado, String> name;
    public static volatile SingularAttribute<Empregado, Departamento> departamento;
    public static volatile SingularAttribute<Empregado, Integer> id;
    public static volatile ListAttribute<Empregado, Conta> contas;
    public static volatile SingularAttribute<Empregado, String> cargo;

}