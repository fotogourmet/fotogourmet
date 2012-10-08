package com.fotogourmet.recetas

import com.fotogourmet.exceptions.BadRequestException
import com.mongodb.DBCollection
import com.gmongo.GMongo
import com.mongodb.DB

def queryHelperService


final def outputParameters = [
	'ingrediente'
	]

def search(def params) {
	
	log.debug "Buscando codigo: $params"
	
	//return queryHelperService.doQuery('codigosbarra', ean: params)
	
	GMongo mongo = new GMongo()
	DB db = mongo.getDB('test')

	return db.codigosbarra.find(ean: params).skip(0).limit(25)*.toMap() //.collect{filterQuery(it)}
			
}
	
def filterQuery(def result) {
	return result?.subMap(outputParameters)

}