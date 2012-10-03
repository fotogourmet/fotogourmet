package com.fotogourmet.recetas

import com.fotogourmet.recetas.Recipe;
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.fotogourmet.exceptions.BadRequestException
import com.gmongo.GMongo
import org.bson.types.ObjectId

class RecipeService {
	
	static transactional = false
	
	def authService
	def queryHelperService

	final def outputParameters = [
		'ingredientesB',
		'descripcion'
	]
	

	def queryBuilder = [
		'ingredientesB': {val -> [ingredientesB: [$elemMatch: [$regex : '.*'+val+'.*', $options: 'i']]]},
		'categoria': {val -> [categoria: val]}
	]
	
	def availableSearchParams = queryBuilder.keySet() as List
	
    def get(String id) {
		log.debug "Querying with id: $id"
		ObjectId oId = new ObjectId(id)
		return Recipe.get(oId)?.filterResult()
    }
	
	def makeQuery(def params) {
		
		def queryList = []
		params.each {
			it.value?.split(",")?.each{ q ->
				queryList << queryBuilder[it.key](q)
			}
		}
		
		return queryHelperService.doQuery('recetas', [$and: queryList]).collect{filterQuery(it)}
	}
	
	def filterQuery(def result) {
		return result?.subMap(outputParameters)

	}
	
	def search(def params) {
			
		def filteredParams = params.findAll{it.key in availableSearchParams}
				
		log.debug "Filtered params: $filteredParams"
		if (!filteredParams)
			throw new BadRequestException()

		return makeQuery(filteredParams)
	}
	
}
