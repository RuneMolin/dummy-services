# DummyServices
DummyServices er et projekt tænkt til at kunne simulere DAR service. Pt er følgende implementeret:

-   ADRaAReplikeringEventStatusHent
    * GET - returnerer JSON enten default fra jar, eller hvad der er blevet sat med POST
    * POST - Sætter JSON der bliver returneret

Default starter DummyServices op på port 9080 (kan rettes i application.properties)

For at teste med GET indtast url i en browser, f.eks
```
http://localhost:9080/ADRaAReplikeringEventStatusHent
```

For at sætte JSON, installeres et browser plugin f.eks Postman eller lignende, som gør det muligt at test GET/POST m.fl

# Netcompany implementering
Netcompany har implementeret deres service på en anden måde end beskrevet tidligere. Der er derfor lavet en tilsvarende dummy service som kan simulere
dette:

-   CPRDeltaService
    * CPRDeltaService/Status - GET - returner højste eventId for hvert område
    * CPRDeltaService/Status - POST- Sætter JSON svar
    
    * CPRDeltaService/Records - GET - returnere data for et område 
      eksempel: ```http:localhost9080/CPRDeltaService/Records?Eventstart=0&Eventslut=100&Entitet=DARSogneinddeling&Startindeks=0```
      
    * CPRDeltaService/Records/{} - POST - Sætter JSON for et et område (POST body skal indeholde data)
      eksempel: ```http:localhost9080/CPRDeltaService/Records/DARSogneinddeling```
