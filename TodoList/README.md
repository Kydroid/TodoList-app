# Presentation
Test d'une archi hexa light.
Pas tres hexa architecture car plusieurs dependance dans le core.
Mais plus simple pour une appli sans couche métier riche.

# Idée amélioration
Pour une archi hexa plus strict, prévoir :
- le retrait des dependances dans le core domain
- des DTO avec mapper au niveau des couches infra
- une différenciation entity domain != entity jpa
- ...

# Techno 
- Spring boot
- Hibernate
- JPA
- h2database
- Lombok
- jackson
- swagger

# Test 
- test E2E avec TestRestTemplate depuis controller -> persistence
- test integration avec mock repo depuis controller -> usecase
- test usecase
