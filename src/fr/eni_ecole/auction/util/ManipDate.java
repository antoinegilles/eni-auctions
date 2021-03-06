/**
 * Package pour les diff�rentes classes d'utilitaires.
 */
package fr.eni_ecole.auction.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe en charge de manipuler les dates mais qui permet aussi de convertir 
 * les objets SQL Date en Util.Java et inversement. 
 */
public class ManipDate {
	
	/**
	 * V�rification que la date est bien format� suivant ce format: dd/MM/yyyy
	 * @param laDate la date � v�rifier
	 * @return true si c'est bien format�, false sinon.
	 */
	private static boolean verifDate(String laDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean bValue= true;
		java.util.Date dateTmp = null;

		try{
			if (laDate == null) bValue=false;
			else {
				dateTmp = sdf.parse(laDate);
				if(dateTmp!=null && !laDate.equals(sdf.format(dateTmp)))
					bValue= false;
			}
		}catch (ParseException e){
			bValue= false;
		}
		
		return bValue;
	}
	
	/**
	 * Permet de calculer la diff�rence entre 2 dates.
	 * @param dateDebut date de d�but.
	 * @param dateFin date de fin.
	 * @return un entier
	 */
	public static int datesDiff(Date dateDebut, Date dateFin) {
		long diffCalul = 0;
		if (dateDebut != null && dateFin != null && dateFin.getTime() >= dateDebut.getTime()){
			diffCalul = dateFin.getTime() - dateDebut.getTime();
			diffCalul /= 31536000000L; //1000*60*60*24*365;
		}
		return (int)diffCalul;
	}

	/**
	 * Converti un champ string date en util.date.
	 * @param laDate le champ texte date à convertir
	 * @return la date de type java.util.Date
	 * @throws Exception Exception.
	 */
	public static java.util.Date stringVersUtil(String laDate) throws Exception{
		java.util.Date date=null;
		if (laDate != null && verifDate(laDate)){
			try{
				date = new SimpleDateFormat("yyyy-MM-dd").parse(laDate);
			}catch (Exception e){
				throw new Exception("Date non valide !");
			}
		}
		return date;
	}
	
	/**
	 * Retourne la date format� dans une chaine de caractere au format long.
	 * @param laDate � formater
	 * @return une date au format String long.
	 */
	public static String dateAuFormatLong(java.util.Date laDate){
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL,Locale.getDefault());
		return f.format(laDate);
	}
	/**
	 * Retourne la date format� dans une chaine de caractere au format cours.
	 * @param laDate � formater
	 * @return une date au format String cours.
	 */
	public static String dateAuFormatCours(java.util.Date laDate){
		DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.getDefault());
		return f.format(laDate);
	}

	/**
	 * Converti une jave.Util.Date vers une date au format SQL
	 * @param laDate date � convertir
	 * @return uneDate.
	 */
	public static java.sql.Date dateUtilVersSQL(java.util.Date laDate){
		if (laDate != null)
			return new java.sql.Date(laDate.getTime());
		else
			return null;
	}
	
	/**
	 * Converti une jave.sql.Date vers une date au format util.Date
	 * @param laDate date � convertir
	 * @return uneDate.
	 */
	public static java.util.Date dateSQLVersUtil(java.sql.Date laDate){
		if (laDate != null)
			return new java.util.Date(laDate.getTime());
		else
			return null;
	}

}
