/**
*
* # Dummy service
*
* This component simulates EventStatus service ADRaAReplikeringEventStatusHent.
* By default it will startup on port 9080.
* for initial and full load are identical; However handling by the Datahandler will be different.
*
* Use **get** method for reading/testing the dummyservice (e.g. http://localhost:9080/ADRaAReplikeringEventStatusHent)
* Use **post** method for setting the response
*
* ### To set ADRaAReplikeringEventStatusHent:
* POST a json snippet using POSTMAN plugin in Chrome Browser like this:
* [
* {
* "entitet": "Adresse",
*  "eventid": 2
*  },
*  {
*  "entitet": "Sogneinddeling",
*  "eventid": 2
*  }
*  ]
*
* ### To set HTTPSTATUS ADRaAReplikeringEventStatusHent:
* POST a json snippet using POSTMAN plugin in Chrome Browser like this (e.g. 400 - Bad request):
*
* [
* {
* "httpStatus": "400"
* }
* ]
**/
package dk.cpr.dar;
