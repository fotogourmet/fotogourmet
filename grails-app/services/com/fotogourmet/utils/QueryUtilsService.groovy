package com.fotogourmet.utils

class QueryUtilsService {
	
	def addId(def p) {if (p.containsKey("_id")) p["_id"] = p["_id"].toString(); return p}
	
	    def filterQuery(def result, def outputParameters) {
		return addId(result?.subMap(outputParameters))
	}
}
