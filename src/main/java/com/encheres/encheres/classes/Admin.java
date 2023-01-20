package com.encheres.encheres.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@TableName
@Table(name="Admin")
public class Admin extends ObjetBDD{
	@Id
    @DBColumn(primaryKey = true)
    @Column
    int idAdmin;
    @DBColumn
    @Column
    String pseudo;
    @Column
    @DBColumn
    String mdp;  
    
    public Admin() {
    }
    public Admin(int idAdmin, String pseudo, String mdp) {
        this.idAdmin = idAdmin;
        this.pseudo = pseudo;
        this.mdp = mdp;
    }
    public int getIdAdmin() {
        return idAdmin;
    }
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
   
}
