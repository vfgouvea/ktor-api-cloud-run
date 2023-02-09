
gcloud projects list

gcloud config set project PROJETO_ID

docker build -t ktor-api-sample .

gcloud builds submit --tag gcr.io/PROJETO_ID/ktor-api-sample .

gcloud run deploy --image gcr.io/PROJETO_ID/ktor-api-sample ktor-api --port 5000 --region southamerica-east1 --allow-unauthenticated