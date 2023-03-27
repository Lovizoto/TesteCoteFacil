/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetaManageBean;

import CrudBean.CrudBean;
import dbClasse.Planeta;
import dbControle.dbPlaneta;
import java.sql.Connection;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class PlanetaBean extends CrudBean<Planeta> {

    private Planeta planeta = new Planeta();
    private ArrayList<Planeta> planetas = new ArrayList<>();
    
    private dbPlaneta cnPlaneta;    
    public Connection DAO() {
       cnPlaneta.setPreInit(true);
       if (this.cnPlaneta.getConnection() == null || !cnPlaneta.isPreInit()){
           cnPlaneta = new dbPlaneta();
       }
       return cnPlaneta.getConnection();
    }    
    
    @Override
    public Planeta criaNovaEntidade() {
       return new Planeta();
    }

//    public void listarPlanetas() {
//        dbPlaneta cn = new dbPlaneta();
//        cn.selecionaPlanetas();
//        this.planetas = cn.getListPlaneta();
//    }
//
//    public Planeta getPlaneta() {
//        return planeta;
//    }
//
//    public void setPlaneta(Planeta planeta) {
//        this.planeta = planeta;
//    }
//
//    public ArrayList<Planeta> getPlanetas() {
//        return planetas;
//    }
//
//    public void setPlanetas(ArrayList<Planeta> planetas) {
//        this.planetas = planetas;
//    }

}
