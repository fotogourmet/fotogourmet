package com.fotogourmet.recetas

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class FilterService {

	def grailsApplication
	
	def filterBSONFields(def queryResult) {
		def props = new LinkedHashMap()		
		def dom = new DefaultGrailsDomainClass(queryResult.getClass())
		
		dom.properties.each{
			props[it.name] = filter(queryResult[it.name])
		}
		
		props.id = queryResult.id.toString()
		
		return props
	}
	
    def filter(def field) {
		return grailsApplication.isDomainClass(field.getClass()) ? filterBSONFields(field) : field
    }
}
