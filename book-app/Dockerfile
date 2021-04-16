# to build image run
# docker build . -f Dockerfile -t book-app:latest
FROM node:14.0.0 as build-stage
WORKDIR /app
COPY package*.json /app/
RUN npm install
COPY ./ /app/
ARG configuration=production
RUN npm run build -- --output-path=./dist/out --configuration $configuration

FROM nginx
COPY --from=build-stage /app/dist/out /usr/share/nginx/html