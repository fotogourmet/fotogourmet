package com.pijosoft.pijorecipes

import org.bson.types.ObjectId

class Recipe {
	
ObjectId id
	String nombre
	String imagenPpal
	String ingredientes
	String ingredientesB
	String procedimiento
	
	static mapping = {
		nombre index:true
		collection "recetas"
}

    static constraints = {	
		nombre(nullable:false,blank:false)
    }
}
