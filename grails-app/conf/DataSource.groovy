// environment specific settings
environments {
    development {
		grails {
			mongo {
				host = "127.0.0.1"
				port = 27017
				//username = "app01"
				//password = "app01"
				databaseName = "test"
				options {
					autoConnectRetry = true
					connectTimeout = 300
				}
			}
		}
	}
    test {
		grails {
			mongo {
				host = "127.0.0.1"
				port = 27017
				//username = "app01"
				//password = "app01"
				databaseName = "test"
				options {
					autoConnectRetry = true
					connectTimeout = 300
				}
			}
		}
    }
    production {
		grails { 
			mongo { 
				host = "127.0.0.1"
				port = 27017
				//username = "app01"
				//password = "app01"
				databaseName = "test"
				options {
					autoConnectRetry = true
					connectTimeout = 300

					}
			}
		}
    }
}
