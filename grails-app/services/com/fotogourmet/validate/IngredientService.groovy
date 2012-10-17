package com.fotogourmet.validate

class IngredientService {

	def queryHelperService
	def queryUtilsService
	
	final def outputParameters = [
		'ing'
	]
	
    def validateIng(def params) {
			log.debug "Buscando ingrediente: $params"
			return queryHelperService.doQuery('ingredientes', [$regex:[ing: '/'+params+'/i']]).collect{queryUtilsService.filterQuery(it, outputParameters)}
		}
}