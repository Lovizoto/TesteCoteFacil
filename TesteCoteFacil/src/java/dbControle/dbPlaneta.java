/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbControle;

import AbstractAPI.ClsPlanet;
import dbClasse.Planeta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class dbPlaneta {

    private int insert;
    private boolean preInit = false;
    private Statement stm, stm4;
    private Connection connection, connection3;
    private DAOConnection cn = new DAOConnection();
    private DAOConnection cn3 = new DAOConnection();
    private Planeta planeta = new Planeta();
    ArrayList<Planeta> listPlaneta = new ArrayList<>();

    public int getInsert() {
        return insert;
    }

    public void setInsert(int insert) {
        this.insert = insert;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public Statement getStm4() {
        return stm4;
    }

    public void setStm4(Statement stm4) {
        this.stm4 = stm4;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection3() {
        return connection3;
    }

    public void setConnection3(Connection connection3) {
        this.connection3 = connection3;
    }

    public DAOConnection getCn() {
        return cn;
    }

    public void setCn(DAOConnection cn) {
        this.cn = cn;
    }

    public DAOConnection getCn3() {
        return cn3;
    }

    public void setCn3(DAOConnection cn3) {
        this.cn3 = cn3;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }

    public ArrayList<Planeta> getListPlaneta() {
        return listPlaneta;
    }

    public void setListPlaneta(ArrayList<Planeta> listPlaneta) {
        this.listPlaneta = listPlaneta;
    }

    public boolean isPreInit() {
        return preInit;
    }

    public void setPreInit(boolean preInit) {
        this.preInit = preInit;
        if (preInit) {
            preInit();
        } else {
            finalizeInit();
        }
    }

    private void preInit() {
        try {
            this.cn = new DAOConnection();
            this.connection = this.cn.conectaDB();
            this.stm = this.connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void finalizeInit() {
        this.cn.desconectaDB();
        this.connection = null;
    }

    public int inserePlaneta() {
        return insercaoPlaneta();
    }

    private int insercaoPlaneta() {
        Statement stm2 = null;
        Connection connection2;
        DAOConnection cn2 = new DAOConnection();

        if (!this.preInit) {
            connection2 = cn2.conectaDB();
            try {
                stm2 = connection2.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            stm2 = this.stm;
        }
        ResultSet rs;
        String Query;

        try {
            Query = "INSERT INTO public.planeta("
                    + " nome_planeta, "
                    + " clima_planeta, "
                    + " terreno_planeta, "
                    + " qtde_filmes) "
                    + "VALUES ("
                    + "$$" + this.planeta.getNome() + "$$,"
                    + "$$" + this.planeta.getClima() + "$$, "
                    + "$$" + this.planeta.getTerreno() + "$$,"
                    + " " + this.planeta.getQtde() + ") "
                    + "RETURNING id_planeta AS R1";

            rs = stm2.executeQuery(Query);
            rs.next();
            if (rs.getInt("R1") > 0) {
                return 0;
            } else {
                return -1;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            if (!this.preInit) {
                cn2.desconectaDB();
                connection2 = null;
            }
            return - 1;
        }
    }

    public int selecionaPlanetas() {
        return selecaoPlanetas();
    }

    private int selecaoPlanetas() {
        Statement stm2 = null;
        Connection connection2;
        DAOConnection cn2 = new DAOConnection();

        if (!this.preInit) {
            connection2 = cn2.conectaDB();
            try {
                stm2 = connection2.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            stm2 = this.stm;
        }
        ResultSet rs;
        String Query;

        try {
            Query = "SELECT id_planeta AS R1,"
                    + " nome_planeta AS R2, "
                    + " clima_planeta AS R3, "
                    + " terreno_planeta AS R4, "
                    + " qtde_filmes AS R5 "
                    + " FROM public.planeta";

            rs = stm2.executeQuery(Query);
            ArrayList<Planeta> listPlaneta = new ArrayList<>();

            while (rs.next()) {
                Planeta p = new Planeta();
                p.setId(rs.getInt("R1"));
                p.setNome(rs.getString("R2"));
                p.setClima(rs.getString("R3"));
                p.setTerreno(rs.getString("R4"));
                p.setQtde(rs.getInt("R5"));
                listPlaneta.add(p);
            }

            this.listPlaneta = listPlaneta;
            listPlaneta = null;

            if (this.listPlaneta.size() > 0) {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return 0;
            } else {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return -2;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            if (!this.preInit) {
                cn2.desconectaDB();
                connection2 = null;
            }
            return -1;
        }
    }

    public int editaDados() {
        return edicaoDados();
    }

    private int edicaoDados() {
        Statement stm2 = null;
        Connection connection2;
        DAOConnection cn2 = new DAOConnection();
        if (!this.preInit) {
            connection2 = cn2.conectaDB();
            try {
                stm2 = connection2.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            stm2 = this.stm;
        }
        ResultSet rs;
        String Query;

        try {
            Query = "UPDATE public.planeta "
                    + "SET "
                    + " nome_planeta = $$" + this.planeta.getNome() + "$$,"
                    + " clima_planeta = $$" + this.planeta.getClima() + "$$, "
                    + " terreno_planeta = $$" + this.planeta.getTerreno() + "$$ "
                    + "WHERE id_planeta = " + this.planeta.getId() + " "
                    + "RETURNING id_planeta AS R1;";
            rs = stm2.executeQuery(Query);
            if (rs.next()) {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return 0;
            } else {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return -2;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            if (!this.preInit) {
                cn2.desconectaDB();
                connection2 = null;
            }
            return -1;
        }
    }

    public int buscaPlanetaPeloNome() {
        return buscandoPlanetaPeloNome();
    }

    private int buscandoPlanetaPeloNome() {
        Statement stm2 = null;
        Connection connection2;
        DAOConnection cn2 = new DAOConnection();
        if (!this.preInit) {
            connection2 = cn2.conectaDB();
            try {
                stm2 = connection2.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            stm2 = this.stm;
        }
        ResultSet rs;
        String Query;

        try {
            Query = "SELECT id_planeta AS R1,"
                    + " nome_planeta AS R2, "
                    + " clima_planeta AS R3, "
                    + " terreno_planeta AS R4, "
                    + " qtde_filmes AS R5 "
                    + " FROM public.planeta "
                    + "WHERE nome_planeta = $$" + this.planeta.getNome() + "$$;";

            rs = stm2.executeQuery(Query);
            Planeta planeta = new Planeta();
            if (rs.next()) {
                planeta.setId(rs.getInt("R1"));
                planeta.setNome(rs.getString("R2"));
                planeta.setClima(rs.getString("R3"));
                planeta.setTerreno(rs.getString("R4"));
                planeta.setQtde(rs.getInt("R5"));
            }
            this.planeta = planeta;
            planeta = null;

            if (rs.getInt("R1") > 0) {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return 0;
            } else {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return -2;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            if (!this.preInit) {
                cn2.desconectaDB();
                connection2 = null;
            }
            return -1;
        }
    }

    public int buscaPlanetaPeloId() {
        return buscandoPlanetaPeloId();
    }

    private int buscandoPlanetaPeloId() {
        Statement stm2 = null;
        Connection connection2;
        DAOConnection cn2 = new DAOConnection();
        if (!this.preInit) {
            connection2 = cn2.conectaDB();
            try {
                stm2 = connection2.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            stm2 = this.stm;
        }
        ResultSet rs;
        String Query;

        try {
            Query = "SELECT id_planeta AS R1,"
                    + " nome_planeta AS R2, "
                    + " clima_planeta AS R3, "
                    + " terreno_planeta AS R4, "
                    + " qtde_filmes AS R5 "
                    + " FROM public.planeta "
                    + "WHERE id_planeta = " + this.planeta.getId() + ";";

            rs = stm2.executeQuery(Query);
            Planeta planeta = new Planeta();
            if (rs.next()) {
                planeta.setId(rs.getInt("R1"));
                planeta.setNome(rs.getString("R2"));
                planeta.setClima(rs.getString("R3"));
                planeta.setTerreno(rs.getString("R4"));
                planeta.setQtde(rs.getInt("R5"));
            }
            this.planeta = planeta;
            planeta = null;

            if (rs.getInt("R1") > 0) {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return 0;
            } else {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return -2;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            if (!this.preInit) {
                cn2.desconectaDB();
                connection2 = null;
            }
            return -1;
        }
    }

    public int deletaPlaneta() {
        return deletandoPlaneta();
    }

    private int deletandoPlaneta() {
        Statement stm2 = null;
        Connection connection2;
        DAOConnection cn2 = new DAOConnection();
        if (!this.preInit) {
            connection2 = cn2.conectaDB();
            try {
                stm2 = connection2.createStatement();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            stm2 = this.stm;
        }
        ResultSet rs;
        String Query;

        try {
            Query = "DELETE "
                    + "FROM public.planeta "
                    + "WHERE id_planeta = " + this.planeta.getId() + ";";

            rs = stm2.executeQuery(Query);
            if (rs.next()) {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return 0;
            } else {
                if (!this.preInit) {
                    cn2.desconectaDB();
                    connection2 = null;
                }
                return -2;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            if (!this.preInit) {
                cn2.desconectaDB();
                connection2 = null;
            }
            return -1;
        }

    }

}
