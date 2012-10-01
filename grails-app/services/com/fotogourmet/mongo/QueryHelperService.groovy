package com.fotogourmet.mongo

import com.gmongo.GMongo;
import com.mongodb.DB

class QueryHelperService {
	
	static transactional = false
	
	GMongo mongo = new GMongo()
	DB db = mongo.getDB('test')

    def doQuery(def collection, def query) {
		def output = []
		def cursor = db[collection].find(query)
		
		try {
			while(cursor.hasNext()) 
				output << cursor
		} finally {
			cursor.close()
		}
		
		return output
    }
	
}
