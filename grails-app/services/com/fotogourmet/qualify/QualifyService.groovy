package com.fotogourmet.qualify

import com.gmongo.GMongo;
import com.mongodb.DB


class QualifyService {

	static transactional = false
	
	GMongo mongo = new GMongo()
	DB db = mongo.getDB('test')
	
	def saveScore(def id, def score, def acumScore, def countScore) {
	
	//db.recetas.update( { _id: ObjectId( "id" ) } , { $set : { calificacion : score, totalCalificaciones : countScore, sumaCalificaciones : acumScore }})
		

    }
}