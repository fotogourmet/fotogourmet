package com.pijosoft.pijorecipes

class AuthService {

    //TODO implementar
	/**
	 * Toma el token de una cookie, verifica que el usuario esté logueado y devuelve el nombre. 
	 * 
	 * @param request
	 * @return El nombre del usuario autenticado
	 * @throws ForbiddenException: en caso de usuario no logueado.
	 */
	def getAuthenticatedUserName(def request) {
		return "Pijo"
    }
}
