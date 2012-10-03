package com.fotogourmet.codigos

import org.bson.types.ObjectId

class Codigos {
	
	ObjectId id
	String codigo
	String descripcion	

	static constraints = {
		codigo(nullable:false,blank:false)
	}
}
