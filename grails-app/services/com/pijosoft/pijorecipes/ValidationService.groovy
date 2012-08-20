package com.pijosoft.pijorecipes

import java.util.List;

class ValidationService {
	
	def validators = [
		'all': {fields, params -> return fields.minus(flattenParams(params)).isEmpty()},
		'at_least_one': {fields, params -> return fields.minus(flattenParams(params)).size() < fields.size()},
		'combinations': {fieldList, params -> 
			for (def fields : fieldList) {
				if (fields.minus(flattenParams(params)).isEmpty())
					return true
			}
			return false	
		}	
	]
	

    def validateInput(def fields, def params) {
		if (!fields)
			return true
		
		return validators[fields.condition](fields.fields, params)
	}
	
	def flattenParams(Map params) {
		def output = []
		params.each {
			output.addAll(it.key)
		} 
		return output 
	}
	
}