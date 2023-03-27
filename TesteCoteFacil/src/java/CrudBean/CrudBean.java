/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudBean;

import dbClasse.Planeta;
import dbControle.dbPlaneta;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
public abstract class CrudBean<E> {

    private String estadoTela = "buscar"; //inserir, editar, buscar
    private E entidade;
    private ArrayList<E> entidades;

    public void novo() {
        entidade = criaNovaEntidade();
        mudarParaInserir();
    }

    public void salvar() {
        try {
            dbPlaneta cnDb = new dbPlaneta();
            cnDb.setPlaneta((Planeta) entidade);

            if (cnDb.getPlaneta().getId() > 0) {
                if (cnDb.editaDados() == 0) {
                    adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
                    entidade = criaNovaEntidade();
                    mudarParaBuscar();
                } else {
                    adicionarMensagem("Não foi possível salvar os dados!", FacesMessage.SEVERITY_FATAL);
                }

            } else {
                if (cnDb.inserePlaneta() == 0) {
                    adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
                    entidade = criaNovaEntidade();
                    mudarParaBuscar();
                } else {
                    adicionarMensagem("Não foi possível salvar os dados!", FacesMessage.SEVERITY_FATAL);
                }
            }

        } catch (Exception ex) {
            System.out.println("ex = " + ex);
        }
    }

    public void editar(E entidade) {
        try {
            dbPlaneta cnDb = new dbPlaneta();
            cnDb.setPlaneta((Planeta) entidade);
            cnDb.deletaPlaneta();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    public void deletar(E entidade) {
        this.entidade = entidade;

    }

    public void procurar() {
        if (isBuscar() == false) {
            mudarParaBuscar();
            return;
        }
        try {
            dbPlaneta cnDbPlaneta = new dbPlaneta();
            cnDbPlaneta.selecionaPlanetas();
            this.entidades = (ArrayList<E>) cnDbPlaneta.getListPlaneta();

            if (this.entidades == null || this.entidades.size() < 1) {
                adicionarMensagem("Não há nada cadastrado!", FacesMessage.SEVERITY_ERROR);
            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }

    }

    public void adicionarMensagem(String msg, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public ArrayList<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(ArrayList<E> entidades) {
        this.entidades = entidades;
    }

    //Reponsável por criar os métodos nas classes bean
    public abstract E criaNovaEntidade();

    public boolean isInserir() {
        return "inserir".equals(this.estadoTela);
    }

    public boolean isEditar() {
        return "editar".equals(this.estadoTela);
    }

    public boolean isBuscar() {
        return "buscar".equals(this.estadoTela);
    }

    public void mudarParaInserir() {
        this.estadoTela = "inserir";
    }

    public void mudarParaEditar() {
        this.estadoTela = "editar";
    }

    public void mudarParaBuscar() {
        this.estadoTela = "buscar";
    }

}
