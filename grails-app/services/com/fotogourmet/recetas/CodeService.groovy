package com.fotogourmet.recetas

class CodeService {

	def queryHelperService
	def queryUtilsService

	final def outputParameters = [
		'ingrediente'
	]
	
	def search(def params) {
		log.debug "Buscando codigo: $params"
		return queryHelperService.doQuery('codigosbarra', [ean: params]).collect{queryUtilsService.filterQuery(it, outputParameters)}
	}
		
}