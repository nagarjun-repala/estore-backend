sql-init:
	docker-compose up -d
	docker-compose logs -f
run:
	./mvnw clean spring-boot:run