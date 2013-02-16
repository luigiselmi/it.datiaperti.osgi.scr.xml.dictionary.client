package it.datiaperti.osgi.scr.xml.dictionary.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.osgi.service.component.ComponentContext;

import it.datiaperti.osgi.scr.xml.dictionary.service.api.DictionaryService;;



public class DictionaryClient {
  
  private DictionaryService service;

  protected void activate(ComponentContext context) throws Exception {
	  
	  System.out.println("Dictionary Client OSGi Component Active");
	  
	  checkWords();
      
  }
  
  protected void deactivate(ComponentContext context) throws Exception {
	  
	  System.out.println("Dictionary Client OSGi Component Disabled");
  
  }
  
  protected void bindDictionaryService(DictionaryService service) {
	  
	  this.service = service;
	  
  }
  
  protected void unbindDictionaryService(DictionaryService service) {
	  
	  this.service = null;
	  
  }
  
  private void checkWords() {
	  
	  System.out.println("Dictionary check words started.");
	  
	  if (service != null)
      {
          try
          {
              System.out.println("Enter a word or blank line to exit.");
              BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
              
              String word = "";

              // Loop endlessly.
              while (true)
              {
                  // Ask the user to enter a word.
                  System.out.print("Enter word: ");
                  word = reader.readLine();

                  // If the user entered a blank line, then
                  // exit the loop.
                  if (word.length() == 0)
                  {
                      break;
                  }

                  
                  // check if the word is correct.
                  if (service.checkWord(word))
                  {
                      System.out.println("Correct.");
                  }
                  else
                  {
                      System.out.println("Incorrect.");
                  }
              
              }
              
              System.out.println("Dictionary check words terminated.");
              
          } 
          catch (IOException ioe) {
        	  
        	  System.out.println("IO Exception caught.");
        	  ioe.printStackTrace();
          }
          
          
      }
      else
      {
          System.out.println("Couldn't find any dictionary service.");
      }
	  
  }
  
  
  
}
