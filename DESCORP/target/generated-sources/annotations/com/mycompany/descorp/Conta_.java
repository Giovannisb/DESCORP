package com.mycompany.descorp;

import com.mycompany.descorp.Empregado;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-03-09T00:16:15")
@StaticMetamodel(Conta.class)
public class Conta_ { 

    public static volatile SingularAttribute<Conta, Empregado> empregado;
    public static volatile SingularAttribute<Conta, String> conta;
    public static volatile SingularAttribute<Conta, String> banco;
    public static volatile SingularAttribute<Conta, Integer> id;
    public static volatile SingularAttribute<Conta, Integer> digito;
    public static volatile SingularAttribute<Conta, String> agencia;

}