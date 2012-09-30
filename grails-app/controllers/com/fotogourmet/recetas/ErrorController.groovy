package com.fotogourmet.recetas

import grails.converters.JSON

class ErrorController {

    def badRequest = {
		render status:400, contentType: "application/json", text:([error: 400, status: "Deben especificarse todos los parámetros"] as JSON).toString()
	}
	
	def notFound = {
		render status:404, contentType: "application/json", text:([error: 404, status: "Objeto no encontrado"] as JSON).toString()	
	}
	
	def forbidden = {
		render status:403, contentType: "application/json", text:([error: 403, status: "Usuario no autorizado para acceder a ese recurso"] as JSON).toString()
	}
	
	def serverError = {
		render status:500, contentType: "application/json", text:([error: 500, status: "Error del servidor"] as JSON).toString()
	}
}
