package com.fotogourmet.mongo

import com.gmongo.GMongo;
import com.mongodb.DB

class QueryHelperService {
	
	static transactional = false
	
	GMongo mongo = new GMongo()
	DB db = mongo.getDB('test')

    def doQuery(def collection, def query) {
		def output = db[collection].find(query).skip(0).limit(25)
		log.debug "RESULT COUNT: ${output.count}"
		return output.toString()
    }
	
}
