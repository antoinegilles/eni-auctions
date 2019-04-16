package fr.eni_ecole.jee.util;

import java.util.ResourceBundle;

public class Parametre {

	public static String lire(String cle){
		ResourceBundle bundle = ResourceBundle.getBundle("fr.eni_ecole.jee.util.param");
		
		return (null!=bundle) ? bundle.getString(cle) : null;
	}

}
