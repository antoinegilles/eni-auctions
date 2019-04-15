package fr.eni_ecole.auction.beans;

import java.util.Date;

public class Enchere {

	private Date dateEnchere;
	private int montant_enchere;
	public ArticleVendu articleVendu;
	public Utilisateur utilisateur;
	
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
}
