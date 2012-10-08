package com.fotogourmet.codigos

import org.bson.types.ObjectId

class Codigosbarra {
	
	ObjectId id
	String ean
	String ingrediente	

	static constraints = {
		ean(nullable:false,blank:false)
	}
}
