cd .\back\
docker compose up config-server -d
timeout /t 5
docker compose up discovery-service -d
timeout /t 10
docker compose up api-gateway -d
timeout /t 5
docker compose up user-service salle-service equipment-service booking-service -d

timeout /t 100
