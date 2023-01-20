package com.encheres.encheres.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@TableName
public class Utilisateur extends ObjetBDD{
	@Id
    @DBColumn(primaryKey = true)
	@Column(name = "idutilisateur")
    int idUtilisateur;
    @DBColumn
    @Column(name = "pseudo")
    String pseudo;
    @Column(name = "mdp")
    @DBColumn
    String mdp;  
    
    public Utilisateur() {
    }
    public Utilisateur(int idUtilisateur, String pseudo, String mdp) {
        this.idUtilisateur = idUtilisateur;
        this.pseudo = pseudo;
        this.mdp = mdp;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
