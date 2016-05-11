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