# Capybara Backend

Capybara Backend service

## Building and running locally

Start Docker daemon:

```shell
colima start
```

Build and run the database:

```shell
docker rm main

docker run --name main \
  -e POSTGRES_USER=main  \
  -e POSTGRES_PASSWORD=mysecretpassword \
  -e POSTGRES_DB=main \
  -p 5432:5432 \
  -d \
  --net=host \
  postgres:14
```

Build and run the application:

```shell
./gradlew clean build
docker build -t capybara-backend:0.0.0-development .
docker image tag capybara-backend:0.0.0-development capybara-backend:latest
docker run -p 8080:8080 --net=host capybara-backend:0.0.0-development
```

## Building and deploying on Google Cloud Run from the local machine

### Building Docker image

```shell
./gradlew clean build
docker build -t {Docker repository}/capybara-backend:0.0.0-development .
docker image tag {Docker repository}/capybara-backend:0.0.0-development {Docker repository}/capybara-backend:latest
docker image push --all-tags {Docker repository}/capybara-backend
```

Replace `{Docker repository}` Artifact Registry repository, please see
the [documentation](https://cloud.google.com/artifact-registry/docs/docker/names) for more information

### Deploying on Google Cloud Run

```shell
gcloud run deploy capybara-backend \
  --image {Docker repository}/capybara-backend:latest \
  --allow-unauthenticated \
  --region={Region} \
  --add-cloudsql-instances={Cloud SQL instance connection name}
```
