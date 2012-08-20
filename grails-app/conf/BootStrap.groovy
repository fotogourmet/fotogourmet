class BootStrap {
	
	def grailsApplication
	def filterService

    def init = { servletContext ->
		
		grailsApplication.domainClasses.each { domainClass ->
			domainClass.metaClass.filterResult = { 
				return filterService.filter(delegate)
			}
		}
		
    }
    
		def destroy = {
    }
}
