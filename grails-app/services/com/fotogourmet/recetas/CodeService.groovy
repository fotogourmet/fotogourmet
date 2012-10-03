package com.fotogourmet.recetas

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.fotogourmet.exceptions.BadRequestException
import com.gmongo.GMongo
import org.bson.types.ObjectId


def search (def params){
	log.debug "Querying with codigo: $params"
	return queryHelperService.doQuery('codigosbarra', 'ean').collect{filterQuery(params)}
}