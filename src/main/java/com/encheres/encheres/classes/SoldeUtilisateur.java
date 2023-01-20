package com.encheres.encheres.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Date;

	// JPA Entity
	@Entity
	@Table(name = "soldeutilisateur")
	public class SoldeUtilisateur {

	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "soldeutilisateur_idsoldeutilisateur_seq")
	    @SequenceGenerator(name = "soldeutilisateur_idsoldeutilisateur_seq", sequenceName = "soldeutilisateur_idsoldeutilisateur_seq", allocationSize = 1)
	    private Integer idsoldeutilisateur;

	    private Integer valeur;

	    @Column(name = "dateoperation")
	    private Date dateOperation;

	    @ManyToOne
	    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
	    private Utilisateur utilisateur;

	    // Getters and Setters
	    public Integer getIdsoldeutilisateur() {
	        return idsoldeutilisateur;
	    }
	    public void setIdsoldeutilisateur(Integer idsoldeutilisateur) {
	        this.idsoldeutilisateur = idsoldeutilisateur;
	    }
	    public Integer getValeur() {
	        return valeur;
	    }
	    public void setValeur(Integer valeur) {
	        this.valeur = valeur;
	    }
	    
	    public Date getDateOperation() {
			return dateOperation;
		}
		public void setDateOperation(Date dateOperation) {
			this.dateOperation = dateOperation;
		}
		public Utilisateur getUtilisateur() {
	        return utilisateur;
	    }
	    public void setUtilisateur(Utilisateur utilisateur) {
	        this.utilisateur = utilisateur;
	    }
	}


