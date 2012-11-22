 class UrlMappings {

	static mappings = {
		
		"/recipes/search"(controller:"rest"){
			action = [GET: 'searchRecipes']
		}
		
		"/recipes/$id?"(controller: "rest", parseRequest: true){
			action = [GET: 'getRecipe', POST: 'saveRecipe']
		}
		
		"/codigo/$codigo?"(controller: "rest", parseRequest: true){
			action = [GET: 'getCode']
		}
		
		"/validate/$ingrediente?"(controller: "rest", parseRequest: true){
			action = [GET: 'validate']
		}
		"/recipes/$id/score"(controller: "rest", parseRequest: true){
			action = [POST: 'qualifyRecipe']
		}
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"400"(controller: 'error', action: 'badRequest')
		"403"(controller: 'error', action: 'forbidden')
		"404"(controller: 'error', action: 'notFound')
		"500"(controller: 'error', action: 'serverError')
	}
}
