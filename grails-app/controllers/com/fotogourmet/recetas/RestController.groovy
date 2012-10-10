package com.fotogourmet.recetas

import com.fotogourmet.exceptions.BadRequestException
import com.fotogourmet.exceptions.ForbiddenException

import grails.converters.JSON
import grails.validation.ValidationException


class RestController {
	
	def recipeService
	def validationService
	def codeService
	def ingredientService
		
	def validationFields = [
		getRecipe: [condition: 'all', fields: ['id']],
		searchRecipes: [condition: 'at_least_one', fields: ['ingredientesB'],
		getCode: [condition: 'all', fields:['ean']]]
	]
	
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
	
	def validate = {
	def validation
	try { 
		validation = ingredientService.validateIng(params.ingrediente)
	} catch (BadRequestException e) {
		response.sendError(501)
	}catch (Exception e) {
		log.error "Error validando el ingrediente", e
		response.sendError(500)
		}
		if (validation){
			response.sendError(200)
			}
			else{
			response.sendError(400)
		}
	}

	def getCode	= {
	def code
	try {
		code = codeService.search(params.codigo)
	} catch (BadRequestException e) {
		response.sendError(501)
		}catch (Exception e) {
		log.error "Error buscando el codigo", e
		response.sendError(500)
		}
		if (!code)
		response.sendError(404)
	
		render code as JSON
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
		
		render result as JSON 
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
