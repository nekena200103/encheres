package com.encheres.encheres.classes;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@TableName
@Table(name = "enchere")
public class Enchere extends ObjetBDD{
	@Id
    @Column(name = "idenchere")
	@DBColumn(primaryKey = true)
    private int idenchere;
	@DBColumn
    @Column(name = "dateenchere")
    private Timestamp dateEnchere;
	@DBColumn
    @Column(name = "gagnant")
    private Long gagnant;
	@DBColumn
    @Column(name = "idenchereur")
    private Long enchereur;
	@DBColumn
    @Column(name = "sommedepart")
    private Long sommeDepart;
	@DBColumn
    @Column(name = "datefin")
    private Timestamp dateFin;
	@DBColumn
    @Column(name = "commission")
    private Double commission;
    
    @Column(name = "prixminimumdevente")
    private Long prixMinimumDeVente;
    
    @Column(name = "designationproduit")
    private String designationProduit;
    
    @Column(name = "idcategorie")
    private Long categorie;
    
    @Column(name = "statut")
    private String statut;

	

	

	public int getIdEnchere() {
		return idenchere;
	}

	public void setIdEnchere(int idenchere) {
		this.idenchere = idenchere;
	}

	public Timestamp getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Long getGagnant() {
		return gagnant;
	}

	public void setGagnant(Long gagnant) {
		this.gagnant = gagnant;
	}

	public Long getEnchereur() {
		return enchereur;
	}

	public void setEnchereur(Long enchereur) {
		this.enchereur = enchereur;
	}

	public Long getSommeDepart() {
		return sommeDepart;
	}

	public void setSommeDepart(Long sommeDepart) {
		this.sommeDepart = sommeDepart;
	}

	public Timestamp getDateFin() {
		return dateFin;
	}

	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Long getPrixMinimumDeVente() {
		return prixMinimumDeVente;
	}

	public void setPrixMinimumDeVente(Long prixMinimumDeVente) {
		this.prixMinimumDeVente = prixMinimumDeVente;
	}

	public String getDesignationProduit() {
		return designationProduit;
	}

	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}

	public Long getCategorie() {
		return categorie;
	}

	public void setCategorie(Long categorie) {
		this.categorie = categorie;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

  
    
    
}
