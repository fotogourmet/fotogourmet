package com.fotogourmet.mongo

import com.gmongo.GMongo;
import com.mongodb.DB

class QueryHelperService {
	
	static transactional = false
	
	GMongo mongo = new GMongo()
	DB db = mongo.getDB('test')

    def doQuery(def collection, def query) {
		return db[collection].find(query).toMap()
    }
	
}
