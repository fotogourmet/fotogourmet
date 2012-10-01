package com.fotogourmet.recetas

import com.fotogourmet.recetas.Recipe;
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.fotogourmet.exceptions.BadRequestException
import org.bson.types.ObjectId

class RecipeService {
	
	static transactional = false
	
	def authService
	
	def availableSearchParams = [
		'ingredientesB'	
	]

    def get(String id) {
		log.debug "Querying with id: $id"
		ObjectId oId = new ObjectId(id)
		return Recipe.get(oId)?.filterResult()
    }
	
	def search(def params) {
			
		def filteredParams = params.findAll{it.key in availableSearchParams}
				
		log.debug "Filtered params: $filteredParams"
		if (!filteredParams)
			throw new BadRequestException()

			return Recipe.createCriteria().list {	
				and{
					filteredParams.each {
						log.debug "Setting criteria: ${it.key},${it.value}"
						def valores = it.value.split(",")
						log.debug "Split: $valores"
						valores.each {q -> ilike it.key, ("%"+q)}		
					}
				}
			}.collect{it.filterResult()}		

	}
	
}
