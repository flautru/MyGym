cd .\back\
docker compose up config-server -d
timeout /t 5
docker compose up discovery -d
timeout /t 10
docker compose up gateway -d
timeout /t 5
docker compose up user-services salle-services equipment-services reservation -d

timeout /t 100
