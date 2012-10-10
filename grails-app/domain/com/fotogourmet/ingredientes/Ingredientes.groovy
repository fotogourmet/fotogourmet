package com.fotogourmet.ingredientes

import org.bson.types.ObjectId

class Ingredientes {
	ObjectId id
	String ing
	
	static constraints = {
		ing(nullable:false,blank:false)
	}
}