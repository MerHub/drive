/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Entities.Reclamation;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Belgaroui Ghazi
 */
public class ReclamationC {
       Connection cn =DataSource.getInstance().getConnexion();
   public void ajouterReclamation(Reclamation r){
          String requete ="insert into reclamation(id_client,sujet_rec,msg,etat,dateAjout) values (?,?,?,?,?) ";
        try {
          
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setInt(1,r.getId_client());
            pst.setString(2,r.getSujet_rec());
            pst.setString(3,r.getMsg());
            pst.setInt(4,r.getEtat());
            pst.setTimestamp(5,(Timestamp) r.getDateAjout());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationC.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    public List<Reclamation> afficher(){
          List<Reclamation> list =new ArrayList<>(); // array list Vectoc plus lent il ne pejut pas executer plusieurs en mm temps
          String requete = "select * from reclamation";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);// trajaa base de donnee huh
            while (rs.next()){
                Reclamation a = new Reclamation();
                a.setId_rec(rs.getInt(1));
                a.setSujet_rec(rs.getString(2));
                a.setMsg(rs.getString(3));
                a.setEtat(rs.getInt(4));
                a.setDateAjout(rs.getTimestamp(5));
                a.setId_client(rs.getInt(6));
                list.add(a);
            }
        }
         catch (SQLException ex) {
            Logger.getLogger(ReclamationC.class.getName()).log(Level.SEVERE, null, ex);
    }
        return list;
           
    }
     public void modifierReclamation(int id_rec,String sujet_rec, String msg) {
       
       String   requete = "update reclamation set sujet_rec=?,msg=?   where id_rec=?";
         try {
            PreparedStatement pt= cn.prepareStatement(requete);
            
            pt.setString(1,sujet_rec);
            pt.setString(2,msg);
            pt.setInt(3, id_rec);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void supprimerReclamation( int id)
     {
           try {
            PreparedStatement pt=cn.prepareStatement("delete from reclamation where id_rec=?");
           pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationC.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     }
}
