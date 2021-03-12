/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.descorp;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Giovanni
 */

@Embeddable
public class RelProjeto implements Serializable {
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_empregado")    
    private Empregado empregado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_projeto")    
    private Projeto projeto;

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((empregado == null) ? 0 : empregado.hashCode());
        result = prime * result + ((empregado == null) ? 0 : empregado.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RelProjeto empregado = (RelProjeto) obj;
        if (empregado == null) {
            if (empregado.empregado != null) {
                return false;
            }
        } else if (!empregado.equals(empregado.empregado)) {
            return false;
        }
        if (projeto == null) {
            if (empregado.projeto != null) {
                return false;
            }
        } else if (!projeto.equals(empregado.projeto)) {
            return false;
        }
        return true;
    }
    
}
