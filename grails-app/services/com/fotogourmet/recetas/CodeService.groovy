package com.fotogourmet.recetas

import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.fotogourmet.exceptions.BadRequestException
import com.gmongo.GMongo
import org.bson.types.ObjectId


def search (def params){
	log.debug "Querying with codigo: $codigo"
	ObjectId oId = new ObjectId(codigo)
	return Recipe.get(oId)?.filterResult()
}