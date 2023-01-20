package com.encheres.encheres.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorieproduit")
public class CategorieProduit {

    @Id
    @DBColumn(primaryKey = true)
    @Column(name = "idcategorieproduit")
    private Long idcategorieproduit;
    
    @Column(name = "intitulecategorieproduit")
    private String intitule;

	public Long getIdcategorieproduit() {
		return idcategorieproduit;
	}

	public void setIdcategorieproduit(Long idcategorieproduit) {
		this.idcategorieproduit = idcategorieproduit;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
    
    
}
