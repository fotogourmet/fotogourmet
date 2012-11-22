package com.fotogourmet.recetas

import org.bson.types.ObjectId

class Recipe {
	
	ObjectId id
	String nombre
	String imagenPpal
	String ingredientes
	String ingredientesB
	String procedimiento
	String categoria
	float calificacion
	float sumaCalificaciones
	float totalCalificaciones
	String video

	static mapping = {
		nombre index:true
		collection "recetas"
}

    static constraints = {	
		nombre(nullable:false,blank:false)
    }
}
