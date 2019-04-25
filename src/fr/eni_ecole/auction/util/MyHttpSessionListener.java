package fr.eni_ecole.auction.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener{
	  public void sessionCreated(HttpSessionEvent event){
	    event.getSession().setMaxInactiveInterval(15*60); //in seconds
	  }
	  public void sessionDestroyed(HttpSessionEvent event){}
	}