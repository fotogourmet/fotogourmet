package com.fotogourmet.recetas

import com.fotogourmet.exceptions.BadRequestException
import com.fotogourmet.exceptions.ForbiddenException

import grails.converters.JSON
import grails.validation.ValidationException


class RestController {
	
	def recipeService
	def validationService
	
	def validationFields = [
		getRecipe: [condition: 'all', fields: ['id']],
		searchRecipes: [condition: 'at_least_one', fields: ['ingredientesB']]
	]
	
	//los applets son clases que interceptan cosas http.
	//este beforeinterceptor se ejecuta antes de cada metodo del controller. Se hace una validaci�n de los parametros.
	def beforeInterceptor = {
		if (!validationService.validateInput(validationFields[actionName], params))
			response.sendError(400)
	}
	
    def getRecipe = {
		
		def recipe 
		try {
			recipe = recipeService.get(params.id)
		} catch (Exception e) {
			log.error "Error trayendo receta con id ${params.id}", e
			response.sendError(500)
		}
		
		if (!recipe)
			response.sendError(404)
			
		render recipe as JSON
	}
	
	
	def searchRecipes = {
		def result
		try {
			result = recipeService.search(params)
		} catch (BadRequestException e) {
			response.sendError(400)
		} catch (Exception e) {
			log.error "Error haciendo query", e
			response.sendError(500)
		}
		
		if (!result)
			response.sendError(404)
		
		render result //as JSON 
	}
	
	def saveRecipe = {
		
		def result
		try {
			result = recipeService.save(request, params)
		} catch (ForbiddenException e) {
			response.sendError(403)
		} catch (ValidationException e) {
			response.sendError(400)
		} catch (Exception e) {
			response.sendError(500)
		}
		
		render result as JSON
	}
}
