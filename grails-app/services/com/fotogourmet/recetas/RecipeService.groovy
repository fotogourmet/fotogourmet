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
	def queryUtilsService

	final def outputParameters = [
		'_id',
		'nombre',
		'imagenPpal',
		'ingredientes',
		'procedimiento',
		'categoria',
		'video',
		'calificacion'
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
	
	def groupQuery(def params){
		return params.groupBy{it.param}.collectEntries{k,v -> [k, v*.value]}
	}
		
	def makeQuery(def params) {
		
		def queryList = []
		params.each {
			it.value?.split(",")?.each{ q ->
				queryList << queryBuilder[it.key](q)
			}
		}
		
		return queryHelperService.doQuery('recetas', [$and: queryList]).collect{queryUtilsService.filterQuery(it, outputParameters)}
	}
	
		
	def search(def params) {
		
		if (params.sort && !(params.sort in ["random", "calificacion"]))
				throw new BadRequestException()
							
		def filteredParams = params.findAll{it.key in availableSearchParams}
				
		log.debug "Filtered params: $filteredParams"
		if (!filteredParams)
			throw new BadRequestException()

		return makeQuery(filteredParams)
	}
	
}
