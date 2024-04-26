- CLOUD RUN

gcloud projects list

gcloud config set project PROJETO_ID

gradlew build

iniciar o Docker no pc

docker build -t ktor-api-sample .

rodar docker -> docker run -d -p 8080:8080 --name ktor-docker ktor-api-sample:latest
acessar com localhost:8080

gcloud builds submit --tag gcr.io/PROJETO_ID/ktor-api-sample .
gcloud builds submit --tag gcr.io/testingproject-379516/ktor-api-sample .

gcloud run deploy --image gcr.io/PROJETO_ID/ktor-api-sample ktor-api --port 8080 --region southamerica-east1 --allow-unauthenticated
gcloud run deploy --image gcr.io/testingproject-379516/ktor-api-sample ktor-api --port 8080 --region southamerica-east1 --allow-unauthenticated


- API GATEWAY

Crindo uma API:

gcloud api-gateway apis create API_ID --project=PROJECT_ID
API_ID -> é criado pelo usuario mesmo
ex.: gcloud api-gateway apis create api-testing-project --project=testingproject-379516

	gcloud api-gateway apis describe API_ID --project=PROJECT_ID
	gcloud api-gateway apis describe api-testing-project --project=testingproject-379516

	gcloud api-gateway apis list --project=PROJECT_ID -> listar as apis do projeto

	gcloud api-gateway apis delete API_ID --project=PROJECT_ID -> deletando a api


Criando uma API Config:

Criar um arquivo yaml (gateway-api.yaml) com as configuraçoes da api na linguagem swagger ( openAPI spec )

gcloud api-gateway api-configs create CONFIG_ID --api=API_ID --openapi-spec=gateway-api.yaml --project=PROJECT_ID --backend-auth-service-account=SERVICE_ACCOUNT_EMAIL
	CONFIG_ID proprio usuario cria
	SERVICE_ACCOUNT_EMAIL, criar em IAM and admm ou usar a default ja existente

gcloud api-gateway api-configs create api-gateway-config --api=api-testing-project --openapi-spec=gateway-api.yaml --project=testingproject-379516 --backend-auth-service-account=800153915076-compute@developer.gserviceaccount.com

ver detalhes -> gcloud api-gateway api-configs describe CONFIG_ID --api=API_ID --project=PROJECT_ID
gcloud api-gateway api-configs describe api-gateway-config --api=api-testing-project --project=testingproject-379516

Criando api gateway:


deploy api gateway -> gcloud api-gateway gateways create GATEWAY_ID --api=API_ID --api-config=CONFIG_ID --location=GCP_REGION --project=PROJECT_ID
gcloud api-gateway gateways create api-testing-gateway --api=api-testing-project --api-config=api-gateway-config --location=us-east1 --project=testingproject-379516

visualiar gateway -> gcloud api-gateway gateways describe GATEWAY_ID --location=GCP_REGION --project=PROJECT_ID
gcloud api-gateway gateways describe api-testing-gateway --location=us-east1 --project=testingproject-379516



Para criar uma key:

Ir em Api e Services
habilitar a API que criamos em API library, procurar por api-testing-project
Pos habilitar, ir em Credentials -> Create credentials -> API key
Apos criar, restringir a chave (API Restriction -> restrict key). Selecionar apenas para ser utilizada via api-testing-project


Para monitorar:

Ir em Monitoring -> Alerting -> Create new policy
Adicionar uma condicao para o alerta
Nesse caso selecionamos Produced api with consumer dimension - request backend latencies
Configurar de acordo como deseja

Depois criar canal de notificacao
coloquei para avisar por email


