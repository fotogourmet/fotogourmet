package com.pijosoft.pijorecipes

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.pijosoft.pijorecipes.exceptions.BadRequestException
import org.bson.types.ObjectId

class RecipeService {
	
	static transactional = 'false'
	
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

			//el criteria es poner de forma mas elegante las querys. esto genera una query.
			return Recipe.createCriteria().list {	
				filteredParams.each {
					log.debug "Setting criteria: ${it.key},${it.value}"
					ilike it.key, ("%"+it.value)
					//params.ingredientesB.split(",")
					//ilike it.key, it.value
					// es como un where de un SQL.
				}
			}.collect{it.filterResult()} //Saca la caca que trae mongo, la clase y IDS locos.		
	}
	
}