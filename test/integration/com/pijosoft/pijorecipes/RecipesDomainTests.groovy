package com.pijosoft.pijorecipes

import static org.junit.Assert.*
import org.junit.*

class RecipesDomainTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        Recipe.list().each {
			it.delete(flush:true)
		}
		Contents.list().each {
			it.delete(flush:true)
		}
    }

    @Test
    void testCanGenerateRecipeDomain() {
        Contents c = new Contents(description: "Receta del pijo", steps:["poner los huevos", "poner la chele"])
		c.save(flush:true)
		Recipe r = new Recipe(authorName:"pijo", difficulty:3, contents:c)
		r.save(flush:true)
		
		def savedRecipe= Recipe.findByAuthorName("pijo")
		
		assertNotNull savedRecipe
		assertNotNull savedRecipe.id
		assertNotNull savedRecipe.dateCreated
		assertNotNull savedRecipe.lastUpdated
		assertNotNull savedRecipe.contents.id
		assertNotNull savedRecipe.dateCreated
		assertNotNull savedRecipe.lastUpdated
		assertEquals "Receta del pijo", savedRecipe.contents.description
		assertEquals "pijo", savedRecipe.authorName
    }
	
}
