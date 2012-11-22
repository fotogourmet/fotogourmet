package com.fotogourmet.mongo

import com.gmongo.GMongo;
import com.mongodb.DB
import org.bson.types.ObjectId


class QueryHelperService {
	
	static transactional = false
	
	GMongo mongo = new GMongo()
	DB db = mongo.getDB('test')
	
	def doQuery(def collection, def query) {
		return db[collection].find(query).skip(0).limit(100)*.toMap()
		
   }
		

	def doQuery(def collection, def query, def sorta) {
		return db[collection].find(query).sort([(sorta):-1]).skip(0).limit(100)*.toMap()

    }
	
	def saveScore(def id, def score, def acumScore, def countScore) {
		
	log.debug "acumScore: ${acumScore}"
	log.debug "countScore: ${countScore}"
	log.debug "score: ${score}"
	db.recetas.update( [ _id: new ObjectId( id ) ] , [ $set : [ calificacion : score, totalCalificaciones : countScore, sumaCalificaciones : acumScore ]])
	
	}
	
	def update(def collection, String id, Map data) {
		db[collection].update([_id: new ObjectId(id)], [$set: data])
		}
		
	
}
