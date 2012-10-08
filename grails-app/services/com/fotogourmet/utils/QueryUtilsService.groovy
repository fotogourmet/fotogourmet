package com.fotogourmet.utils

class QueryUtilsService {

    def filterQuery(def result, def outputParameters) {
		return result?.subMap(outputParameters)
	}
}
