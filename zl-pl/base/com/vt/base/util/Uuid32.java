package com.vt.base.util;

import java.util.UUID;



/**
 * @author Will
 *
 */
public class Uuid32 {

	public static String getUUID32() {  
          
        return UUID.randomUUID().toString().replace("-", "");  
    }
	
	public static void main(String[] args) {
		for(int i = 0;i<10;i++){
			System.out.println(getUUID32());
		}
	}
	
}
