package com.fotogourmet.mongo

import com.gmongo.GMongo;
import com.mongodb.DB

class QueryHelperService {
	
	static transactional = false
	
	GMongo mongo = new GMongo()

    def doQuery(def collection, def query) {
		def db = mongo.getDB(collection)
		
		return db.list(query)
    }
	
}
