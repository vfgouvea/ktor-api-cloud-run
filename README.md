
gcloud projects list

gcloud config set project PROJETO_ID

gradlew build

docker build -t ktor-api-sample .

rodar docker -> docker run -d -p 8080:8080 --name ktor_docker ktor-api-sample:latest
                acessar com localhost:8080

gcloud builds submit --tag gcr.io/PROJETO_ID/ktor-api-sample .

gcloud run deploy --image gcr.io/PROJETO_ID/ktor-api-sample ktor-api --port 8080 --region southamerica-east1 --allow-unauthenticated