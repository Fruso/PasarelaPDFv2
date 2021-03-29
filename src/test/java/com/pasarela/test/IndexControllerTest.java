package com.pasarela.test;


import com.pasarela.controller.IndexController;
import com.pasarela.exception.AuthException;
import com.pasarela.exception.EstadoException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;

@SpringBootTest
class IndexControllerTest {

	@Autowired
	private IndexController indexController;
	String statusTest = "{status=is work!}";
	
        @Test
	void statusOk() {
            //Verifica que el servicio este apto para ser iniciado
         try{
             
            indexController = new IndexController();
            String data = String.valueOf(indexController.index());
            System.out.println(data);
            System.out.println(statusTest);
            
            assert(new String(data).equals(statusTest));
         }
         catch(Exception e){
	        System.out.println(e);
         }       
            
	}
	
        
}
